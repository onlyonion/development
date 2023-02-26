
## 读写分离

```sh
docker run -d \
-p 3306:3306 \
-v /atguigu/mysql/master/conf:/etc/mysql/conf.d \
-v /atguigu/mysql/master/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
--name atguigu-mysql-master \
mysql:8.0.29


-- 创建slave用户
CREATE USER 'atguigu_slave'@'%';
-- 设置密码
ALTER USER 'atguigu_slave'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
-- 授予复制权限
GRANT REPLICATION SLAVE ON *.* TO 'atguigu_slave'@'%';
-- 刷新权限
FLUSH PRIVILEGES;



docker run -d \
-p 3307:3306 \
-v /atguigu/mysql/slave1/conf:/etc/mysql/conf.d \
-v /atguigu/mysql/slave1/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
--name atguigu-mysql-slave1 \
mysql:8.0.29

vim /atguigu/mysql/slave1/conf/my.cnf

docker exec -it atguigu-mysql-slave1 env LANG=C.UTF-8 /bin/bash

docker run -d \
-p 3308:3306 \
-v /atguigu/mysql/slave2/conf:/etc/mysql/conf.d \
-v /atguigu/mysql/slave2/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
--name atguigu-mysql-slave2 \
mysql:8.0.29

vim /atguigu/mysql/slave2/conf/my.cnf

# 在从机上配置主从关系
CHANGE MASTER TO MASTER_HOST='192.168.0.121', 
MASTER_USER='atguigu_slave',MASTER_PASSWORD='123456', MASTER_PORT=3306,
MASTER_LOG_FILE='binlog.000003',MASTER_LOG_POS=1357; 

# 启动从机的复制功能，执行SQL：
START SLAVE;
-- 查看状态（不需要分号）
SHOW SLAVE STATUS\G
# **两个关键进程：**下面两个参数都是Yes，则说明主从配置成功！
# slave_io_running
# slave_sql_running
```

## 垂直分片

```sh

docker run -d \
-p 3301:3306 \
-v /atguigu/server/user/conf:/etc/mysql/conf.d \
-v /atguigu/server/user/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
--name server-user \
mysql:8.0.29

```

## 水平分片
`${begin..end}` 表示范围区间
`${[unit1, unit2, unit_x]}` 表示枚举值
行表达式中如果出现连续多个 `${ expression }` 或 `$->{ expression }` 表达式，整个表达式最终的结果将会根据每个子表达式的结果进行笛卡尔组合。


https://shardingsphere.apache.org/document/5.1.1/cn/features/sharding/concept/inline-expression/
```conf
# case 1
db0
  ├── t_order0
  └── t_order1
db1
  ├── t_order0
  └── t_order1

db${0..1}.t_order${0..1}
db$->{0..1}.t_order$->{0..1}

# case 2
db0
  ├── t_order0
  └── t_order1
db1
  ├── t_order2
  ├── t_order3
  └── t_order4
db0.t_order${0..1},db1.t_order${2..4}
db0.t_order$->{0..1},db1.t_order$->{2..4}
```


## canal
https://github.com/alibaba/canal/wiki/Canal-Kafka-RocketMQ-QuickStart