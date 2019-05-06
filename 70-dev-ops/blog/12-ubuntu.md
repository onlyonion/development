# ubuntu

/usr/local/lib

## 开发环境
### ssh server
```shell
# 
dpkg -l | grep ssh
sudo apt-get update
sudo apt-get install openssh-server
# 开启服务
sudo /etc/init.d/ssh start 或 sudo service ssh start 
sudo /etc/init.d/ssh start
sudo /etc/init.d/ssh stop
sudo /etc/init.d/ssh restart
```
ssh-server配置文件位于/etc/ssh/sshd_config，在默认端口是22，
* ifconfig

### jdk
```shell
# unpack
tar -zxvf jdk-8u181-linux-x64.tar.gz
cd  /usr/local/lib
sudo mkdir jdk
sudo mv ~/Downloads/soft/jdk1.8.0_181/  /usr/local/lib/
# set env
sudo vi /etc/profile
export JAVA_HOME=/usr/local/lib/jdk1.8.0_181
export JRE_HOME=${JAVA_HOME}/jre    
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib    
export PATH=${JAVA_HOME}/bin:$PATH
source /etc/profile
# default config
sudo update-alternatives --install /usr/bin/java  java  /usr/local/lib/jdk1.8.0_181/bin/java 300   
sudo update-alternatives --install /usr/bin/javac  javac  /usr/local/lib/jdk1.8.0_181/bin/javac 300
sudo update-alternatives --config java
sudo update-alternatives --config javac
```

### maven
```shell
# 1. 下载安装
cd /usr/local/lib/
sudo tar zxvf apache-maven-3.3.9-bin.tar.gz 
#sudo tar zxvf apache-maven-3.3.9-bin.tar.gz -C /usr/local/lib/maven/
#set env
sudo vi /etc/profile
export MAVEN_HOME=/usr/local/lib/apache-maven-3.3.9
export PATH=${PATH}:${MAVEN_HOME}/bin
source /etc/profile
# 2. apt-get 
# /usr/share/maven
sudo apt-get update && sudo apt-get install maven
```

### ant

### git
```shell
# 添加源
sudo add-apt-repository ppa:git-core/ppa
# 更新
sudo apt-get update
# 自动安装git
sudo apt-get install git
# 确认git版本
git --version
# 第一次使用前
git config --global user.name "your name" 
git config --global user.email "your email"
git config user.name 
git config user.email
git config --list
# 密钥 id_rsa和id_rsa.pub
ssh-keygen -t rsa -C "your email"
```
[help](https://blog.csdn.net/m0_37950361/article/details/80138929)

### eclipse
### idea
### vscode
```shell
# 双击.deb 安装之后 运行code启动
/usr/share/code
```

### gitlab
[gitlab](https://about.gitlab.com/install/#ubuntu)
```shell
sudo apt-get update
sudo apt-get install -y curl openssh-server ca-certificates
sudo apt-get install -y postfix

curl https://packages.gitlab.com/install/repositories/gitlab/gitlab-ee/script.deb.sh | sudo bash
sudo EXTERNAL_URL="https://gitlab.example.com" apt-get install gitlab-ee

```

[Ubuntu 搭建 GitLab 笔记](https://www.cnblogs.com/m2ez/p/7063606.html)
```shell
# 方法1
sudo apt-get install curl openssh-server ca-certificates postfix
sudo curl -sS https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.deb.sh | sudo bash
sudo apt-get install gitlab-ce
# 方法2 手动下载包的方式进行安装
sudo curl -LJO https://packages.gitlab.com/gitlab/gitlab-ce/packages/ubuntu/xenial/gitlab-ce-XXX.deb/download
sudo dpkg -i gitlab-ce-XXX.deb
```

[清华大学开源软件镜像站](https://mirror.tuna.tsinghua.edu.cn/help/gitlab-ce/)
```shell
# 信任 GitLab 的 GPG 公钥
curl https://packages.gitlab.com/gpg.key 2> /dev/null | sudo apt-key add - &>/dev/null
deb https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/ubuntu xenial main
sudo apt-get update
sudo apt-get install gitlab-ce
```


### nexus

### jenkins
```shell
sudo apt-get install -f 
dpkg -i jenkins_2.121.3_all.deb
/usr/bin/
/usr/share/jenkins
sudo systemctl start jenkins
sudo systemctl restart jenkins
```

```xml
<securityRealm class="hudson.security.HudsonPrivateSecurityRealm">
	<!-- true modify false -->
	<disableSignup>false</disableSignup> 
	<enableCaptcha>false</enableCaptcha>
</securityRealm>
```

### zookeeper
```shell
# conf
cp zoo_sample.cfg zoo.cfg
# 启动
sh zkServer.sh start
# 检查状态
sh zkServer.sh status
# 停止
sh zkServer.sh stop
# init.d
cd /etc/init.d/
touch zookeeper
chmod +x zookeeper
sudo vim zookeeper

# config 脚本文件
#!/bin/bash
#chkconfig:2345 20 90
#description:zookeeper
#processname:zookeeper
case $1 in
	start) su root /data/zookeeper-3.4.8/bin/zkServer.sh start;;
	stop) su root /data/zookeeper-3.4.8/bin/zkServer.sh stop;;
	status) su root /data/zookeeper-3.4.8/bin/zkServer.sh status;;
	restart) su root /data/zookeeper-3.4.8/bin/zkServer.sh restart;;
	*)  echo "require start|stop|status|restart"  ;;
esac
# env
service zookeeper start/stop
chkconfig --add zookeeper
chkconfig --list 
export ZOOKEEPER_HOME=/data/zookeeper-3.4.8
export PATH=${PATH}:${ZOOKEEPER_HOME}/bin
```
### nginx
```shell
sudo apt install nginx
```
### tomcat
```shell
/usr/local/lib/apache-tomcat-7.0.70
sudo chmod 755 -R apache-tomcat-7.0.70
```
### redis
```shell
# apt安装
# 1. 安装
sudo apt-get install redis-server
# 检查Redis服务器系统进程
ps -aux|grep redis
# 通过启动命令检查Redis服务器状态
netstat -nlt|grep 6379
# 通过启动命令检查Redis服务器状态
sudo /etc/init.d/redis-server status
# 2. 修改配置
sudo vi /etc/redis/redis.conf
# 设置密码 取消注释requirepass 
requirepass redisredis
# 让Redis服务器被远程访问 注释bind
#bind 127.0.0.1
sudo /etc/init.d/redis-server restart
sudo service redis-server restart

# 编译安装
sudo wget http://download.redis.io/releases/redis-3.2.6.tar.gz
sudo tar -zxvf redis-3.2.6.tar.gz
# sudo apt-get install gcc
sudo make
sudo make install
# 安装到目录/usr/local/bin下
```

### rocketmq

### mysql
```shell
# install 5.7.26
sudo apt-get update
sudo apt-get install mysql-server
sudo apt-get install mysql-client
sudo apt-get install libmysqlclient-dev
# check; mysql listen
sudo netstat -tap | grep mysql
# cat /etc/mysql/debian.cnf 
mysql -udebian-sys-maint -p
# password mysql5.7没有password字段，密码存储在authentication_string字段
show databases;
use mysql;
update user set authentication_string=PASSWORD("123456") where user='root';
update user set plugin="mysql_native_password";
flush privileges;
quit;
# restart
/etc/init.d/mysql restart;
```

### mycat

## 应用软件

### 输入法
```shell
# 安装依赖fcitx
sudo apt-get install fcitx-bin
sudo apt-get install fcitx-table

# 下载搜狗.deb 双击安装

sudo apt-get update
sudo add-apt-repository ppa:fcitx-team/nightly
sudo apt-get -f install
sudo apt-get upgrade

```
### ubuntu theme
```shell
sudo apt-get update
sudo apt-get install gnome-tweak-tool
sudo apt-get install gnome-shell-extensions
```
### 安装仿宋
```shell
# copy
sudo cp simfang.ttf /usr/share/fonts
cd  /usr/share/fonts
sudo chmod 644 simfang.ttf
# update cache
sudo mkfontscale
sudo mkfontdir
sudo fc-cache -fsv
```
### 快捷方式
``` shell
# vim jetbrains-idea.desktop  # 注意文件的后缀是 .desktop
[Desktop Entry]
Version=2019.1.1     
Name=jetbrains-idea
Comment=jetbrains-idea
Exec=/usr/local/lib/idea-IU-191.6707.61/bin/idea.sh
Icon=/usr/local/lib/idea-IU-191.6707.61/bin/idea.png
Terminal=false
Type=Application
Categories=Utility;Application;

# 复制到 Unity 启动器中 
/usr/share/applications/
```