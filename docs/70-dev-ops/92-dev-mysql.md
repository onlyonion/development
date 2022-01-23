
## mysql5.6

### 环境变量
MYSQL_HOME
%MYSQL_HOME%\bin;

### my.ini
basedir = D:\opt\wnmp\mysql-5.6.37-winx64
datadir = D:\opt\wnmp\mysql-5.6.37-winx64\data

my.ini文件的编码必须是英文编码（如windows中的ANSI），不能是UTF-8或GBK等

### ms mysql service
mysqld -install
net start mysql

mysqld -remove

mysql -u root -p
update user set password = password('newpwd'), password_expired='N' where user = 'root';


## mysql5.7

mysqld --initialize-insecure --user=mysql
mysqld install MYSQL57 --defaults-file="D:\opt\wnmp\mysql-5.7.22-winx64\my.ini"

mysql -u root -p
update mysql.user set authentication_string=password('newpwd'),password_expired='N' where user='root';
grant all on *.* to root@'%' identified by 'newpwd' with grant option;
flush privileges;

## mysql8

### 大小写不敏感
Edit my.ini file. Add lower_case_table_names=2 under [mysqld]. Save .ini file.
Restart MySQL80 service.

### setup
[mysql install](https://blog.csdn.net/qq_37350706/article/details/81707862)

新建my.ini
```sh
[mysqld]
# 设置3306端口
port=3306
# 设置mysql的安装目录
basedir=C:\Program Files\MySQL
# 设置mysql数据库的数据的存放目录
datadir=C:\Program Files\MySQL\Data
# 允许最大连接数
max_connections=200
# 允许连接失败的次数。
max_connect_errors=10
# 服务端使用的字符集默认为utf8mb4
character-set-server=utf8mb4
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
# 默认使用“mysql_native_password”插件认证
#mysql_native_password
default_authentication_plugin=mysql_native_password
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8mb4
[client]
# 设置mysql客户端连接服务端时默认使用的端口
port=3306
default-character-set=utf8mb4
```


```sh
# init 若存在data文件夹，会失败，重命名，初始化之后改归去，数据就迁移过区了
mysqld --initialize --console
[System] [MY-013169] [Server] D:\opt\wnmp\mysql-8.0.17-winx64\bin\mysqld.exe (mysqld 8.0.17) initializing of server in progress as process 5292
[Note] [MY-010454] [Server] A temporary password is generated for root@localhost: pP53IYtWyS__ # 密码
[System] [MY-013170] [Server] D:\opt\wnmp\mysql-8.0.17-winx64\bin\mysqld.exe (mysqld 8.0.17) initializing of server has completed

# install 安装mysql服务
mysqld --install

## 启动
net start mysql
qYuB-7%l+fDr
mysql -u root -p
ALTER USER 'root'@'localhost' IDENTIFIED BY '123456';

update user set host='%' where user='root';
flush privileges;
```


### MySQL 连接出现 Authentication plugin 'caching_sha2_password' cannot be loaded
```sh
mysql -u root -p
ALTER USER 'root'@'%' IDENTIFIED BY '123456' PASSWORD EXPIRE NEVER;   #修改加密规则 
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';   #更新一下用户的密码 
FLUSH PRIVILEGES;   #刷新权限 
```

### Expression #1 of ORDER BY clause is not in GROUP BY clause and contains nonaggregated column 'information_schema.PROFILING.SEQ' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by
```sh
show variables like "sql_mode"; #STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
# 去掉ONLY_FULL_GROUP_BY,
set sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
```
my.ini文件的`[mysqld]`节点下边添加
```sh
[mysqld]
# 去掉ONLY_FULL_GROUP_BY,
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
```

### JDBC连接MySQL报错Unknown system variable 'query_cache_size'
升级jdbc驱动版本
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.11</version>
</dependency>
```
5.1.48比5.1.42可用性好

 ### MySQL 锁表
 
SELECT * FROM information_schema.INNODB_TRX;
kill 1707385
show OPEN TABLES where In_use > 0;

### mysqld
windows防火墙添加mysqld

### 时区问题
 mysql java.sql.SQLException: The server time zone value‘XXXXXX' is unrecognized or represents...

 jdbc连接的url后面加上serverTimezone=GMT
 低版本的MySQL jdbc驱动
 
### Could not create connection to database server. Attempted reconnect 3 times. Giving up.
```conf
serverTimezone=UTC
jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

# mysql8
driver: com.mysql.cj.jdbc.Driver
url: jdbc:mysql://localhost:3306/demo?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true
&time_zone='+08:00'
&userServerTimezone='+08:00'
```

### mysql重置auto_increment字段
1、直接重置autoIncrement的值
ALTER TABLE table_name AUTO_INCREMENT = 1;

2、通过truncate table 完成
TRUNCATE TABLE table_name;

3、删除表，然后重建表
DROP TABLE table_name;
CREATE TABLE table_name { ... };


### 查看mysql
```sql

show variables like '%max_connection%';
show global status like 'Thread%';

```