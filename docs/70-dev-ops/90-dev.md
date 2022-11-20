## git

### git
```sh
git init 
git add 
git commit -m "first commit"
git remote add origin https://github.com/yourspace/yourproject.git
git push -u origin master

### git - unable to clone Host key verification failed. fatal: Could not read from remote repository
# 添加SSH公钥
ssh-keygen -t rsa -C "youraccount" # 注意是邮箱或者账号，或者两个都是 youraccount@email.com 

# git safe directory
git config --global --add safe.directory "*"
# gitignore
git config --global core.excludesfile ~/.gitignore_global
```

## mybatis
\,jdbcType\=[A-Z]*

## rocketmq
```sh
unzip rocketmq.zip -d ./rocketmq
# namesrv
nohup sh bin/mqnamesrv &
tail -f ~/Logs/rocketmqLogs/namesrv.log
# broker
nohup sh bin/mqbroker -n localhost:9876 &

sh bin/mqshutdown broker
sh bin/mqshutdown namesrv
```

## zookeeper
```sh
vim /etc/profile
export ZOOKEEPER_HOME=/opt/zookeeper-3.4.12
export PATH=$PATH:$ZOOKEEPER_HOME
source /etc/profile
# zk standalone
cp zoo_sample.cfg zoo.cfg
mkdir -p /tmp/zookeeper/data
mkdir -p /tmp/zookeeper/log
zkServer.sh start
zkServer.sh status
# cluster zoo.cfg
# server.0=ip1:2888:3888
# server.1=ip2:2888:3888
# server.2=ip3:2888:3888
```

## kafka
```sh
tar xzvf kafka_2.11-2.0.0.tgz
# $KAFKA_HOME /opt/kafka_2.11-2.0.0
# $KAFKA_HOME/conf/server.properties
broker.id=0
listeners=PLAINTEXT://localhost:9092
log.dirs=/tmp/kafka-logs
zookeeper.connect=localhost:2181/kafka

# start
bin/kafka-server-start.sh config/server.properties &
bin/kafka-server-start.sh -daemon config/server.properties
jps -l
# create topics
bin/kafka-topics.sh --zookeeper localhost:2181/kafka --create --topics topic-demo --replication-factor 3 --partitions 4
bin/kafka-console-consumer.sh --boostrap-server localhost:9092 --topic topic-demo
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic-demo
```