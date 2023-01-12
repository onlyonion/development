
## es
https://blog.csdn.net/u011863024/article/details/115721328
```sh
# 解压缩
tar -zxvf elasticsearch-7.8.0-linux-x86_64.tar.gz -C /opt/module
# 改名
mv elasticsearch-7.8.0 es

useradd es #新增 es 用户
passwd es #为 es 用户设置密码
userdel -r es #如果错了，可以删除再加
chown -R es:es /opt/module/es #文件夹所有者

# 修改/opt/module/es/config/elasticsearch.yml文件 加入如下配置
cluster.name: elasticsearch
node.name: node-1
network.host: 0.0.0.0
http.port: 9200
cluster.initial_master_nodes: ["node-1"]

# 修改/etc/security/limits.conf 在文件末尾中增加下面内容
# 每个进程可以打开的文件数的限制
es soft nofile 65536
es hard nofile 65536

* soft nofile 65536
* hard nofile 65536
* soft core unlimited
* hard core unlimited

# 修改/etc/security/limits.d/20-nproc.conf 在文件末尾中增加下面内容
# 每个进程可以打开的文件数的限制
es soft nofile 65536
es hard nofile 65536
# 操作系统级别对每个用户创建的进程数的限制
* hard nproc 4096
# 注： * 带表 Linux 所有用户名称

# 修改/etc/sysctl.conf 在文件中增加下面内容
# 一个进程可以拥有的 VMA(虚拟内存区域)的数量,默认值为 65536
vm.max_map_count=655360

# 重新加载
sysctl -p

# 启动es
cd /opt/module/es/
#启动
bin/elasticsearch
#后台启动
bin/elasticsearch -d  


#暂时关闭防火墙
systemctl stop firewalld
#永久关闭防火墙
systemctl enable firewalld.service #打开防火墙永久性生效，重启后不会复原
systemctl disable firewalld.service #关闭防火墙，永久性生效，重启后不会复原

```

su es
cd /home/es/es
bin/elasticsearch -Ehttp.port=9201 -Epath.data=/home/es/es/data/node1_data -Epath.logs=/home/es/es/logs/node1_logs -Enode.name=node-1 -d
bin/elasticsearch -Ehttp.port=9202 -Epath.data=/home/es/es/data/node2_data -Epath.logs=/home/es/es/logs/node2_logs -Enode.name=node-2 -d
bin/elasticsearch -Ehttp.port=9203 -Epath.data=/home/es/es/data/node3_data -Epath.logs=/home/es/es/logs/node3_logs -Enode.name=node-3 -d


```conf
# 加入如下配置
#集群名称
cluster.name: cluster-es
#节点名称， 每个节点的名称不能重复
node.name: node-1
#ip 地址， 每个节点的地址不能重复
network.host: linux1
#是不是有资格主节点
node.master: true
node.data: true
http.port: 9200
# head 插件需要这打开这两个配置
http.cors.allow-origin: "*"
http.cors.enabled: true
http.max_content_length: 200mb
#es7.x 之后新增的配置，初始化一个新的集群时需要此配置来选举 master
cluster.initial_master_nodes: ["node-1"]
#es7.x 之后新增的配置，节点发现
discovery.seed_hosts: ["linux1:9300","linux2:9300","linux3:9300"]
gateway.recover_after_nodes: 2
network.tcp.keep_alive: true
network.tcp.no_delay: true
transport.tcp.compress: true
#集群内同时启动的数据任务个数，默认是 2 个
cluster.routing.allocation.cluster_concurrent_rebalance: 16
#添加或删除节点及负载均衡时并发恢复的线程个数，默认 4 个
cluster.routing.allocation.node_concurrent_recoveries: 16
#初始化数据恢复时，并发恢复线程的个数，默认 4 个
cluster.routing.allocation.node_initial_primaries_recoveries: 16
```
