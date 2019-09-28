
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
5. jstack 进程id | grep tid（16进制线程ID小写英文） -A60 （打印前60行）

[Java线上应用故障排查之一：高CPU占用【转】](http://www.linuxhot.com/java-cpu-used-high.html)

## book
* [《分布式Java应用 基础与实践》林昊 电子工业出版社](/docs/99-book/notes/30-distributed/分布式Java应用.md)
* [《逆流而上 阿里巴巴技术成长之路》阿里巴巴集团成长集编委会](/docs/99-book/notes/40-architecture/逆流而上.md)