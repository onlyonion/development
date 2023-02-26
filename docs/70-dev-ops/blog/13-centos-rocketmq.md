
## rocketmq start
```sh
### 启动namesrv
$ nohup sh bin/mqnamesrv &
### 验证namesrv是否启动成功
$ tail -f ~/logs/rocketmqlogs/namesrv.log
The Name Server boot success...

### 先启动broker
$ nohup sh bin/mqbroker -n localhost:9876 &
### 验证broker是否启动成功, 比如, broker的ip是192.168.1.2 然后名字是broker-a
$ tail -f ~/logs/rocketmqlogs/Broker.log 
The broker[broker-a,192.169.1.2:10911] boot success...

### console
nohup java -jar rocketmq-console-ng-1.0.0.jar --rocketmq.config.namesrvAddr=localhost:9876 > /dev/null &
```

### windows
```sh
start mqnamesrv.cmd
start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
```

### JVM配置
```sh
-server -Xms8g -Xmx8g 
# 当JVM是默认8字节对齐，建议配置最大堆内存不要超过32G，否则会影响JVM的指针压缩技术，浪费内存。
-XX:+AlwaysPreTouch
# 禁用偏置锁定可能会减少 JVM 暂停：
-XX:-UseBiasedLocking  
# G1
# 超过了整个Region容量的超级大对象，将会被存放在N个连续的Humongous Region之中，G1的大多数行为都把Humongous Region作为老年代的一部分来进行看待。
-XX:+UseG1GC 
-XX:G1HeapRegionSize=16m   # 取值范围为1MB～32MB，且应为2的N次幂，默认值为0
-XX:G1ReservePercent=25 # 设置堆内存保留为假天花板的总量,以降低提升失败的可能性. 默认值是 10
-XX:InitiatingHeapOccupancyPercent=30 # 启动并发GC周期时的堆内存占用百分比 默认值为 45.
# 另外不要把 -XX:MaxGCPauseMillis 的值设置太小，否则 JVM 将使用一个小的年轻代来实现这个目标，这将导致非常频繁的 minor GC
-XX:+UseGCLogFileRotation   
-XX:NumberOfGCLogFiles=5 
-XX:GCLogFileSize=30m
```