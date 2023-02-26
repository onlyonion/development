io.netty.util.HashedWheelTimer
- AtomicLong
- CountDownLatch
- PlatformDependent

## Define
```plantuml
@startuml

interface Timer {
  + Timeout newTimeout(TimerTask task, long delay, TimeUnit unit)
  + Set<Timeout> stop()
}
interface Timeout
interface TimerTask

class HashedWheelTimer #orange {
  - final HashedWheelBucket[] wheel
}

Timer ^..  HashedWheelTimer
Timer .> Timeout
Timer .> TimerTask
TimerTask ..> Timeout

class HashedWheelBucket {
  - HashedWheelTimeout head
  - HashedWheelTimeout tail
}

HashedWheelTimer *-- HashedWheelBucket

class HashedWheelTimeout
HashedWheelBucket *- HashedWheelTimeout
Timeout ^.. HashedWheelTimeout

HashedWheelTimeout *-- HashedWheelTimer

@enduml
```


```java
public class HashedWheelTimer implements Timer {
    private volatile int workerState; // 0 - init, 1 - started, 2 - shut down

    private final long tickDuration;
    private final HashedWheelBucket[] wheel;
}
```