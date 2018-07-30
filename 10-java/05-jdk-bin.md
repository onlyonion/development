
jps、jstack、jmap、jhat、jstat、hprof、jinfo

ps、top、printf、grep

## 1. jps 
Java Virtual Machine Process Status Tool
jps主要用来输出JVM中运行的进程状态信息

语法格式：

	jps [options] [hostid]


如果不指定hostid就默认为当前主机或服务器。
命令行参数选项：
    
```

-q 不输出类名、Jar名和传入main方法的参数
-m 输出传入main方法的参数
-l 输出main类或Jar的全限名
-v 输出传入JVM的参数

```


##	2. jstack
jstack主要用来查看某个Java进程内的线程堆栈信息

语法格式：

	jstack [option] pid
	jstack [option] executable core
	jstack [option] [server-id@]remote-hostname-or-ip

命令行参数选项：

	-l long listings，会打印出额外的锁信息，在发生死锁时可以用jstack -l pid来观察锁持有情况
	-m mixed mode，不仅会输出Java堆栈信息，还会输出C/C++堆栈信息（比如Native方法）

jstack输出的线程信息主要包括	

1.	jvm自身线程
	JVM内部的后台线程，来执行譬如垃圾回收，或者低内存的检测等等任务
2.	用户级别的线程


## 3. jmap（Memory Map）和jhat（Java Heap Analysis Tool）

jmap用来查看堆内存使用状况，一般结合jhat使用。
jmap语法格式如下：

	jmap [option] pid
	jmap [option] executable core
	jmap [option] [server-id@]remote-hostname-or-ip
	
如果运行在64位JVM上，可能需要指定-J-d64命令选项参数。

	jmap -permstat pid
	
打印进程的类加载器和类加载器加载的持久代对象信息，输出：类加载器名称、对象是否存活（不可靠）、对象地址、父类加载器、已加载的类大小等信息	

>	jmap -heap pid 查看进程堆内存使用情况，包括使用的GC算法、堆配置参数和各代中堆内存使用情况
>	jmap -histo[:live] pid查看堆内存中的对象数目、大小统计直方图，如果带上live则只统计活对象
>	jmap -histo:live 21711 | more


用jmap把进程内存使用情况dump到文件中，再用jhat分析查看。jmap进行dump命令格式如下：

	jmap -dump:format=b,file=dumpFileName pid
	jmap -dump:live,format=b,file=heap.dmp PID
	
dump出来的文件可以用MAT、VisualVM等工具查看，这里用jhat查看：

	jhat -port 9998 /tmp/dump.dat
	
jhat通过web浏览器具体分析内容中的对象和数据
jhat是一个Java堆复制浏览器
	
## 4. jstat（JVM统计监测工具）

	jstat [ generalOption | outputOptions vmid [interval[s|ms] [count]] ]
	
vmid是Java虚拟机ID，在Linux/Unix系统上一般就是进程ID。interval是采样时间间隔。count是采样数目。比如下面输出的是GC信息，采样时间间隔为250ms，采样数为4：

	jstat -gc 21711 250 4
	jstat -gc  pid 1000
	
可以看出：
堆内存 = 年轻代 + 年老代 + 永久代
年轻代 = Eden区 + 两个Survivor区（From和To）

现在来解释各列含义：
S0C、S1C、S0U、S1U：Survivor 0/1区容量（Capacity）和使用量（Used）
EC、EU：Eden区容量和使用量
OC、OU：年老代容量和使用量
PC、PU：永久代容量和使用量
YGC、YGT：年轻代GC次数和GC耗时
FGC、FGCT：Full GC次数和Full GC耗时
GCT：GC总耗时

CCSMN：最小压缩类空间大小
CCSMX：最大压缩类空间大小
CCSC：当前压缩类空间大小



## 5. hprof（Heap/CPU Profiling Tool）
hprof能够展现CPU使用率，统计堆内存使用情况
	
## 6.jinfo
查看系统启动参数


## jvisualvm.exe 在JDK 的 bin 目录下

## jconsole
JConsole是一个基于JMX的GUI工具，用于连接正在运行的JVM，不过此JVM需要使用可管理的模式启动
如果要把一个应用以可管理的形式启动，可以在启动时设置com.sun.management.jmxremote。

## jmx
JMX（Java Management Extensions，即Java管理扩展）是一个为应用程序、设备、系统等植入管理功能的框架。
JMX可以跨越一系列异构操作系统平台、系统体系结构和网络传输协议，灵活的开发无缝集成的系统、网络和服务管理应用。



## java

[java命令行参数](https://www.cnblogs.com/princessd8251/articles/4025140.html) 

```shell
java -?
Usage: java [-options] class [args...]
           (to execute a class)
   or  java [-options] -jar jarfile [args...]
           (to execute a jar file)
where options include:
    -d32	  use a 32-bit data model if available
    -d64	  use a 64-bit data model if available
    -server	  to select the "server" VM
                  The default VM is server,
                  because you are running on a server-class machine.


    -cp <class search path of directories and zip/jar files>
    -classpath <class search path of directories and zip/jar files>
                  A : separated list of directories, JAR archives,
                  and ZIP archives to search for class files.
    -D<name>=<value>
                  set a system property
    -verbose:[class|gc|jni]
                  enable verbose output
    -version      print product version and exit
    -version:<value>
                  Warning: this feature is deprecated and will be removed
                  in a future release.
                  require the specified version to run
    -showversion  print product version and continue
    -jre-restrict-search | -no-jre-restrict-search
                  Warning: this feature is deprecated and will be removed
                  in a future release.
                  include/exclude user private JREs in the version search
    -? -help      print this help message
    -X            print help on non-standard options
    -ea[:<packagename>...|:<classname>]
    -enableassertions[:<packagename>...|:<classname>]
                  enable assertions with specified granularity
    -da[:<packagename>...|:<classname>]
    -disableassertions[:<packagename>...|:<classname>]
                  disable assertions with specified granularity
    -esa | -enablesystemassertions
                  enable system assertions
    -dsa | -disablesystemassertions
                  disable system assertions
    -agentlib:<libname>[=<options>]
                  load native agent library <libname>, e.g. -agentlib:hprof
                  see also, -agentlib:jdwp=help and -agentlib:hprof=help
    -agentpath:<pathname>[=<options>]
                  load native agent library by full pathname
    -javaagent:<jarpath>[=<options>]
                  load Java programming language agent, see java.lang.instrument
    -splash:<imagepath>
                  show splash screen with specified image
See http://www.oracle.com/technetwork/java/javase/documentation/index.html for more details.

```