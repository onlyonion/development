# linux cmd

* printf
* top
* version
* proc
* nohup

## 0. printf
```sh
# 进制转换 十六进制和十进制互相转换都没问题
# 十进制显示
$ printf %d 0xac
172
# 十六进制显示
$ printf %x 172
ac 
```

## 1. top
概念：top命令是Linux下常用的系统性能分析工具，能实时查看系统中各个进程资源占用情况。

```
参数：
	-p	指定进程号
		eg：top -p PID	查看进程ID为PID的进程
	-H	查看各个线程资源占用的情况。
		eg：top			查看各个进程 资源占用的情况。
			top	-H 		查看各个线程 资源占用的情况。
			top -Hp	PID	查看进程ID为PID的进程中 所有线程 的资源占用情况。
			
	-c	查看进程的完整命令(COMMAND)
		eg：top -c		查看各个进程 资源占用的情况，其中命令(COMMAND)项显示详细信息。
		
	-n	更新n次后，退出top命令。
		eg：top -n 5	更新5次后，停止更新并退出top命令。
```

```
    PID		进程号
	USER	运行用户
	PR		优先级，说明：PR其实就是进程调度器分配给进程的时间片长度，单位是时钟个数，linux下一个时钟约为10ms，PR值为20则表示这个进程的时间片为200ms
	NI		任务nice值，代表这个进程的优先值
	VIRT	进程使用的虚拟内存总量，单位kb。VIRT=SWAP+RES
	RES		物理内存用量
	SHR		共享内存用量
	S		进程的状态。
				S	睡眠状态
				D	不可中断的睡眠状态
				R	运行状态
				Z	僵死状态
				T	停止或跟踪状态
	%CPU	从上一次刷新到现在，该进程占用cpu时间和总时间的百分比
	%MEM	占用的物理内存与总内存的百分比
	TIME+	累计cpu占用时间
	COMMAND	该进程的命令名称
```
[top命令](https://blog.csdn.net/wodewutai17quiet/article/details/78187567 )

### VIRT RES SHR DATA
VIRT：virtual memory usage 虚拟内存
1、进程“需要的”虚拟内存大小，包括进程使用的库、代码、数据等
2、假如进程申请100m的内存，但实际只使用了10m，那么它会增长100m，而不是实际的使用量

RES：resident memory usage 常驻内存
1、进程当前使用的内存大小，但不包括swap out
2、包含其他进程的共享
3、如果申请100m的内存，实际使用10m，它只增长10m，与VIRT相反
4、关于库占用内存的情况，它只统计加载的库文件所占内存大小

SHR：shared memory 共享内存
1、除了自身进程的共享内存，也包括其他进程的共享内存
2、虽然进程只使用了几个共享库的函数，但它包含了整个共享库的大小
3、计算某个进程所占的物理内存大小公式：RES – SHR
4、swap out后，它将会降下来

DATA
1、数据占用的内存。如果top没有显示，按f键可以显示出来。
2、真正的该程序要求的数据空间，是真正在运行中要使用的。

top 运行中可以通过 top 的内部命令对进程的显示方式进行控制。内部命令如下：
s – 改变画面更新频率
l – 关闭或开启第一部分第一行 top 信息的表示
t – 关闭或开启第一部分第二行 Tasks 和第三行 Cpus 信息的表示
m – 关闭或开启第一部分第四行 Mem 和 第五行 Swap 信息的表示
N – 以 PID 的大小的顺序排列表示进程列表
P – 以 CPU 占用率大小的顺序排列进程列表
M – 以内存占用率大小的顺序排列进程列表
h – 显示帮助
n – 设置在进程列表所显示进程的数量
q – 退出 top
s – 改变画面更新周期
[linux top命令VIRT,RES,SHR,DATA的含义](https://javawind.net/p131)

## 2. version

* uname -a   （Linux查看版本当前操作系统内核信息）
* cat /proc/version （Linux查看当前操作系统版本信息）
* cat /etc/issue  或cat /etc/redhat-release（Linux查看版本当前操作系统发行版信息）
* cat /proc/cpuinfo （Linux查看cpu相关信息，包括型号、主频、内核信息等）
* getconf LONG_BIT  （Linux查看版本说明当前CPU运行在32bit模式下， 但不代表CPU不支持64bit）
* lsb_release -a

* cat /proc/meminfo
* free -h
* htop

## 3. proc 存储的是当前内核运行状态的一系列特殊文件

```sh
/proc/N # 存储系统当前正在运行的进程的相关信息，其中N为正在运行的进程
/proc/sys/net/core/
/proc/sys/net/ipv4/tcp_max_syn_backlog  # tcp backlog
/proc/cmdline # 在启动时传递至内核的相关参数信息，这些信息通常由lilo或grub等启动管理工具进行传递
/proc/cpuinfo # 处理器的相关信息的文件
/proc/meminfo 
/proc/devices 
/proc/diskstats 
/proc/scsi
```

## 4 nohup
https://www.cnblogs.com/baby123/p/6477429.html

## 5 ulimit
查看用户限制

数据段长度：ulimit -d unlimited   
最大内存大小：ulimit -m unlimited   
堆栈大小：ulimit -s unlimited   
CPU 时间：ulimit -t unlimited   
虚拟内存：ulimit -v unlimited   


```sh
# 解除 Linux 系统的最大进程数和最大文件打开数限制：
vi /etc/security/limits.conf
# 添加如下的行 * 代表针对所有用户 noproc 是代表最大进程数 nofile 是代表最大文件打开数 
* soft noproc 11000
* hard noproc 11000
* soft nofile 4100
* hard nofile 4100 
sysctl -p # 设置配置永久生效
```