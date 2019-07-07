
### 进程

### 线程
Java在64位系统默认Stack大小是1024KB，所以一个进程开启上万个线程是不现实的。
多线程并不能充分的利用CPU，大部分线程处于**等待**状态，CPU也没有这么**核**让线程使用。所以一般线程数目都是CPU的核数。

先阶段行业里的比较流行的解决方案之一就是单线程加上异步回调。其代表派是 node.js 以及Java里的新秀 Vert.x 。
他们的核心思想是一样的，遇到需要进行I/O操作的地方，就直接让出CPU资源，然后注册一个回调函数，其他逻辑则继续往下走，
I/O结束后带着结果向事件队列里插入执行结果，然后由事件调度器调度回调函数，传入结果。

异步回调函数导致业务逻辑的割裂，之前栈上的局部变量丢失，回调函数不停的嵌套。

Promise，CompletableFuture等技术解决异步回调嵌套问题。

### 协程 coroutine
协程的本质上其实还是和上面的方法一样，只不过他的核心点在于调度那块由他来负责解决

Golang里的 go 关键字其实就是负责开启一个 Fiber ，让 func 逻辑跑在上面。而这一切都是发生的用户态上，没有发生在内核态上，也就是说没有ContextSwitch上的开销。

Quasar实现的coroutine的方式与Golang很像，只不过一个是框架级别实现，一个是语言内置机制而已。

异步无阻塞的编码方式其实有很多种实现，比如node.js的提倡的Promise，对应到Java8的就是CompletableFuture。

[次时代Java编程（一）：Java里的协程](https://www.open-open.com/lib/view/open1468892872350.html)


### [Quasar](https://blog.csdn.net/hj7jay/article/details/51980038 )
Go的优势在于可以实现request-per-goroutine,整个系统中可以有成千上万个goroutine。 goroutine是轻量级的，而且在I/O阻塞的时候可以不占用线程，这让Go可以轻松地处理上万个链接，即使I/O阻塞也没问题。Go和Java之间的通讯协议可以通过Protobuffer来实现，而且它们之间只保留一个TCP连接即可。