
## JVM关闭

* 正常关闭 所有非守护线程执行结束, System.exit(0), ctrl + c, kill (-15)
* 异常关闭 RuntimeException, OOM
* 强制关闭 kill -9 sigkill, Runtime.halt(), 断电, 系统关机, 系统crash

## JVM安全退出

### 使用关闭钩子

Runtime.addShutdownHook(Thread hook)注册自定义钩子，在钩子中实现资源的清理

### 基于信号的进程通知机制

通过这种信号机制，对应用程序JVM发送特定信号,JVM可以感知并处理该信号，进而可以接受程序退出指令。


[摘自-JVM安全退出](https://tech.imdada.cn/2017/06/18/jvm-safe-exit) 