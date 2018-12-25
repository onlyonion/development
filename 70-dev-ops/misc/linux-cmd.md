### top
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