io.netty.util.concurrent.GenericFutureListener

## hierarchy

## define
```plantuml
@startuml

interface EventListener
interface GenericFutureListener<F extends Future<?>> {
    + void operationComplete(F future)
}

GenericFutureListener ..> Future

EventListener ^-- GenericFutureListener

@enduml
```