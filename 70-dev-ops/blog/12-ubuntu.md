### ssh server

dpkg -l | grep ssh

```
sudo apt-get install openssh-server
//开启服务
sudo /etc/init.d/ssh start 或 sudo service ssh start 

sudo /etc/init.d/ssh start
sudo /etc/init.d/ssh stop
sudo /etc/init.d/ssh restart

```
ssh-server配置文件位于/etc/ssh/sshd_config，在默认端口是22，

* ifconfig


### jdk

* install
tar -zxvf jdk-8u181-linux-x64.tar.gz
cd  /usr/local/lib
sudo mkdir jdk
sudo mv ~/jdk1.8.0_181/usr/local/lib/jdk/

* env
sudo vi /etc/profile

```
#set java env
export JAVA_HOME=/usr/local/lib/jdk/jdk1.8.0_181
export JRE_HOME=${JAVA_HOME}/jre    
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib    
export PATH=${JAVA_HOME}/bin:$PATH
```

source /etc/profile

* default config

```
sudo update-alternatives --install /usr/bin/java  java  /usr/local/lib/jdk/jdk1.8.0_181/bin/java 300   
sudo update-alternatives --install /usr/bin/javac  javac  /usr/local/lib/jdk/jdk1.8.0_181/bin/javac 300
sudo update-alternatives --config java
sudo update-alternatives --config javac
```

### ide

* eclipse
* idea

### maven

sudo mkdir /usr/local/lib/maven/
sudo tar zxvf apache-maven-3.5.4-bin.tar.gz -C /usr/local/lib/maven/

```
export MAVEN_HOME=/usr/local/lib/maven/apache-maven-3.5.4
export PATH=${PATH}:${MAVEN_HOME}/bin
```

source /etc/profile

### ant

### git

* install

```
sudo add-apt-repository ppa:git-core/ppa       //添加源
sudo apt-get update                            //更新
sudo apt-get install git                		 //自动安装git
git --version                                  //确认git版本

//第一次使用前
git config --global user.name "your name" 
git config --global user.email "your email"
git config user.name 
git config user.email
git config --list
```

[help](https://blog.csdn.net/m0_37950361/article/details/80138929)

### gitlab

### nexus

### jenkins

sudo apt-get install -f 
dpkg -i jenkins_2.121.3_all.deb

/usr/bin/
/usr/share/jenkins

sudo systemctl start jenkins
sudo systemctl restart jenkins

```
<securityRealm class="hudson.security.HudsonPrivateSecurityRealm">
	<!-- true modify false -->
	<disableSignup>false</disableSignup> 
	<enableCaptcha>false</enableCaptcha>
</securityRealm>
```

### zookeeper

cd conf
cp zoo_sample.cfg zoo.cfg

* start
sh zkServer.sh start //启动
sh zkServer.sh status //检查状态
sh zkServer.sh stop //停止

* init.d

cd /etc/init.d/
touch zookeeper
chmod +x zookeeper
sudo vim zookeeper

```
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
```

service zookeeper start/stop

chkconfig --add zookeeper
chkconfig --list 


```
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


### ubuntu theme
sudo apt-get update
sudo apt-get install gnome-tweak-tool
sudo apt-get install gnome-shell-extensions

