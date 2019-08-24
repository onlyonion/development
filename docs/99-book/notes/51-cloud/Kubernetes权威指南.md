《Kubernetes权威指南：从Docker到Kubernetes实践全接触》 龚正 吴治辉 王伟 崔秀龙 闫建勇 等编著 电子工业出版社

Kubernetes是由谷歌开源的Docker容器集群管理系统，为容器化的应用提供了资源调度、部署运行、服务发现、扩容及缩容等一整套功能。

## 第1章 Kubernetes 入门
### 1.1 Kubernetes 是什么
- 一个全新的基于容器技术的分布式架构领先方案。Borg的一个开源版本。
- 开放的开发平台。不限定任何编程接口，不同语言编写的服务，映射为Kubernetes的Service，通过标准TCP协议进行交换。
- 一个完备的分布式系统支撑平台。

在Kubernetes中，Service（服务）是分布式集群架构的核心，关键特征
- 拥有一个唯一指定的名字
- 拥有一个虚拟IP和端口号
- 能够提供某种远程服务能力
- 被映射到了提供这种服务能里的一组容器应用上

Service的服务进程目前都是基于Socket通信方式对外提供服务。
容器提供了强大的隔离功能。Kubernetes设计了Pod对象，将没给服务进程包装到相应的Pod中，使其成为Pod中运行的一个容器。
在集群管理方法，Kubernetes将集群中的机器划分为一个Master节点和一群工作节点（Node）。

### 1.2 为什么要用Kubernetes
使用Kubernetes的理由有很多，最根本的一个理由就是：IT从来都是由一个新技术驱动的行业。

好处：
- 轻松开发复杂系统
- 全面拥抱微服务架构
- 可以随时整体迁移到公有云
- 横向扩容能力

### 1.3 从一个简单的例子开始
### 1.4 Kubernetes 基本概念和术语
1. Master 集群控制节点
   - kube-apiserver
   - kube-controller-manager
   - kube-shcedule
   - etcd
2. Node
   - kubelet 负载Pod对应的容器的创建、启停等任务，同时与Master节点密切协作
   - kube-proxy 实现KubernetesService的通信与负载均衡机制
   - Docker Engine
3. Pod
4. Label
5. Replication Controller（RC）
6. Deployment
7. Horizontal Pod Autoscaler(HPA)
8. Service（服务）
9.  Volumne（存储卷）
10. Persistent Volume
11. Namespace（命名空间）
12. Antotation（注解）

## 第2章 Kubernetes 实践指南 

## 第3章 Kubernetes 核心原理
### 3.1 Kubernetes API Server 原理分析
### 3.2 Controller Manager 原理分析
### 3.3 Scheduler 原理分析
### 3.4 kubelet 运行机制分析
### 3.5 kube-proxy 运行机制分析
### 3.6 深入分析集群安全机制
### 3.7 网络原理

## 第4章 Kubernetes 开发指南
### 4.1 REST 简述
### 4.2 Kubernetes API 详解
### 4.3 使用Java 程序访问Kubernetes API
jersey是一个RESTful请求服务Java框架。

## 第5章 Kubernetes 运维指南
### 5.1 Kubernetes 集群管理指南
### 5.2 Kubernetes 高级案例
### 5.3 Trouble Shooting 指导
### 5.4 Kubernetes v1.3 开发中的新功能

## 第6章 Kubernetes 源码导读
LiteIDE
