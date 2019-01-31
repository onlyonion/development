
## define

```java
public class Thread implements Runnable {
    private volatile String name;
    private int priority;
    private boolean daemon = false;
    
    private ThreadGroup group;
    
    private long stackSize;
    private long tid;
    
    private volatile int threadStatus = 0;
    
    public enum State {
        NEW,
        RUNNABLE,
        BLOCKED,
        WAITING,
        TIMED_WAITING,
        TERMINATED;
    }
}
```

@startuml

class Thread {
    - String name
    - int priority
    - boolean daemon
    - ThreadGroup group
    - long tid
    - int threadStatus
}

Runnable <|.. Thread

enum State {
    NEW,
    RUNNABLE,
    BLOCKED,
    WAITING,
    TIMED_WAITING,
    TERMINATED;
}

Thread +- State
    
@enduml
