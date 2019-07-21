java.lang.management.ThreadInfo

## define

## fields
```java
    private String       threadName;
    private long         threadId;
    private long         blockedTime;
    private long         blockedCount;
    private long         waitedTime;
    private long         waitedCount;
    private LockInfo     lock;
    private String       lockName;
    private long         lockOwnerId;
    private String       lockOwnerName;
    private boolean      inNative;
    private boolean      suspended;
    private Thread.State threadState;
    private StackTraceElement[] stackTrace;
    private MonitorInfo[]       lockedMonitors;
    private LockInfo[]          lockedSynchronizers;

    private static MonitorInfo[] EMPTY_MONITORS = new MonitorInfo[0];
    private static LockInfo[] EMPTY_SYNCS = new LockInfo[0];
```

## methods