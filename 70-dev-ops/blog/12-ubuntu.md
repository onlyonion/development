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
sudo mv ~/Downloads/soft/jdk1.8.0_181/  /usr/local/lib/jdk/
# set env
sudo vi /etc/profile
export JAVA_HOME=/usr/local/lib/jdk/jdk1.8.0_181
export JRE_HOME=${JAVA_HOME}/jre    
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib    
export PATH=${JAVA_HOME}/bin:$PATH
source /etc/profile
# default config
sudo update-alternatives --install /usr/bin/java  java  /usr/local/lib/jdk/jdk1.8.0_181/bin/java 300   
sudo update-alternatives --install /usr/bin/javac  javac  /usr/local/lib/jdk/jdk1.8.0_181/bin/javac 300
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

### tomcat

### zookeeper

### redis

### rocketmq

### mysql

### mycat


## 应用软件

### 输入法
```shell
# 安装依赖fcitx
sudo apt-get install fcitx-bin
sudo apt-get install fcitx-table
# 下载搜狗.deb 双击安装
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
# vim idea.desktop  # 注意文件的后缀是 .desktop
[Desktop Entry]
Version=2018.2         # 软件的版本
Name=idea    # 软件显示的名称
Comment=Back up your data with one click          # 软件的解释与注释
Exec=/usr/local/lib/idea-IU-182.3911.36/bin/idea.sh       # 开启软件的 脚本
Icon=/usr/local/lib/idea-IU-182.3911.36/bin/idea.png      # 软件的 logo
Terminal=false                                    # 是否在终端打开
Type=Application                                  # 类型
Categories=Utility;Application;                   # 分类

# 添加到 Unity 启动器中 
/usr/share/applications/
```