# mysql主从复制

* 一主一从
* 主主复制
* 一主多从---扩展系统读取的性能，因为读是在从库读取的；
* 多主一从---5.7开始支持
* 联级复制---

mysql主从复制用途
* 实时灾备，用于故障切换
* 读写分离，提供查询服务
* 备份，避免影响业务
 
主从部署必要条件：
* 主库开启binlog日志（设置log-bin参数）
* 主从server-id不同
* 从库服务器能连通主库

主从复制过程
* 主库会生成一个 log dump 线程，用来给从库 i/o线程传binlog
* 从库生成两个线程，一个I/O线程，一个SQL线程；
* i/o线程去请求主库 的binlog，并将得到的binlog日志写到relay log（中继日志） 文件中；
* SQL线程，会读取relay log文件中的日志，并解析成具体操作，来实现主从的操作一致，而最终数据一致；