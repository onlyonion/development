## 1. 操作系统引论

### 1.3 操作系统的基本特征

* 并发性
* 共享性
* 虚拟技术
* 异步性

### 1.4 操心系统的主要功能

* 处理机管理功能            cpu
* 存储器管理功能            memory
* 设备管理功能              input/output
* 文件管理功能              filesystem
* 操作系统与用户之间的接口

## 2. 进程管理

## 3. 处理机调度与死锁

### 3.3 调度算法

* 先来先服务和短作业优先调度算法
* 高优先权优先的轮转调度算法
* 基于时间片的轮转调度算法

## 4. 存储器管理

### 4.1 存储器的层次结构
* 寄存器        cpu
* 高速缓存      
* 主存
* 磁盘缓存
* 磁盘
* 可移动存储介质

### 4.2 程序的装入和链接

source --> compile --> link --> load

* loading Absolute Loading Mode; Reloaction Loading Mode; Dynamic Run-time Loading
* linking Static Linking; Load-time Dynamic Linking; Run-time Dynamic Linking


### 4.3 连续分配方式
### 4.4 基本分页存储管理的方式
### 4.5 基本分段存储管理的方式
### 4.6 虚拟存储器的基本概念
### 4.7 请求分页存储管理方式
### 4.8 页面置换算法
### 4.9 请求分段存储管理方式

## 5. 设备管理

### 5.1 IO系统

#### 5.1.4 总线系统

> 总线的性能是用总线的时钟频率、带看和相应的传输速率等指标来衡量的。

### 5.2 IO控制方式

### 5.3 缓冲管理

### 5.4 IO软件

### 5.5 设备分配

### 5.6 磁盘存储器的管理

## 6. 文件管理

### 6.1 文件和文件系统

* 数据项、数据类型
* 记录
* 文件
* 文件类型
* 文件系统模型

### 6.2 文件的逻辑结构

### 6.3 外存分配方式

* 连续分配
* 链接分配
* 索引分配

### 6.4 目录管理

### 6.5 文件存储空间的管理

### 6.6 文件共享与文件保护

### 6.7 数据一致性控制

## 7. 操作系统接口

### 7.1 联机用户接口

### 7.2 Shell命令语言

### 7.3 系统调用

### 7.4 Unix系统调用

#### 7.4.1 unix系统调用类型

* 进程控制      fork, exit, wait, exec, getp-id, getpgrp, getppid, geteuid, pause
* 文件操作      create, open, close, read, write(fd, buf, nByte), link, unlink
* 进程间通信    消息机制msgget, msgrcv; 共享内存shmget; 信号量机制
* 信息维护      stime, times, utime, uname

### 7.5 图形用户接口

## 8. 网络操作系统
## 9. 系统安全性
## 10. Unix系统内核结构