[CPU亲和性](https://blog.csdn.net/zjy900507/article/details/81389670)

超线程技术(Hyper-Threading)：就是利用特殊的硬件指令，把两个逻辑内核(CPU core)模拟成两个物理芯片，

CPU affinity 是一种调度属性(scheduler property), 它可以将一个进程"绑定" 到一个或一组CPU上.

在SMP(Symmetric Multi-Processing对称多处理)架构下，Linux调度器(scheduler)会根据CPU affinity的设置让指定的进程运行在"绑定"的CPU上,而不会在别的CPU上运行. 