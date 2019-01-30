java.util.concurrent.locks.AbstractQueuedSynchronizer

[Java并发之AQS详解](https://www.cnblogs.com/daydaynobug/p/6752837.html)

## 3.1 acquire(int)
　　此方法是独占模式下线程获取共享资源的顶层入口。如果获取到资源，线程直接返回，否则进入等待队列，直到获取到资源为止，且整个过程忽略中断的影响。
    这也正是lock()的语义，当然不仅仅只限于lock()。获取到资源后，线程就可以去执行其临界区代码了。下面是acquire()的源码：


```java_holder_method_tree

    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }
    
    // tryAcquire()尝试直接去获取资源，如果成功则直接返回；
    // addWaiter()将该线程加入等待队列的尾部，并标记为独占模式；
    // acquireQueued()使线程在等待队列中获取资源，一直获取到资源后才返回。如果在整个等待过程中被中断过，则返回true，否则返回false。
    // 如果线程在等待过程中被中断过，它是不响应的。只是获取资源后才再进行自我中断selfInterrupt()，将中断补上。
    
    
    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }
    
    // 当前线程加入到等待队列的队尾，并返回当前线程所在的结点
    private Node addWaiter(Node mode) {
        //以给定模式构造结点。mode有两种：EXCLUSIVE（独占）和SHARED（共享）
        Node node = new Node(Thread.currentThread(), mode);
        
        //尝试快速方式直接放到队尾。
        Node pred = tail;
        if (pred != null) {
            node.prev = pred;
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        
        //上一步失败则通过enq入队。
        enq(node);
        return node;
    }
    
    // 将node加入队尾
    private Node enq(final Node node) {
        //CAS"自旋"，直到成功加入队尾
        for (;;) {
            Node t = tail;
            if (t == null) { // 队列为空，创建一个空的标志结点作为head结点，并将tail也指向它。
                if (compareAndSetHead(new Node()))
                    tail = head;
            } else {//正常流程，放入队尾
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }
    
    // 进入等待状态休息，直到其他线程彻底释放资源后唤醒自己，自己再拿到资源，然后就可以去干自己想干的事了。
    final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;//标记是否成功拿到资源
        try {
            boolean interrupted = false;//标记等待过程中是否被中断过
            
            //又是一个“自旋”！
            for (;;) {
                final Node p = node.predecessor();//拿到前驱
                //如果前驱是head，即该结点已成老二，那么便有资格去尝试获取资源（可能是老大释放完资源唤醒自己的，当然也可能被interrupt了）。
                if (p == head && tryAcquire(arg)) {
                    setHead(node);//拿到资源后，将head指向该结点。所以head所指的标杆结点，就是当前获取到资源的那个结点或null。
                    p.next = null; // setHead中node.prev已置为null，此处再将head.next置为null，就是为了方便GC回收以前的head结点。也就意味着之前拿完资源的结点出队了！
                    failed = false;
                    return interrupted;//返回等待过程中是否被中断过
                }
                
                //如果自己可以休息了，就进入waiting状态，直到被unpark()
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;//如果等待过程中被中断过，哪怕只有那么一次，就将interrupted标记为true
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
    
    // 1. 结点进入队尾后，检查状态，找到安全休息点；
    // 2. 调用park()进入waiting状态，等待unpark()或interrupt()唤醒自己；
    // 3. 被唤醒后，看自己是不是有资格能拿到号。如果拿到，head指向当前结点，并返回从入队到拿到号的整个过程中是否被中断过；如果没拿到，继续流程1。
    
```

## 3.2 release(int)

```java_holder_method_tree

    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;//找到头结点
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);//唤醒等待队列里的下一个线程
            return true;
        }
        return false;
    }
    
    // 用unpark()唤醒等待队列中最前边的那个未放弃线程
    private void unparkSuccessor(Node node) {
        //这里，node一般为当前线程所在的结点。
        int ws = node.waitStatus;
        if (ws < 0)//置零当前线程所在的结点状态，允许失败。
            compareAndSetWaitStatus(node, ws, 0);
    
        Node s = node.next;//找到下一个需要唤醒的结点s
        if (s == null || s.waitStatus > 0) {//如果为空或已取消
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)//从这里可以看出，<=0的结点，都是还有效的结点。
                    s = t;
        }
        if (s != null)
            LockSupport.unpark(s.thread);//唤醒
    }

```

## 3.3 acquireShared(int)

```java_holder_method_tree

    public final void acquireShared(int arg) {
        if (tryAcquireShared(arg) < 0)
            doAcquireShared(arg);
    }
    
    // 将当前线程加入等待队列尾部休息，直到其他线程释放资源唤醒自己，自己成功拿到相应量的资源后才返回
    private void doAcquireShared(int arg) {
        final Node node = addWaiter(Node.SHARED);//加入队列尾部
        boolean failed = true;//是否成功标志
        try {
            boolean interrupted = false;//等待过程中是否被中断过的标志
            for (;;) {
                final Node p = node.predecessor();//前驱
                if (p == head) {//如果到head的下一个，因为head是拿到资源的线程，此时node被唤醒，很可能是head用完资源来唤醒自己的
                    int r = tryAcquireShared(arg);//尝试获取资源
                    if (r >= 0) {//成功
                        setHeadAndPropagate(node, r);//将head指向自己，还有剩余资源可以再唤醒之后的线程
                        p.next = null; // help GC
                        if (interrupted)//如果等待过程中被打断过，此时将中断补上。
                            selfInterrupt();
                        failed = false;
                        return;
                    }
                }
                
                //判断状态，寻找安全点，进入waiting状态，等着被unpark()或interrupt()
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
    
    private void setHeadAndPropagate(Node node, int propagate) {
        Node h = head; 
        setHead(node);//head指向自己
         //如果还有剩余量，继续唤醒下一个邻居线程
        if (propagate > 0 || h == null || h.waitStatus < 0) {
            Node s = node.next;
            if (s == null || s.isShared())
                doReleaseShared();
        }
    }

```

## 3.4 releaseShared()

```java_holder_method_tree

    public final boolean releaseShared(int arg) {
        if (tryReleaseShared(arg)) {//尝试释放资源
            doReleaseShared();//唤醒后继结点
            return true;
        }
        return false;
    }
    
    private void doReleaseShared() {
        for (;;) {
            Node h = head;
            if (h != null && h != tail) {
                int ws = h.waitStatus;
                if (ws == Node.SIGNAL) {
                    if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                        continue;
                    unparkSuccessor(h);//唤醒后继
                }
                else if (ws == 0 &&
                         !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                    continue;
            }
            if (h == head)// head发生变化
                break;
        }
    }

```