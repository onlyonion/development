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

redmi pro
00342-36095-22725-AAOEM

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

MAVEN_HOME	D:\opt\java\apache-maven-3.6.0
M2_HOME	    %MAVEN_HOME%
ANT_HOME	D:\opt\java\apache-ant-1.9.14

# .${user}/.m2/setting.xml 配置

# 编码
JAVA_OPTS -Dfile.encoding=UTF-8
GRADLE_OPTS -Dfile.encoding=UTF-8
GRADLE_HOME D:\opt\java\gradle-6.6.1
GRAILS_HOME D:\opt\java\grails-4.0.0
GROOVY_HOME D:\opt\java\groovy-2.5.8

# 修改 .gradle\caches\modules-2\files-2.1
GRADLE_USER_HOME D:\m2\repository

ROCKETMQ_HOME   D:\opt\java\rocketmq-4.3.0
ZOOKEEPER_HOME  D:\opt\java\zookeeper-3.4.8

GRAPHVIZ_DOT    D:\opt\tool\graphviz-2.38\bin\dot.exe

Path		;%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;%MAVEN_HOME%\bin;%GRADLE_HOME%\bin;%ANT_HOME%\bin
NLS_LANG 	AMERICAN_AMERICA.AL32UTF8

# scala
SCALA_HOME = D:\opt\java\scala-2.11.5
Path = %SCALA_HOME%\bin
ClassPath = .;%SCALA_HOME%\bin;%SCALA_HOME%\lib\dt.jar;%SCALA_HOME%\lib\tools.jar.;

# hadoop
HADDOOP_HOME 
Path		;%HADDOOP_HOME%\bin;
```

### 1.2 java doc
-encoding UTF-8 -charset UTF-8

## 2. nodejs
```sh
NODE_HOME D:\opt\nodejs\node-v16.15.0-win-x64
Path ;%NODE_HOME%

npm install cnpm -g --registry=https://registry.npm.taobao.org
cnpm install digo -g

npm install --global vue-cli
npm install -g webpack
```

## 3. php
## 4. python
CONDA_HOME  D:\opt\tensorflow\anaconda3
PYTHON2     D:\opt\python\python27
PYTHON      D:\opt\python\python39
Path        %PYTHON2%;%PYTHON2%\Scripts;%PYTHON%;%PYTHON%\Scripts
```sh
pip3 install -i https://pypi.tuna.tsinghua.edu.cn/simple 要安装的库的名称
```

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

## nginx
```sh
# start
start nginx

nginx -s reload
# 检查参数
nginx -t

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
    server 127.0.0.1:8080 weight=1  max_fails=1  fail_timeout=20;
    server 127.0.0.1:8081 weight=5  max_conns=800;
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


## openssl
```sh
openssl version
```

### keytool
```sh
keytool -genkey -alias undertow -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -dname "CN=li, OU=dream, O=dream, L=bj, ST=bj, C=CN"
```

### github
```sh
nslookup github.global.ssl.fastly.net
nslookup github.com

173.252.102.241 github.global.ssl.fastly.net
20.205.243.166 github.com

# windows
ipconfig /flushdns 
```

## windows11
```sh
# 切换到旧版右键菜单：
reg add "HKCU\Software\Classes\CLSID\{86ca1aa0-34aa-4e8b-a509-50c905bae2a2}\InprocServer32" /f /ve
# 恢复回Win11右键菜单：
reg delete "HKCU\Software\Classes\CLSID\{86ca1aa0-34aa-4e8b-a509-50c905bae2a2}" /f
# 重启Windows资源管理器生效：
taskkill /f /im explorer.exe & start explorer.exe
```