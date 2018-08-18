## Java内存模型

### 主内存与工作内存

### java内存交互协议

| 指令  	 |	作用范围		|	描述								|
| :---:  |	:---:		|	:---:							|
| lock	 | 主内存变量	|									|
| unlock | 主内存变量	|									|
| read 	 | 主内存变量	| 由主内存传输到工作内存				|
| load 	 | 工作内存变量	| 由read的变量放入到工作内存变量副本	|
| use 	 | 工作内存变量	| 由工作内存传递给执行引擎			|
| assign | 工作内存变量	| 接收执行引擎变量赋值给工作内存变量	|
| store  | 工作内存变量	| 传输到主内存						|
| write  | 主内存变量	| store的变量放入主内存变量			|

```
	主内存  <--> 工作内存 <--> 执行引擎
```

### java线程的实现

### Happens-Before规则
1.	程序顺序规则：一个线程中的每个操作，happens-before于该线程中的任意后续操作
2.	监视器锁规则：对一个锁的解锁，hanppen-before于随后对这个锁的加锁
3.	volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读
4.	传递性：如果A happens-before B，且B happens-before C，那么A happens-before C
5.	start()规则：如果线程A执行操作ThreadB.start()（启动线程B），那么A线程的ThreadB.start()操作happens-before于线程B中的任意操作
6.	join()规则：如果线程A执行操作ThreadB.join()并成功返回，那么线程B中的任意操作happens-before与线程A从ThreadB.join()操作成功返回。
