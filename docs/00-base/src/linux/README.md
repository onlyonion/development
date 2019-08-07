linux-2.6.32.6

## overview 
- [arch](/docs/00-base/src/linux/arch/README.md) 特定体系结构的源码
- [block](/docs/00-base/src/linux/block/README.md) 块设备I/O层 
- [crypto](/docs/00-base/src/linux/crypto/READEME.md) 加密API
- Documentation 内核源码文档
- [drivers](/docs/00-base/src/linux/drivers/README.md) 设备驱动程序
- firmware 使用某些驱动程序而需要的设备固件
- [fs](/docs/00-base/src/linux/fs/README.md) VFS和各种文件系统
  - ext4
  - fat
  - hpfs
  - hfs
  - [fs-writeback](/docs/00-base/src/linux/fs/fs-writeback.md)
include 内核头文件
  - linux
    - [aio](/docs/00-base/src/linux/include/linux/aio.md)
    - [netfilter](/docs/00-base/src/linux/include/linux/netfilter.md)
    - [rbtree](/docs/00-base/src/linux/include/linux/rbtree.md)
    - [sched](/docs/00-base/src/linux/include/linux/sched.md)
    - [spinlock](/docs/00-base/src/linux/include/linux/spinlock.md)
    - [syscalls](/docs/00-base/src/linux/include/linux/syscalls.md)
    - [thread_info](/docs/00-base/src/linux/include/linux/thread_info.md)
- [init](/docs/00-base/src/linux/init/README.md) 内核引导和初始化
- [ipc](/docs/00-base/src/linux/ipc/README.md) 进程间通信代码
- kernel 像调度程序这样的核心子系统
- lib 通用内核函数
- mm 内存管理子系统和VM
  - mempolicy.c
  - mempool.c
  - memory.c
  - memcontrol.c
  - page_alloc.c
  - [page-writeback](/docs/00-base/src/linux/mm/page-writeback.md)
- [net](/docs/00-base/src/linux/net/README.md) 网络子系统
- samples 示范代码
- scripts 编译内核所用的脚本
- security Linux安全模块
- sound 语音子系统
- tools 工具
- usr 早期用户空间代码
- virt 虚拟化基础结构
  - kvm
  
  
  
## book
* [《Linux内核设计与实现》美 Robert Love著 陈莉君 康华 译](/99-book/notes/70-dev-ops/Linux内核设计与实现.md)
* [《深入分析Linux内核源代码》陈莉君 人民邮电出版社](/99-book/notes/70-dev-ops/深入分析Linux内核源代码.md)  
* [《Linux命令行与shell脚本编程大全（第2班）》 美 Richard Blum著 吴海峰 译](/99-book/notes/70-dev-ops/Linux命令行与shell脚本编程大全.md)
