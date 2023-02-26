Kafka


### 分数数量预估
1. 创建一个只有1个分区的topic
2. 测试这个topic的producer吞吐量和consumer吞吐量。
3. 假设他们的值分别是Tp和Tc，单位可以是MB/s。
4. 然后假设总的目标吞吐量是Tt，那么分区数 = Tt / min（Tp，Tc）

例如：
producer吞吐量 = 20m/s；
consumer吞吐量 = 50m/s，
期望吞吐量100m/s；
分区数 = 100 / 20 = 5分区
分区数一般设置为：`3-10`个