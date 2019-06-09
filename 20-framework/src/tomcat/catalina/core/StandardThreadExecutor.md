org.apache.catalina.core.StandardThreadExecutor
## hierarchy
```
LifecycleBase (org.apache.catalina.util)
    LifecycleMBeanBase (org.apache.catalina.util)
        StandardThreadExecutor (org.apache.catalina.core)
```
## define
```plantuml
@startuml

interface java.util.concurrent.Executor
java.util.concurrent.Executor ^-- Executor
java.util.concurrent.Executor ^-- ResizableExecutor

interface Lifecycle 
interface Executor
interface ResizableExecutor

Lifecycle ^-- Executor
Executor ^.. StandardThreadExecutor
ResizableExecutor ^.. StandardThreadExecutor

abstract class LifecycleBase
abstract class LifecycleMBeanBase

interface MBeanRegistration
Lifecycle ^.. LifecycleBase
LifecycleBase ^-- LifecycleMBeanBase
MBeanRegistration ^.. LifecycleMBeanBase
LifecycleMBeanBase ^-- StandardThreadExecutor

'''''''''''''''''''''StandardThreadExecutor'''''''''''''''''''''
class StandardThreadExecutor #orange {
    int maxThreads = 200
    int minSpareThreads = 25
    int maxIdleTime = 60000
    ThreadPoolExecutor executor
    String name
    - TaskQueue taskqueue
}


StandardThreadExecutor o-- ThreadPoolExecutor
StandardThreadExecutor o-- TaskQueue

@enduml
```