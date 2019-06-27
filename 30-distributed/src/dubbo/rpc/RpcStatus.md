com.alibaba.dubbo.rpc.RpcStatus

* ConcurrentHashMap
* Semaphore
* AtomicInteger
* AtomicLong
  * volatile long + while + compareAndSwapLong
  * getAndAddLong  getLongVolatile + compareAndSwapLong + while
  
## define
```plantuml
@startuml

class RpcStatus {
    - volatile Semaphore executesLimit
}

RpcStatus o-- Semaphore

@enduml
```  
  