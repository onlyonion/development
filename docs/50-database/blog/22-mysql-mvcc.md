[mysql mvcc](https://blog.csdn.net/weixin_45515047/article/details/123646734)
MVVC 实现：排他锁+undolog+版本事务链+一致性read-view视图+版本事务链匹配规则

### 一致性非锁定读
在 InnoDB 存储引擎中，多版本控制 (multi versioning)open in new window 就是对非锁定读的实现。
如果读取的行正在执行 DELETE 或 UPDATE 操作，这时读取操作不会去等待行上锁的释放。
相反地，InnoDB 存储引擎会去读取行的一个快照数据，对于这种读取历史数据的方式，我们叫它`快照读 (snapshot read)`

在 Repeatable Read 和 Read Committed 两个隔离级别下，如果是执行普通的 select 语句（不包括 select ... lock in share mode ,select ... for update）则会使用 一致性非锁定读（MVCC）。
并且在 Repeatable Read 下 MVCC 实现了可重复读和防止部分幻读

### 锁定读
在锁定读下，读取的是数据的最新版本，这种读也被称为 `当前读（current read）`。锁定读会对读取到的记录加锁

select ... lock in share mode：对记录加 S 锁**（共享锁）**，其它事务也可以加S锁，如果加 x 锁则会被阻塞
select ... for update、insert、update、delete：对记录加 X 锁**（排他锁）**，且其它事务不能加任何锁
如果执行的是下列语句，就是 **锁定读（Locking Reads）**open in new window

select ... lock in share mode
select ... for update
insert、update、delete 操作
InnoDB 在实现Repeatable Read 时，如果执行的是当前读，则会对读取的记录使用 Next-key Lock ，来防止其它事务在间隙间插入数据

### MVCC
MVCC由三个部分实现：隐藏字段、Read View、undo log
- 隐藏字段
  - DB_TRX_ID 创建或者最后一次修改记录的事务id
  - DB_ROW_ID 如果没有设置主键且该表没有唯一非空索引时，InnoDB 会使用该 id 来生成聚簇索引
  - DB_ROLL_PTR 回滚指针，undolog
- undo log
  - insert undo log ：指在 insert 操作中产生的 undo log。
  因为 insert 操作的记录只对事务本身可见，对其他事务不可见，故该 undo log 可以在事务提交后直接删除。
  不需要进行 purge 操作
  - update undo log ：update 或 delete 操作中产生的 undo log。
  该 undo log可能需要提供 MVCC 机制，因此不能在事务提交时就进行删除。
  提交时放入 undo log 链表，等待 purge线程 进行最后的删除
- Read View

RR 与 RC
readview生成的时机是不同的
RC:`每次`在进行快照读的时候都会生成新的readview
RR:只有`第一次`进行快照读的时候才会生成readview，之后的读操作斗湖用第一次生成的readview

### MVCC + Next-key-Lock 防止幻读
InnoDB存储引擎在 RR 级别下通过 MVCC和 Next-key Lock 来解决幻读问题：

1、执行普通 select，此时会以 MVCC 快照读的方式读取数据
在快照读的情况下，RR 隔离级别只会在事务开启后的第一次查询生成 Read View ，并使用至事务提交。
所以在生成 Read View 之后其它事务所做的更新、插入记录版本对当前事务并不可见，实现了可重复读和防止快照读下的 “幻读”

2、执行 select…for update/lock in share mode、insert、update、delete 等当前读
在当前读下，读取的都是最新的数据，如果其它事务有插入新的记录，并且刚好在当前事务查询范围内，就会产生幻读！
InnoDB 使用 Next-key Lock 来防止这种情况。当执行当前读时，会锁定读取到的记录的同时，锁定它们的间隙，防止其它事务在查询范围内插入数据。
只要我不让你插入，就不会发生幻读

### RR or RC
[互联网项目中mysql应该选什么事务隔离级别](https://zhuanlan.zhihu.com/p/59061106)

- 在RR隔离级别下，存在间隙锁，导致出现死锁的几率比RC大的多；
- 在RR隔离级别下，条件列未命中索引会锁表！而在RC隔离级别下，只锁行；
- 在RC隔离级别下，半一致性读(semi-consistent)特性增加了update操作的并发性；

以上列出了选择RC隔离级别的原因，但是使用RC隔离级别要注意一点，就是binlog的格式要设置为`row`模式，即记录每行实际数据的变更。