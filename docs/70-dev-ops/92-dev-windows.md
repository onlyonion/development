# windows

## 激活
win10教育版激活(32位和64位均可正常使用)：
    NW6C2-QMPVW-D7KKK-3GKT6-VCFB2
    2WH4N-8QGBV-H22JP-CT43Q-MDWWJ

slmgr /ipk NW6C2-QMPVW-D7KKK-3GKT6-VCFB2
slmgr /skms kms.xspace.in
slmgr /ato


# dev environment
## 1. java
### 1.1 java环境变量
```sh
JAVA_HOME	D:\opt\java\jdk1.8.0_131
JRE_HOME    %JAVA_HOME%\jre
CLASSPATH	.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar

MAVEN_HOME	D:\opt\java\apache-maven-3.3.9
ANT_HOME	D:\opt\java\apache-ant-1.9.14

GRADLE_HOME D:\opt\java\gradle-5.6
GRAILS_HOME D:\opt\java\grails-4.0.0
GROOVY_HOME D:\opt\java\groovy-2.5.8

# 修改 .gradle\caches\modules-2\files-2.1
GRADLE_USER_HOME D:\m2\repository

ROCKETMQ_HOME   D:\opt\java\rocketmq-4.3.0
ZOOKEEPER_HOME  D:\opt\java\zookeeper-3.4.8

GRAPHVIZ_DOT    D:\opt\tool\graphviz-2.38\bin\dot.exe

Path		;%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;%MAVEN_HOME%\bin;%ANT_HOME%\bin
NLS_LANG 	"AMERICAN_AMERICA.AL32UTF8"
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
## mysql

### 大小写不敏感
Edit my.ini file. Add lower_case_table_names=2 under [mysqld]. Save .ini file.
Restart MySQL80 service.

### setup
[mysql install](https://blog.csdn.net/qq_37350706/article/details/81707862)

mysqld --initialize --console
[System] [MY-013169] [Server] C:\opt\wnmp\mysql-8.0.17-winx64\bin\mysqld.exe (mysqld 8.0.17) initializing of server in progress as process 5292
[Note] [MY-010454] [Server] A temporary password is generated for root@localhost: pP53IYtWyS__
[System] [MY-013170] [Server] C:\opt\wnmp\mysql-8.0.17-winx64\bin\mysqld.exe (mysqld 8.0.17) initializing of server has completed
mysqld --install

net start mysql

mysql -u root -p
ALTER USER 'root'@'localhost' IDENTIFIED BY '新密码';

update user set host='%' where user='root';
flush privileges;

### 时区问题
 mysql java.sql.SQLException: The server time zone value‘XXXXXX' is unrecognized or represents...

 jdbc连接的url后面加上serverTimezone=GMT
 低版本的MySQL jdbc驱动