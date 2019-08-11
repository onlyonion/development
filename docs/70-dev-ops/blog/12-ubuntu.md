# ubuntu

/usr/local/lib

## 安装
- apt方式
- dpkg方式 dpkg -L packagename
- 源码安装

apt安装
下载路径 /var/cache/apt/archives
文档一般在 /usr/share
可执行文件 /usr/bin
配置文件 /etc
lib文件 /usr/lib


## 开发环境

### 命令行启动
```sh
# 字符界面的命令：sudo systemctl set-default multi-user.target
# 图形界面的命令：sudo systemctl set-default graphical.target
```

### ifconfig
```sh
# sudo vi /etc/network/interfaces
auto lo
iface lo inet loopback
auto ens33
iface ens33 inet static
address 192.168.1.100
netmast 255.255.255.0
gateway 192.168.1.1

# sudo /etc/init.d/networking restart重启网络
```

### ssh server
```sh
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
```sh
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
sudo update-alternatives --install /usr/bin/java  java  /usr/local/lib/jdk-11.0.4/bin/java 300   
sudo update-alternatives --install /usr/bin/javac  javac  /usr/local/lib/jdk-11.0.4/bin/javac 300
sudo update-alternatives --config java
sudo update-alternatives --config javac
```

### maven
```sh
# 1. 下载安装
cd /usr/local/lib/
sudo tar zxvf apache-maven-3.3.9-bin.tar.gz 
#sudo tar zxvf apache-maven-3.3.9-bin.tar.gz -C /usr/local/lib/maven/
#set env
sudo vi /etc/profile
export MAVEN_HOME=/usr/local/lib/apache-maven-3.3.9
export M2_HOME=${MAVEN_HOME}
export PATH=${PATH}:${MAVEN_HOME}/bin
source /etc/profile
# 2. apt-get 
# /usr/share/maven
sudo apt-get update && sudo apt-get install maven
```

### ant

### git
```sh
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
```sh
# 双击.deb 安装之后 运行code启动
/usr/share/code
```

### gitlab
[清华大学开源软件镜像站](https://mirror.tuna.tsinghua.edu.cn/help/gitlab-ce/)
```sh
# 信任 GitLab 的 GPG 公钥
curl https://packages.gitlab.com/gpg.key 2> /dev/null | sudo apt-key add - &>/dev/null

# vim /etc/apt/sources.list.d/gitlab_gitlab-ce.list
# deb https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/ubuntu xenial main
sudo apt-get update
sudo apt-get install gitlab-ce
# sudo dpkg -i gitlab-ce_11.11.7-ce.0_amd64.deb 
# sudo vim vim /etc/gitlab/gitlab.rb
# sudo gitlab-ctl reconfigure
# root pwd
```

[gitlab](https://about.gitlab.com/install/#ubuntu)
```sh
sudo apt-get update
sudo apt-get install -y curl openssh-server ca-certificates
sudo apt-get install -y postfix

curl https://packages.gitlab.com/install/repositories/gitlab/gitlab-ee/script.deb.sh | sudo bash
sudo EXTERNAL_URL="https://gitlab.example.com" apt-get install gitlab-ee

```

[Ubuntu 搭建 GitLab 笔记](https://www.cnblogs.com/m2ez/p/7063606.html)
```sh
# 方法1
sudo apt-get install curl openssh-server ca-certificates postfix
sudo curl -sS https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.deb.sh | sudo bash
sudo apt-get install gitlab-ce
# 方法2 手动下载包的方式进行安装
sudo curl -LJO https://packages.gitlab.com/gitlab/gitlab-ce/packages/ubuntu/xenial/gitlab-ce-XXX.deb/download
sudo dpkg -i gitlab-ce-XXX.deb
```


```sh
# su - gitlab-psql //登陆用户 
# sudo passwd 修改密码gitlab
# /opt/gitlab/embedded/bin/psql -h /var/opt/gitlab/postgresql -d gitlabhq_production
# postgresql允许远程访问
# /var/opt/gitlab/postgresql/data/postgresql.conf 改成  listen_addresses='*' 
# /var/opt/gitlab/postgresql/data/pg_hba.conf 添加 
# host  all    all    192.168.1.0/24    trust # trust不需要密码
# host  all    all    0.0.0.0/0    md5
# 库 gitlabhq_production 用户名 gitlab 端口 5432

# Start all GitLab components
sudo gitlab-ctl start
# Stop all GitLab components
sudo gitlab-ctl stop
# Restart all GitLab components
sudo gitlab-ctl restart
```

### nexus

### jenkins
```sh
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
```sh
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
```sh
sudo apt install nginx
```

### tomcat
```sh
/usr/local/lib/apache-tomcat-7.0.70
sudo chmod 755 -R apache-tomcat-7.0.70
```
### redis
```sh
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
# bind 127.0.0.1
sudo /etc/init.d/redis-server restart
sudo service redis-server restart

# 3. redis-server --version 查看版本
```

```sh
# 编译安装
sudo wget http://download.redis.io/releases/redis-5.0.5.tar.gz
sudo tar -zxvf redis-5.0.5.tar.gz
# sudo apt-get install gcc
sudo make
sudo make install
# 安装到目录/usr/local/bin下，配置文件/etc/redis/redis.conf

# 指定配置文件启动
./redis-server /etc/redis/redis.conf
```

### rocketmq
服务端与客户端的版本一定要对应
```sh
# 启动
nohup sh bin/mqnamesrv &
nohup sh bin/mqbroker -n localhost:9876 autoCreateTopicEnable=true &

# 关闭
sh bin/mqshutdown broker
sh bin/mqshutdown namesrv
```

### mysql
```sh
# install 5.7.26
sudo apt-get update
sudo apt-get install mysql-server
sudo apt-get install mysql-client
sudo apt-get install libmysqlclient-dev
# check; mysql listen
sudo netstat -tap | grep mysql
# cat /etc/mysql/debian.cnf 
# mysql -uroot -p
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

# 远程访问 GRANT ALL PRIVILEGES ON *.* TO root@'%' IDENTIFIED BY '密码' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;
FLUSH PRIVILEGES;

# 低版本修改密码
 update user set password=password('123456') where user='root' and host='localhost'; 
 flush privileges; 
```

### mycat


## docker
```sh
wget -qO- https://get.docker.com/ | sh # 获取最新版本的 Docker 安装包
sudo usermod -aG docker onion 
sudo service docker start
docker run hello-world
```

### docker redis
```
docker run -p 6379:6379 
-v /home/user/myredis/data:/data
-v /home/user/myredis/conf/redis.conf:/usr/local/etc/redis/redis.conf
-d redis:3.2 redis-server /usr/local/etc/redis/redis.conf
-- appendonly yes
```

### docker tomcat
### docker mysql
### docker redis
### docker nginx

## 应用软件

### 输入法
```sh
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
```sh
sudo apt-get update
sudo apt-get install gnome-tweak-tool
sudo apt-get install gnome-sh-extensions
```

### 安装仿宋
```sh
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
``` sh
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

### windows + ubuntu + easybcd
```sh
# easybcd 添加grub启动项
# 注意 vmlinuz.efi 与 initrd.lz 是否有后缀名
title Install Ubuntu
root (hd0,0)
kernel (hd0,0)/vmlinuz.efi boot=casper iso-scan/filename=/ubuntu-14.04-desktop-amd64.iso locale=zh_CN.UTF-8
initrd (hd0,0)/initrd.lz

title reboot
reboot

title halt
halt
```
安装后没有ubuntu启动项，回到演示盘
```sh
sudo fdisk -l
sudo -i
mkdir /media/ubuntu
mount /dev/sdb6 /media/ubuntu # sda8 安装的ubuntu分区
``