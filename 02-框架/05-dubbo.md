## 负载均衡

## FailoverCluster 失效转移
当消费端发起一次调用，如果集群容错模式选择的是FailoverCluster模式(缺省模式)，当调用发生失败会自动发起切换，重试其它服务器。