# 分布式理论与算法

分布式与集群
- 分布式	一个业务分拆多个子业务，部署在不同的服务器上
- 集群		同一个业务，部署在多个服务器上

## ACID模型
关系数据库的ACID模型拥有 高一致性 + 可用性 很难进行分区：
- Atomicity	原子性, 一个事务中所有操作都必须全部完成，要么全部不完成。
- Consistency	一致性, 在事务开始或结束时，数据库应该在一致状态。
- Isolation	隔离层, 事务将假定只有它自己在操作数据库，彼此不知晓。
- Durability	持久性, 一旦事务完成，就不能返回。

## 分布式事务
跨数据库事务：2PC (two-phase commit) is the anti-scalability pattern (Pat Helland) 。
2PC是反可伸缩模式的，JavaEE中的JTA事务可以支持2PC。
因为2PC是反模式，尽量不要使用2PC，使用BASE来回避。

## CAP理论
- Consistency(一致性), 数据一致更新，所有数据变动都是同步的
- Availability(可用性), 好的响应性能
- Partition tolerance(分区容错性) 可靠性

定理：任何分布式系统只可同时满足二点，没法三者兼顾。
忠告：做架构不要将精力浪费在如何设计能满足三者的完美分布式系统，而是应该进行取舍。

## BASE模型
BASE模型反ACID模型，完全不同ACID模型，牺牲高一致性，获得可用性或可靠性：
- Basically Available 基本可用。支持分区失败(e.g. sharding碎片划分数据库)
- Soft state 软状态 状态可以有一段时间不同步，异步。
- Eventually consistent 最终一致，最终数据是一致的就可以了，而不是时时高一致。

BASE的体现
1. 按功能划分数据库
2. sharding分片

## 算法

### paxos算法
基于消息传递且具有高度容错特性的一致性算法，是目前公认的解决分布式一致性问题最有效的算法之一
角色：
* Proposer 提案者 提案者，可能有多个，它门负责提出提案
* Acceptor 接受者 一定要有多个，它们对指定提案进行表决，同意则接受提案，不同意则拒绝
* Learners 学习者 收集每位Acceptor接受的提案，并根据少数服从多数的原则，形成最终提案

提案（Proposal）。最终要达成一致的value就在提案里。
* Paxos的目标：保证最终有一个value会被选定，当value被选定后，进程最终也能获取到被选定的value。
* 分布式系统如何对一个问题达成共识

Paxos算法描述
一个Acceptor只需记住：1. 已接受的编号最大的提案 2. 已响应的请求的最大编号。提案 = 提案编号 + value
* 阶段一：
  * Proposer选择一个提案编号N，然后向半数以上的Acceptor发送编号为N的Prepare请求。
  * 如果一个Acceptor收到一个编号为N的Prepare请求，且N大于该Acceptor已经响应过的所有Prepare请求的编号，
  那么它就会将它已经接受过的编号最大的提案（如果有的话）作为响应反馈给Proposer，同时该Acceptor承诺不再接受任何编号小于N的提案。
* 阶段二：
  * 如果Proposer收到半数以上Acceptor对其发出的编号为N的Prepare请求的响应，那么它就会发送一个针对[N,V]提案的Accept请求给半数以上的Acceptor。
  注意：V就是收到的响应中编号最大的提案的value，如果响应中不包含任何提案，那么V就由Proposer自己决定。
  * 如果Acceptor收到一个针对编号为N的提案的Accept请求，只要该Acceptor没有对编号大于N的Prepare请求做出过响应，它就接受该提案。

![Paxos算法推导](./img/paxos-concept-info.jpg)

Learner学习被选定的value

### Raft
Raft是一种共识算法，旨在替代Paxos。Raft提供了一种在计算系统集群中分布状态机的通用方法，确保集群中的每个节点都同意一系列相同的状态转换。 
Raft通过当选的领导者达成共识。

共识，就是多个节点对某个事情达成一致的算法，即使在部分节点故障、网络延时、网络分割的情况下。

[raft](https://www.backendcloud.cn/2017/11/12/raft-gossip/)

raft 集群中的每个节点都可以根据集群运行的情况在三种状态间切换：follower, candidate 与 leader。
leader 向 follower 同步日志，follower 只从 leader 处获取日志。

Raft一致性算法处理日志复制以保证强一致性。

#### follower 节点不可用
follower 节点不可用的情况相对容易解决。
因为集群中的日志内容始终是从 leader 节点同步的，只要这一节点再次加入集群时重新从 leader 节点处复制日志即可。

#### leader 不可用
一般情况下，leader 节点定时发送 heartbeat 到 follower 节点。

##### 故障转移
由于某些异常导致 leader 不再发送 heartbeat ，或 follower 无法收到 heartbeat 。
当某一 follower 发生 election timeout 时，其状态变更为 candidate，并向其他 follower 发起投票。
当超过半数的 follower 接受投票后，这一节点将成为新的 leader，leader 的步进数加1并开始向 follower 同步日志。

##### 故障恢复
当一段时间之后，如果之前的 leader 再次加入集群，则两个 leader 比较彼此的步进数，步进数低的 leader 将切换自己的状态为 follower。
较早前 leader 中不一致的日志将被清除，并与现有 leader 中的日志保持一致。

### Gossip 
传统的监控，如ceilometer，由于每个节点都会向server报告状态，随着节点数量的增加server的压力随之增大。
分布式健康检查可以解决这类性能瓶颈，降节点数量从数百台扩至数千台，甚至更多。

Gossip协议的最大的好处是，即使集群节点的数量增加，每个节点的负载也不会增加很多，几乎是恒定的。
这就允许Consul管理的集群规模能横向扩展到数千个节点。

Consul的每个Agent会利用Gossip协议互相检查在线状态，本质上是节点之间互Ping，分担了服务器节点的心跳压力。
如果有节点掉线，不用服务器节点检查，其他普通节点会发现，然后用Gossip广播给整个集群。

Gossip算法又被称为反熵（Anti-Entropy），熵是物理学上的一个概念，代表杂乱无章，而反熵就是在杂乱无章中寻求一致，
这充分说明了Gossip的特点：在一个有界网络中，每个节点都随机地与其他节点通信，经过一番杂乱无章的通信，最终所有节点的状态都会达成一致。
