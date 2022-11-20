check linux

## base
```sh
# 解压缩
# windows *.tgz
tar -czvf test.tar.gz a.c # 压缩
tar -xzvf test.tar.gz # 解压
unzip test.zip # 解压
unzip -d /temp test.zip # 指定目录

# yum install lrzsz
# 复制文件夹到文件夹
cp -r 
```
## check top
```sh
### 整机
top -H # 查看线程
top -p {pid} # 查看指定进程id的top信息
top -H -p {pid} # 查看指定进程id的所有线程的top信息
top -Hp {pid}
dstat # 全能实时系统信息统计

### 进程
ps
vmstat #进程、虚拟内存、页面交换、IO读写、CPU活动率
mpstat
pidstat
cat /proc/cpuinfo
cat /proc/cpuinfo| grep “physical id”| sort| uniq| wc -l # 查看物理CPU个数
cat /proc/cpuinfo| grep “cpu cores”| uniq # 查看每个物理CPU中core的个数(即核数)
cat /proc/cpuinfo| grep “processor”| wc -l # 查看逻辑CPU的个数

### 内存
free -m
cat /proc/meminfo

### 磁盘
ostat #系统io状态信息 `磁盘IO iostat -xdk 2 3 每2秒采样，3次`
iotop
df -h

### 网络
ifstat #实时网络流量监控
iftop 
netstat #查看网络相关信息、各种网络协议套接字状态
sar

netstat -nap | grep 2682 # 根据进程ID查找端口

# 根据端口查找进程ID
netstat -nap | grep 8080 
netstat -nlp | grep 8080 
# tcp        0      0 0.0.0.0:2182            0.0.0.0:*               LISTEN      707790/java
lsof -i | grep 8080
```


| cmd          | note                                      |
| :----------- | :---------------------------------------- |
| vmstat       | 进程、虚拟内存、页面交换、IO读写、CPU活动 |
| iostat iotop | 系统IO状态                                |
| ifstat iftop | 实时网络流量监控                          |
| netstat      | 网络协议相关                              |
| dstat        | 全能实时系统信息统计                      |
| strace       |                                           |
| GDB          | 程序调试、coredump分析                    |
| lsof         | 查看系统当前打开的文件信息                |
| tcpdump      | 网络抓包工具                              |
| traceroute   | 网络路由分析工具                          |