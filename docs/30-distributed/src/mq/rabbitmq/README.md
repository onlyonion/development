# rabbitmq

- erlang
- AMQP 高级消息队列协议


```sh
# linux
vim etc/hostname

# rabbitmq
rabbitmq-server start &
rabbitmqctl stop_app
rabbitmqctl start_app

# plugin 
rabbitmq-plugins enable rabbitmq_management
rabbitmq-plugins list

# rabbitmqctl
rabbitmqctl status
rabbitmqctl add_user username password
rabbitmqctl list_users
rabbitmqctl set_permissions -p vhostpath
rabbitmqctl list_vhosts
rabbitmqctl list_queues
rabbitmqctl -p vhostpath purge_queue blue

# cluster
rabbitmqctl join_cluster <clusternode> [--ram]  ram内存存储
rabbitmqctl cluster_status
rabbitmqctl change_cluster_node_type disc | ram 修改集群节点的存储模式
rabbitmqctl forget_cluster_node [--offline] 集群节点下线

```

通信端口
- 5672 通信端口
- 15672 管控台通信端口
- 25672 集群通信端口