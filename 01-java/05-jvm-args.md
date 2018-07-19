

## 1. 堆大小设置


## 2. 回收器选择

JVM给了三种选择：串行收集器、并行收集器、并发收集器，但是串行收集器只适用于小数据量的情况，所以这里的选择主要针对并行收集器和并发收集器。
默认情况下，JDK5.0以前都是使用串行收集器，如果想使用其他收集器需要在启动时加入相应参数。JDK5.0以后，JVM会根据当前系统配置进行判断。

1.吞吐量优先的并行收集器
并行收集器主要以到达一定的吞吐量为目标，适用于科学技术和后台处理等。

典型配置：

	java -Xmx3800m -Xms3800m -Xmn2g -Xss128k -XX:+UseParallelGC -XX:ParallelGCThreads=20
	-XX:+UseParallelGC：选择垃圾收集器为并行收集器。此配置仅对年轻代有效。即上述配置下，年轻代使用并发收集，而年老代仍旧使用串行收集。
	-XX:ParallelGCThreads=20：配置并行收集器的线程数，即：同时多少个线程一起进行垃圾回收。此值最好配置与处理器数目相等。

	java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:+UseParallelGC -XX:ParallelGCThreads=20 -XX:+UseParallelOldGC
	-XX:+UseParallelOldGC：配置年老代垃圾收集方式为并行收集。JDK6.0支持对年老代并行收集。

	java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:+UseParallelGC  -XX:MaxGCPauseMillis=100
	-XX:MaxGCPauseMillis=100:设置每次年轻代垃圾回收的最长时间，如果无法满足此时间，JVM会自动调整年轻代大小，以满足此值。

	java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:+UseParallelGC  -XX:MaxGCPauseMillis=100 -XX:+UseAdaptiveSizePolicy
	-XX:+UseAdaptiveSizePolicy：设置此选项后，并行收集器会自动选择年轻代区大小和相应的Survivor区比例，以达到目标系统规定的最低相应时间或者收集频率等，此值建议使用并行收集器时，一直打开。


2.响应时间优先的并发收集器
并发收集器主要是保证系统的响应时间，减少垃圾收集时的停顿时间。适用于应用服务器、电信领域等。
典型配置：

	java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:ParallelGCThreads=20 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC
	-XX:+UseConcMarkSweepGC：设置年老代为并发收集。测试中配置这个以后，-XX:NewRatio=4的配置失效了，原因不明。所以，此时年轻代大小最好用-Xmn设置。
	-XX:+UseParNewGC:设置年轻代为并行收集。可与CMS收集同时使用。JDK5.0以上，JVM会根据系统配置自行设置，所以无需再设置此值。

	java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:+UseConcMarkSweepGC -XX:CMSFullGCsBeforeCompaction=5 -XX:+UseCMSCompactAtFullCollection
	-XX:CMSFullGCsBeforeCompaction：由于并发收集器不对内存空间进行压缩、整理，所以运行一段时间以后会产生“碎片”，使得运行效率降低。此值设置运行多少次GC以后对内存空间进行压缩、整理。
	-XX:+UseCMSCompactAtFullCollection：打开对年老代的压缩。可能会影响性能，但是可以消除碎片

## 3. 辅助信息


## 4. 常见配置汇总 

1.堆设置

	-Xms:初始堆大小
	-Xmx:最大堆大小
	-XX:NewSize=n:设置年轻代大小
	-XX:NewRatio=n:设置年轻代和年老代的比值。如:为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代年老代和的1/4
	-XX:SurvivorRatio=n:年轻代中Eden区与两个Survivor区的比值。注意Survivor区有两个。如：3，表示Eden：Survivor=3：2，一个Survivor区占整个年轻代的1/5
	-XX:MaxPermSize=n:设置持久代大小
	
	-XX:NewRatio
	- 新生代（eden + 2 * s）和老年代（不包含永久代）的比值
	- 4 表示 新生代：老年代 = 1:4，即年轻代占堆的1/5
	
	-XX:SurvivorRatio
	- 设置两个Survivor区和eden的比
	- 8 表示两个Survivor：eden = 2:8，即一个Survivor占年轻代的1/10

2.收集器设置 

	-XX:+UseSerialGC:设置串行收集器
	-XX:+UseParallelGC:设置并行收集器
	-XX:+UseParalledlOldGC:设置并行年老代收集器
	-XX:+UseConcMarkSweepGC:设置并发收集器

3.垃圾回收统计信息 

	-XX:+PrintGC
	-XX:+PrintGCDetails
	-XX:+PrintGCTimeStamps
	-Xloggc:filename

4.并行收集器设置 

	-XX:ParallelGCThreads=n:设置并行收集器收集时使用的CPU数。并行收集线程数。
	-XX:MaxGCPauseMillis=n:设置并行收集最大暂停时间
	-XX:GCTimeRatio=n:设置垃圾回收时间占程序运行时间的百分比。公式为1/(1+n)

5.并发收集器设置 

	-XX:+CMSIncrementalMode:设置为增量模式。适用于单CPU情况。
	-XX:ParallelGCThreads=n:设置并发收集器年轻代收集方式为并行收集时，使用的CPU数。并行收集线程数。

## 5. 调优总结

1.年轻代大小选择

* 响应时间优先的应用：尽可能设大，直到接近系统的最低响应时间限制（根据实际情况选择）。在此种情况下，年轻代收集发生的频率也是最小的。同时，减少到达年老代的对象。
* 吞吐量优先的应用：尽可能的设置大，可能到达Gbit的程度。因为对响应时间没有要求，垃圾收集可以并行进行，一般适合8CPU以上的应用。

2.年老代大小选择

* 响应时间优先的应用：年老代使用并发收集器，所以其大小需要小心设置，一般要考虑并发会话率和会话持续时间等一些参数。
如果堆设置小了，可以会造成内存碎片、高回收频率以及应用暂停而使用传统的标记清除方式；
如果堆大了，则需要较长的收集时间。最优化的方案，一般需要参考以下数据获得：
	1.并发垃圾收集信息
	2.持久代并发收集次数
	3.传统GC信息
	4.花在年轻代和年老代回收上的时间比例
	5.减少年轻代和年老代花费的时间，一般会提高应用的效率
	
* 吞吐量优先的应用：一般吞吐量优先的应用都有一个很大的年轻代和一个较小的年老代。原因是，这样可以尽可能回收掉大部分短期对象，减少中期的对象，而年老代尽存放长期存活对象。

3.较小堆引起的碎片问题
因为年老代的并发收集器使用标记、清除算法，所以不会对堆进行压缩。
	
	-XX:+UseCMSCompactAtFullCollection：使用并发收集器时，开启对年老代的压缩。
	-XX:CMSFullGCsBeforeCompaction=0：上面配置开启的情况下，这里设置多少次Full GC后，对年老代进行压缩

[JVM参数配置详解](https://blog.csdn.net/yjl33/article/details/78890363) 


