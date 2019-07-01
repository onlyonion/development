troubleshoot 故障排查

top

jps
jstack pid > pid.log
jmap 

1. 查消耗cpu最高的进程PID 
```
执行top -c ，显示进程运行信息列表。按下P,进程按照cpu使用率排序
```
2. 根据PID查出消耗cpu最高的线程号
```
top -Hp 3033 ，显示一个进程的线程运行信息列表。按下P,进程按照cpu使用率排序
```
3. 根据线程号查出对应的java线程，进行处理
```
// 执行命令,导出进程快照
jstack -l 3033 > ./3033.stack
// 然后执行，grep命令，看线程0xbda做了什么
cat 3033.stack |grep 'bda' -C 8
```

### linux command
* top
* cpu vmstat; mpstat; pidstat
* mem free -m
* disk df -h
* io  磁盘IO iostat -xdk 2 3 每2秒采样，3次
* net 网络IO ifstat; sar; netstat; 

### cpu占用过高
结合linux和jdk命令一起分析
1. top命令找出cpu占比最高的
2. ps -ef 或者 jps进一步定位，得志是一个怎么样的后台程序
3. 定位到具体的**线程或代码**; `ps -mp 进程 -o THREAD,tid,time`
4. 将需要的线程ID转换为16进制格式 `printf "%x\n" tid` 十进制转换成十六进制
5. jstack 进程id | grep tid（16进制线程ID小写英文） -A60 （打印前60行）


[Java线上应用故障排查之一：高CPU占用【转】](http://www.linuxhot.com/java-cpu-used-high.html)

方法一：
1. jps 获取Java进程的PID。
2. jstack pid >> java.txt 导出CPU占用高进程的线程栈。
3. top -H -p PID 查看对应进程的哪个线程占用CPU过高。
4. echo “obase=16; PID” | bc 将线程的PID转换为16进制,大写转换为小写。
5. 在第二步导出的Java.txt中查找转换成为16进制的线程PID。找到对应的线程栈。
6. 分析负载高的线程栈都是什么业务操作。优化程序并处理问题。

方法二：
1. 使用top 定位到占用CPU高的进程PID top 通过ps aux | grep PID命令
2. 获取线程信息，并找到占用CPU高的线程 ps -mp pid -o THREAD,tid,time | sort -rn 
3. 将需要的线程ID转换为16进制格式 printf "%x\n" tid
4. 打印线程的堆栈信息 jstack pid | grep tid -A30