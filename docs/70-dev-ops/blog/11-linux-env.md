
```sh

export MY_LIB_HOME=/mnt/hgfs/Y/opt

# oraclejdk
export JAVA_HOME=${MY_LIB_HOME}/jdk1.8.0_181
export JRE_HOME=${JAVA_HOME}/jre    
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib    
export PATH=${JAVA_HOME}/bin:$PATH

# openjdk
yum install java-1.8.0-openjdk
# 进入 /usr/lib/jvm/ 目录将 XXX 重命名为 java8
export JAVA_HOME=/usr/lib/jvm/java8/jre
export CLASSPATH=$CLASSPATH:$JAVA_HOME/lib/
export PATH=$PATH:$JAVA_HOME/bin

# mvn
export MAVEN_HOME=${MY_LIB_HOME}/apache-maven-3.3.9
export M2_HOME=${MAVEN_HOME}
export PATH=${PATH}:${MAVEN_HOME}/bin

# zk
export ZOOKEEPER_HOME=${MY_LIB_HOME}/zookeeper-3.4.14
export PATH=${PATH}:${ZOOKEEPER_HOME}/bin

# node
export NODE_HOME=${MY_LIB_HOME}/node-v12.9.0-linux-x64
export PATH=${PATH}:${NODE_HOME}/bin

vim /etc/profile
source /etc/profile
```