《Spring Cloud与Docker微服务架构实战》周立

# 前言
# SpringCloud
## 1 微服务架构概述
微服务架构风格是一种将单一应用程序开发为一组小型服务的方法，每个服务运行在自己的进程中，服务间通信采用轻量级通信机制。
这些服务围绕业务能力构建并且可通过全自动部署机制独立部署。

微服务设计原则
- 单一职责
- 服务自治
- 轻量级通信机制
- 微服务粒度

## 2 微服务开发框架 -- SpringCloud
云原生可简单理解为面向云环境的软件架构。

## 3 开始使用SpringCloud实战微服务
## 4 微服务注册与发现
## 5 使用Ribbon实现客户端负载均衡
## 6 使用Feign实现声明式REST调用
## 7 使用Hystrix实现微服务的容错处理

雪崩效应，基础服务故障导致级联故障的现象，提供者不可用导致消费者不可用，并将不可用逐渐放大的过程。

- 为网络设置超时
- 使用断路器，对导致错误的操作的代理，实现快速失败

Hystrix实现延迟与容错
- 包裹请求
- 跳闸机制
- 资源隔离 为每个依赖维护一个小型的线程池（或者信号量），如果线程池已满，立即拒绝，快速失败
- 监控
- 回退机制 回退逻辑可自定义
- 自我修复

Hystrix的隔离策略有两种：
- THREAD 线程隔离 单独的线程上执行，并发请求受线程池中的线程数量限制
- SEMAPHORE 信号量隔离 开销相对小，并发请求受到信号量个数限制

## 8 使用Zuul构建微服务网络

### 8.11 使用Sidecar整合非JVM微服务

## 9 使用SpringCloudConfig统一管理微服务配置

## 10 使用SpringCloudSleuth实现微服务跟踪
SpringCloudSleuth为SpringCloud提供了分布式跟踪的解决方案，它打了借用了GoogleDapper、Twitter Zipkin和Apache HTrace的设计。
## 11 SpringCloud常见问题与汇总

# Docker
## 12 Docker入门
### 12.1 Docker简介
开源的容器引起，有助于更快地交付应用。Docker将应用程序和基础设施层隔离，并且能将基础设施层当作程序一样进行管理。
### 12.2 Docker的架构
- Docker daemon
- Client
- Image
- Container
- Registry

### 12.5 Docker的常用命令
- Docker镜像的常用命令
- Docker容器的常用命令


## 13 将微服务运行在Docker上

```sh
docker build -t nginx:my .
docker run -d -p 92:80 nginx:my
# ADD 复制文件
# ARG 构建参数
ARG user1=someuser
# CMD 容器启动命令，每个dockerfile只有一个cmd命令，指定了多个，只有最后一个会被执行
# COPY 复制文件
# ENTRYPOINT
# ENV 设置环境变量
# EXPOSE 暴露的端口
# FROM 指定基础镜像
# LABEL
# MAINTAINER maintainer
# RUN 执行命令
# USER 设置用户
# VOLUME 指定挂载点
# WORKER指定工作目录
```

## 14 使用DockerCompose编排微服务