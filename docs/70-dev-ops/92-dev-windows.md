# windows

## 激活
win10教育版激活(32位和64位均可正常使用)：
    NW6C2-QMPVW-D7KKK-3GKT6-VCFB2
    2WH4N-8QGBV-H22JP-CT43Q-MDWWJ
```sh
slmgr /ipk NW6C2-QMPVW-D7KKK-3GKT6-VCFB2
slmgr /skms kms.xspace.in
slmgr /ato

# 打开“注册表编辑器”；（Windows + R然后输入 Regedit）
# 修改SkipRearm 的值为1：（在HKEY_LOCAL_MACHINE–》SOFTWARE–》Microsoft–》Windows NT–》CurrentVersion–》SoftwareProtectionPlatform里面，将SkipRearm的值修改为1）重启电脑
# 以管理员身份启动cmd，输入SLMGR -REARM，根据提示，再次重启电脑！
slmgr /ipk DCPHK-NFMTC-H88MJ-PFHPY-QJ4BJ # 弹出窗口提示：“成功的安装了产品密钥”。
slmgr /skms xykz.f3322.org # 密钥管理服务计算机名称成功设置为xykz.f3322.org
slmgr /ato # 按回车键后将弹出窗口提示：“成功的激活了产品”。
```

## 电源管理
```sh
# 卓越性能
# win + x 打开菜单，windows powershell 管理员
powercfg -duplicatescheme e9a42b02-d5df-448d-aa00-03f14749eb61 
```

# dev environment
## 1. java
### 1.1 java环境变量
```sh
JAVA_HOME	D:\opt\java\jdk1.8.0_221
JRE_HOME    %JAVA_HOME%\jre
CLASSPATH	.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar

MAVEN_HOME	D:\opt\java\apache-maven-3.3.9
M2_HOME	D:\opt\java\apache-maven-3.3.9
ANT_HOME	D:\opt\java\apache-ant-1.9.14

# .${user}/.m2/setting.xml 配置

# 编码
GRADLE_OPTS -Dfile.encoding=UTF-8
GRADLE_HOME D:\opt\java\gradle-5.6
GRAILS_HOME D:\opt\java\grails-4.0.0
GROOVY_HOME D:\opt\java\groovy-2.5.8

# 修改 .gradle\caches\modules-2\files-2.1
GRADLE_USER_HOME D:\m2\repository

ROCKETMQ_HOME   D:\opt\java\rocketmq-4.3.0
ZOOKEEPER_HOME  D:\opt\java\zookeeper-3.4.8

GRAPHVIZ_DOT    D:\opt\tool\graphviz-2.38\bin\dot.exe

Path		;%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;%MAVEN_HOME%\bin;%GRADLE_HOME%\bin
NLS_LANG 	AMERICAN_AMERICA.AL32UTF8
```

### 1.2 java doc
-encoding UTF-8 -charset UTF-8

### 1.3 .bat
```sh
SETX JAVA_HOME "D:\opt\java\jdk1.8.0_131" /m
SETX JRE_HOME "%JAVA_HOME%\jre" /m
SETX M2_HOME "D:\opt\apahce\apache-maven-3.3.9" /m
SETX ANT_HOME "D:\opt\apahce\apache-ant-1.10.1" /m
SETX NLS_LANG "AMERICAN_AMERICA.AL32UTF8" /m
SETX Path "%JAVA_HOME%\bin;%M2_HOME%\bin;%ANT_HOME%\bin;%PATH%;"
```

## 2. nodejs
```sh
npm install cnpm -g --registry=https://registry.npm.taobao.org
cnpm install digo -g

npm install --global vue-cli
npm install -g webpack

```

## 3. php
## 4. python
## 5. object c, swift

# windows
## 查看端口占用
windows

netstat -aon|findstr "8080"
tasklist|findstr "2720"
taskkill /f /t /im tor.exe

linux 
netstat -tunlp | grep 8000

### 打开系统属性
sysdm.cpl

### host
```sh
# ipconfig /flushdns
192.30.253.112 github.com
151.101.185.194 github.global.ssl.fastly.net
192.30.255.113 www.github.com
192.30.255.120 nodeload.github.com
```

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
# init
mysqld --initialize --console
[System] [MY-013169] [Server] C:\opt\wnmp\mysql-8.0.17-winx64\bin\mysqld.exe (mysqld 8.0.17) initializing of server in progress as process 5292
[Note] [MY-010454] [Server] A temporary password is generated for root@localhost: pP53IYtWyS__ # 密码
[System] [MY-013170] [Server] C:\opt\wnmp\mysql-8.0.17-winx64\bin\mysqld.exe (mysqld 8.0.17) initializing of server has completed

# install
mysqld --install

## 启动
net start mysql

mysql -u root -p
ALTER USER 'root'@'localhost' IDENTIFIED BY '新密码';

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

### 时区问题
 mysql java.sql.SQLException: The server time zone value‘XXXXXX' is unrecognized or represents...

 jdbc连接的url后面加上serverTimezone=GMT
 低版本的MySQL jdbc驱动

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

### Could not create connection to database server. Attempted reconnect 3 times. Giving up.
```conf
serverTimezone=UTC
jdbc:mysql://localhost:3306/SshProject?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
```

## nginx
```sh
# start
start nginx

# view
tasklist /fi "imagename eq nginx.exe"
nginx -v

# port
netstat -ano | findstr 0.0.0.0:80
netstat -ano | findstr "80"

# stop
nginx -s stop # (快速停止nginx) 
nginx -s quit # (完整有序的停止nginx)
taskkill /f /t /im nginx.exe
```

### nginx-tomcat
```conf
upstream backend {
    server 127.0.0.1:8080;
    server 127.0.0.1:8081;
}

server {
    listen 80;
    server_name localhost;

    location / {
        proxy_pass http://backend;
    }
}
```

重复绑定了server name，这个警告不会影响到服务器运行，现在运行的nginx服务和将要加载的新配置中的重复。

### root 、alias指令区别

location /img/ {
    alias /var/www/image/;
}
#若按照上述配置的话，则访问/img/目录里面的文件时，ningx会自动去/var/www/image/目录找文件
location /img/ {
    root /var/www/image;
}
#若按照这种配置的话，则访问/img/目录下的文件时，nginx会去/var/www/image/img/目录下找文件。] 
alias是一个目录别名的定义，root则是最上层目录的定义。

还有一个重要的区别是alias后面必须要用“/”结束，否则会找不到文件的。。。而root则可有可无~~