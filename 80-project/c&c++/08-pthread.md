基于进程的多任务处理是程序的并发执行。
基于线程的多任务处理是同一程序的片段的并发执行。

```c++
#include <pthread.h>
pthread_create (thread, attr, start_routine, arg) 

pthread_exit (status) 
```


```c++
pthread_join (threadid, status) 
pthread_detach (threadid) 
```