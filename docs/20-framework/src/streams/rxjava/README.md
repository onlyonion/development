RxJava – Reactive Extensions for the JVM – 
a library for composing asynchronous and event-based programs using observable sequences for the Java VM.

响应式编程是一种基于异步数据流概念的编程模式

RxJava - JVM响应式扩展Reactive Extensions 用于使用Java VM的可观察序列编写异步和基于事件的程序的库。

rx
## pacakge
```
annotations
exceptions
functions
internal
observables
observers
plugins
schedulers
singles
subjects
subscriptions
BackpressureOverflow
Completable
CompletableEmitter
CompletableSubscriber
Emitter
Notification
Observable
Observer
Producer
Scheduler
Single
SingleEmitter
SingleSubscriber
Subscriber
Subscription
```

## overview
```plantuml
@startuml

''''''''''''''''''被观察者''''''''''''''''''''
class Observable<T>

interface Producer
abstract class Scheduler
Scheduler +-- Worker
abstract class Worker
Subscription ^.. Worker

class Notification<T>
Notification ..> Observer
Notification +-- Kind
enum Kind {
    OnNext, 
    OnError, 
    OnCompleted
}

interface SingleEmitter<T>
SingleEmitter ..> Subscription


''''''''''''''''''观察者''''''''''''''''''''
interface Observer<T> {
    + void onCompleted()
    + void onError(Throwable e)
    + void onNext(T t)
}
interface Emitter<T>
Emitter ..> Subscription
Observer ^-- Emitter


Observer ^.. Subscriber
Subscription ^.. Subscriber
interface Subscription {
    + void unsubscribe()
    + boolean isUnsubscribed()
}
abstract class Subscriber<T>

@enduml
```