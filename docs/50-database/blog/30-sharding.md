# sharding
分库分表；数据库水平拆分、垂直拆分

* sharding-jdbc
* cobar
* mycat

[分库分表的几种常见玩法及如何解决跨库查询等问题](https://blog.csdn.net/dinglang_2009/article/details/53195835)

## 跨库Join的几种解决思路
* 全局表
* 字段冗余
* 数据同步
* 系统层组装 调用不同模块的组件或者服务，获取到数据并进行字段拼装
* 简单的列表查询的情况


## 分表算法
- CRC16 Cyclic Redundancy Check
- %