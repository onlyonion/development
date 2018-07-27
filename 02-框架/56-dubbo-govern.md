dubbo服务治理

概念
* 超时(timeout)
* 重试(retry)
* 幂等(idempotent)
* 熔断(circuit break)
* 限流(current limiting)
* 服务降级
* 资源隔离

级别
* 应用级别
* 接口级别
* 方法级别


## 2 从微观角度思考

2.1 超时（timeout）
如果超过了时间阈值，就不继续等待。

2.2 重试（retry）
provider偶尔抖动，重试一下
考虑切换一台机器来进行调用

2.2.1 幂等(idempotent)
如果允许consumer重试，那么provider就要能够做到幂等。
即，同一个请求被consumer多次调用，对provider产生的影响(这里的影响一般是指某些写入相关的操作) 是一致的。
而且这个幂等应该是服务级别的，而不是某台机器层面的，重试调用任何一台机器，都应该做到幂等。

2.3 熔断（circuit break）

> 电路保险丝当出现了问题之后，保险丝会自动烧断

如果检查出来频繁超时，就把consumer调用provider的请求，直接短路掉，不实际调用，而是直接返回一个mock的值。
等provider服务恢复稳定之后，重新调用。

spring cloud hystrix
 
2.3.1 简单的熔断处理逻辑	通过注解使用的熔断器

2.4 限流(current limiting)
provider端，根据consumer的重要程度，以及平时的QPS大小，来给每个consumer设置一个流量上线，同一时间内只会给A consumer提供N个线程支持，超过限制则等待或者直接拒绝。

2.4.1 资源隔离
provider可以对consumer来的流量进行限流，防止provider被拖垮。 
同样，consumer 也需要对调用provider的线程资源进行隔离。 这样可以确保调用某个provider逻辑不会耗光整个consumer的线程池资源。
 

2.4.2 服务降级
降级服务既可以代码自动判断，也可以人工根据突发情况切换。

	应用级别
	dubbo.reference.default.break.limit：该参数是配置一个方法在指定时间内出现多少个异常则判断为服务提供方宕机 
	dubbo.reference.default.retry.frequency：该参数配置重试频率，比如配置100，则表示没出现一百次异常则尝试一下远程服务是否可用 
	dubbo.reference.circuit.break:服务降级功能开关，默认是false，表示关闭状态，可以配置为true
	
	接口级别
	dubbo.reference.{fullinterfacename}.break.limit
	{fullinterfacename}.retry.frequency
	dubbo.reference.${fullinterfacename}.circuit.break
	方法级别
	dubbo.reference.{methodName}.break.limit
	dubbo.reference.{methodName}.retry.frequency
	dubbo.reference.{methodName}.circuit.break
	

2.4.2.1 consumer 端
consumer 如果发现某个provider出现异常情况，比如，经常超时(可能是熔断引起的降级)，数据错误，这是，consumer可以采取一定的策略，降级provider的逻辑，基本的有直接返回固定的数据。

2.4.2.2 provider 端
当provider 发现流量激增的时候，为了保护自身的稳定性，也可能考虑降级服务。 
比如，1，直接给consumer返回固定数据，2，需要实时写入数据库的，先缓存到队列里，异步写入数据库。

## 3 从宏观角度重新思考

长链路 A -> B -> C -> D

3.1 超时
3.2 重试
比如下面情况:
A -> B -> C。
RB = 100ms，TBC=10ms
B是个核心服务，B的计算成本特别大，那么A就应该尽量给B长一点的超时时间，而尽量不要重试调用B，而B如果发现C超时了，B可以多调用几次C，因为重试C成本小，而重试B成本则很高。 so …

3.3 熔断
A -> B -> C，如果C出现问题了，那么B熔断了，则A就不用熔断了。

3.4 限流
B只允许A以QPS<=5的流量请求，而C却只允许B以QPS<=3的qps请求，那么B给A的设定就有点大，上游的设置依赖下游。
而且限流对QPS的配置，可能会随着服务加减机器而变化，最好是能在集群层面配置，自动根据集群大小调整。

3.5 服务降级
服务降级这个问题，如果从整体来操作，
1，一定是先降级优先级地的接口，两权相害取其轻 
2，如果服务链路整体没有性能特别差的点，比如就是外部流量突然激增，那么就从外到内开始降级。 
3如果某个服务能检测到自身负载上升，那么可以从这个服务自身做降级。

3.6 涟漪
3.7 级联失败(cascading failure)
3.8 关键路径
3.9 最长路径


[摘自-dubbo 熔断，限流，降级](https://blog.csdn.net/world_snow/article/details/79080314)
[摘自-实现dubbo服务降级](https://blog.csdn.net/qq_34531925/article/details/79496126)