# docker

## ms -> cloud
* microservice
* openstack
* Kubernetes [kubə'nætis], [kubə'nætris] 技术平台，编排工具


[docker](https://www.jianshu.com/p/2a9ae69c337d)

[Docker教程-菜鸟教程](https://www.runoob.com/docker/docker-tutorial.html)

## Docker
Docker 是一个开源的应用容器引擎，基于 Go 语言 并遵从Apache2.0协议开源。

Docker 可以让开发者打包他们的应用以及依赖包到一个**轻量级**、**可移植**的容器中，
然后发布到任何流行的 Linux 机器上，也可以实现虚拟化。


Docker 架构
- Docker 使用客户端-服务器 (C/S) 架构模式，使用远程API来管理和创建Docker容器。
- Docker 容器通过 Docker 镜像来创建。
- 容器与镜像的关系类似于面向对象编程中的对象与类。

## Docker Compose
compose 组成；创作；构成；作曲

## Docker Swarm
Docker Swarm 是一款用来管理多主机上的Docker容器的工具，可以负责帮你启动容器，监控容器状态，
如果容器的状态不正常它会帮你重新帮你启动一个新的容器，来提供服务，同时也提供服务之间的负载均衡，而这些东西Docker-Compose 是做不到的

## Kubernetes
Kubernetes是一个开源的，用于管理云平台中多个主机上的容器化的应用，Kubernetes的目标是让部署容器化的
应用简单并且高效（powerful）,Kubernetes提供了应用部署，规划，更新，维护的一种机制。

Kubernetes对计算资源进行了更高层次的抽象，通过将容器进行细致的组合，将最终的应用服务交给用户。

Docker是容器技术的核心、基础，Docker Compose是一个基于Docker的单主机容器编排工具.而k8s是一个跨主机的集群部署工具。