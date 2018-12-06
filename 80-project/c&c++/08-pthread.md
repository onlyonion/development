基于进程的多任务处理是程序的并发执行。
基于线程的多任务处理是同一程序的片段的并发执行。

```c++
#include <pthread.h>
// 创建一个 POSIX 线程

// thread	        指向线程标识符指针。
// attr	            一个不透明的属性对象，可以被用来设置线程属性。您可以指定线程属性对象，也可以使用默认值 NULL。
// start_routine	线程运行函数起始地址，一旦线程被创建就会执行。
// arg	            运行函数的参数。它必须通过把引用作为指针强制转换为 void 类型进行传递。如果没有传递参数，则使用 NULL。
pthread_create (thread, attr, start_routine, arg) 

// 终止一个 POSIX 线程
pthread_exit (status) 
```


```c++
pthread_join (threadid, status) 
pthread_detach (threadid) 
```