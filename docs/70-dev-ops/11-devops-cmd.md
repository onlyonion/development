
linux

## linux
### 整机
* top
* dstat 全能实时系统信息统计

### 进程
* ps
* vmstat 进程、虚拟内存、页面交换、IO读写、CPU活动率
* mpstat
* pidstat

### 内存
* free -m

cat /proc/meminfo

### 磁盘
* iostat 系统io状态信息 `磁盘IO iostat -xdk 2 3 每2秒采样，3次`
* iotop
* df -h

### 网络
* ifstat 实时网络流量监控
* iftop 
* netstat 查看网络相关信息、各种网络协议套接字状态
* sar

```sh
netstat -nap | grep 2682 # 根据进程ID查找端口
netstat -nap | grep 8080 # 根据端口查找进程ID
```

### 分析工具
* strace 诊断、调试程序的系统调用
* GDB 程序调试、coredump分析
* lsof 查看系统当前打开的文件信息
* tcpdump 网络抓包工具
* traceroute 网络路由分析工具

### 常用命令
* awk
* grep
* less
* tail

## jdk bin
* jps
* jstack pid > pid.log
* jmap 

## git
* merge 提交commit合并修改
* rebase 修改提交历史记录 
