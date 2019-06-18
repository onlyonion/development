java.util.concurrent.FutureTask

## hierarchy
```
FutureTask (java.util.concurrent)
    Object (java.lang)
    RunnableFuture (java.util.concurrent)
        Future (java.util.concurrent)
        Runnable (java.lang)
```

## define
```plantuml
@startuml

interface Runnable
interface Future<V>
Runnable ^-- RunnableFuture
Future ^-- RunnableFuture

interface RunnableFuture<V>
RunnableFuture ^.. FutureTask

class FutureTask<V> {
    - Callable<V> callable
    - Object outcome
    - volatile Thread runner
    - volatile WaitNode waiters
    # boolean runAndReset()
}

@enduml
```