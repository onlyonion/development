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
## check
```sh
### 整机
top -Hp # 查看线程
dstat # 全能实时系统信息统计

### 进程
ps
vmstat #进程、虚拟内存、页面交换、IO读写、CPU活动率
mpstat
pidstat

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
lsof -i | grep 8080
```

### 分析工具
* strace 诊断、调试程序的系统调用
* GDB 程序调试、coredump分析
* lsof 查看系统当前打开的文件信息
* tcpdump 网络抓包工具
* traceroute 网络路由分析工具

## troubleshoot 故障排查
* cpu消耗
* 内存消耗
* 文件IO消耗
* 网络IO消耗
* 应用慢

### cpu占用过高
结合linux和jdk命令一起分析
1. top命令找出cpu占比最高的
2. ps -ef 或者 jps进一步定位，得志是一个怎么样的后台程序
3. 定位到具体的**线程或代码**; `ps -mp 进程 -o THREAD,tid,time` 或 `ps -mp pid -o THREAD,tid,time | sort -rn `
4. 将需要的线程ID转换为16进制格式 `printf "%x\n" tid` 十进制转换成十六进制
5. jstack 进程id | grep tid（16进制线程ID小写英文） -A 60 （打印前60行）  `jstack pid | grep tid -A 60`


[Java线上应用故障排查之一：高CPU占用](https://blog.csdn.net/blade2001/article/details/9065985)

如果该问题导致线上系统不可用，那么首先需要做的就是，导出 jstack 和内存信息，然后重启系统，尽快保证系统的可用性。

CPU 过高可能是系统频繁的进行Full GC、代码死循环、代码死锁

### 频繁Full GC
GC日志；使用jstat -gcutil 5280 1000查看实时GC情况；

可能是jvm参数设置不合理，老年代空间不足（扩容空间）、Metaspace空间触发、频繁创建对象触发等等。

代码中某个位置读取数据量较大，导致内存耗尽，从而FullGC。

jstat 命令监控 GC 情况，可以看到 Full GC 次数非常多，并且次数在不断增加


## book
* [《分布式Java应用 基础与实践》林昊 电子工业出版社](/docs/99-book/notes/30-distributed/分布式Java应用.md)
* [《逆流而上 阿里巴巴技术成长之路》阿里巴巴集团成长集编委会](/docs/99-book/notes/40-architecture/逆流而上.md)