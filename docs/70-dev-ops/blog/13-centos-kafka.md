Kafka
https://blog.csdn.net/weixin_44911248/article/details/126026111

## 启动
```sh
# 启动
bin/kafka-server-start.sh -daemon config/server.properties 
bin/kafka-server-stop.sh stop

# 查看当前服务器中的所有topic（在kafka目录下）
bin/kafka-topics.sh --zookeeper localhost:2181/kafka --list
# 创建主题topic（在kafka目录下）
bin/kafka-topics.sh --create --zookeeper localhost:2181/kafka --replication-factor 1 --partitions 1 --topic test


# 启动控制台生产者（在kafka目录下）
bin/kafka-console-producer.sh --bootstrap-server localhost:9092/kafka --topic clicks
# 启动控制台消费者（在kafka目录下）（需要另外打开一个终端）
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic example
```


## 管理
```sh
./kafka-consumer-groups.sh --bootstrap-server 192.168.100.77:9092 --list
./kafka-consumer-groups.sh --bootstrap-server 192.168.100.77:9092 --describe --group 用户组名字
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group bwt_cache_member --describe

# 查看topic某分区数据偏移量(offset)最大值，就是看目前Kafka里有多少条消息
./kafka-run-class.sh kafka.tools.GetOffsetShell --broker-list 192.168.100.77:9092 --topic topic队列名字 --partitions 0
```