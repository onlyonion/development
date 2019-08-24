
```sh

export MY_LIB_HOME=/mnt/hgfs/Y/opt

# jdk
export JAVA_HOME=${MY_LIB_HOME}/jdk1.8.0_181
export JRE_HOME=${JAVA_HOME}/jre    
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib    
export PATH=${JAVA_HOME}/bin:$PATH

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

```