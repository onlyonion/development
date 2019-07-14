## 负载均衡
[loadbalance](http://dubbo.apache.org/zh-cn/docs/source_code_guide/loadbalance.html)

## 1. 简介
* 职责是将网络请求，或者其他形式的负载“均摊”到不同的机器上。
* 避免集群中部分服务器压力过大，而另一些服务器比较空闲的情况。
* 通过负载均衡，可以让每台服务器获取到适合自己处理能力的负载。
* 在为高负载服务器**分流**的同时，还可以避免资源浪费，一举两得。
* 负载均衡可分为软件负载均衡和硬件负载均衡。nginx，F5

## 2.源码分析
AbstractLoadBalance
* select() 多个提供者（集群环境）时使用负载均衡算法
* getWeight() 服务预热，JVM预热

### 2.1 RandomLoadBalance   加权随机算法


### 2.2 LeastActiveLoadBalance
活跃调用数越小，表明该服务提供者效率越高，单位时间内可处理更多的请求。此时应优先将请求分配给该服务提供者

初始情况下，所有服务提供者活跃数均为0。每收到一个请求，活跃数加1，完成请求后则将活跃数减1。
在服务运行一段时间后，性能好的服务提供者处理请求的速度更快，因此活跃数下降的也越快

LeastActiveLoadBalance 在实现上还引入了权重值。所以准确的来说，LeastActiveLoadBalance 是基于**加权最小活跃数算法**实现的

算法：
1. 遍历 invokers 列表，寻找活跃数最小的 Invoker
2. 如果有多个 Invoker 具有相同的最小活跃数，此时记录下这些 Invoker 在 invokers 集合中的下标，并累加它们的权重，比较它们的权重值是否相等
3. 如果只有一个 Invoker 具有最小的活跃数，此时直接返回该 Invoker 即可
4. 如果有多个 Invoker 具有最小活跃数，且它们的权重不相等，此时处理方式和 RandomLoadBalance 一致
5. 如果有多个 Invoker 具有最小活跃数，但它们的权重相等，此时随机返回一个即可

### 2.3 ConsistentHashLoadBalance
一致性 hash 算法由麻省理工学院的 Karger 及其合作者于1997年提供出的，算法提出之初是用于大规模**缓存系统**的负载均衡

### 2.4 RoundRobinLoadBalance   加权轮询负载均衡

1. 轮询是一种无状态负载均衡算法，实现简单，适用于每台服务器性能相近的场景下
2. 经过加权后，每台服务器能够得到的请求数比例，接近或等于他们的权重比

平滑加权轮询算法