include/linux/sched.h


### task_struct
```c
struct task_struct {
	volatile long state;	/* -1 unrunnable, 0 runnable, >0 stopped */
	void *stack;
	atomic_t usage;
	unsigned int flags;	/* per process flags, defined below */
	unsigned int ptrace;

	int lock_depth;		/* BKL lock depth */
	int prio, static_prio, normal_prio;
	unsigned int rt_priority;

    /* CPU-specific state of this task */
	struct thread_struct thread;
    /* filesystem information */
	struct fs_struct *fs;
    /* open file information */
	struct files_struct *files;
    /* namespaces */
	struct nsproxy *nsproxy;
    /* signal handlers */
	struct signal_struct *signal;
	struct sighand_struct *sighand;

	/* Protection of the PI data structures: */
	spinlock_t pi_lock;

}  
```