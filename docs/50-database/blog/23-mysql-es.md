

mysql --> es

1. 同步双写
2. 异步双写  发MQ
3. 定时任务
4. 数据订阅 canal, maxwell 

MySQL 通过 binlog 订阅实现主从同步，各路数据订阅框架比如 canal 就依据这个原理，将 client 组件伪装成从库，来实现数据订阅。