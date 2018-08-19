### ssh server
dpkg -l | grep ssh
sudo apt-get install openssh-server
sudo /etc/init.d/ssh start或sudo service ssh start 

ssh-server配置文件位于/etc/ssh/sshd_config，在默认端口是22，
（或把配置文件中的”PermitRootLogin without-password”加一个”#”号,把它注释掉，再增加一句”PermitRootLogin yes”） 
然后重启SSH服务： 
sudo /etc/init.d/ssh stop 
sudo /etc/init.d/ssh start

* ifconfig

### jdk

* setup
tar -zxvf jdk-8u171-linux-x64.tar.gz
cd  /usr/lib
sudo mkdir jdk
sudo mv ~/jdk1.8.0_171/usr/lib/jdk

* env
sudo vi /etc/profile

```
#set java env
export JAVA_HOME=/usr/local/lib/jdk1.8.0_181
export JRE_HOME=${JAVA_HOME}/jre    
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib    
export PATH=${JAVA_HOME}/bin:$PATH
```

source /etc/profile

```
sudo update-alternatives --install /usr/bin/java  java  /usr/local/lib/jdk/jdk1.8.0_181/bin/java 300   
sudo update-alternatives --install /usr/bin/javac  javac  /usr/local/lib/jdk/jdk1.8.0_181/bin/javac 300
```

### ide

* eclipse
* idea

### maven

### ant

### gitlab

### nexus

### jenkins

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