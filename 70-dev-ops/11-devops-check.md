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