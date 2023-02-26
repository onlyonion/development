java.util.concurrent.Semaphore

控制同时访问特定资源的线程数量

## define
```plantuml
@startuml

class Semaphore {
    - final Sync sync
    + void acquire()
    + void release()
    + void acquire(int permits)
    + void release(int permits) 
}

Semaphore o-- Sync
Semaphore +-- Sync
Semaphore +-- NonfairSync
Semaphore +-- FairSync

abstract class AbstractQueuedSynchronizer #orange
AbstractQueuedSynchronizer ^-- Sync

class Sync #orange {
    # final int nonfairTryAcquireShared(int acquires)
}
Sync ^-- NonfairSync
Sync ^-- FairSync

class NonfairSync {
    # int tryAcquireShared(int acquires)
}

class FairSync {
    # int tryAcquireShared(int acquires)
}

@enduml
```

## methods

### acquire
```java
    public void acquire() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }
```

### acquire
```java
    public void acquire() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }
    public void acquire(int permits) throws InterruptedException {
        if (permits < 0) throw new IllegalArgumentException();
        sync.acquireSharedInterruptibly(permits);
    }
```

###
```java
    public void release() {
        sync.releaseShared(1);
    }
    public void release(int permits) {
        if (permits < 0) throw new IllegalArgumentException();
        sync.releaseShared(permits);
    }
```