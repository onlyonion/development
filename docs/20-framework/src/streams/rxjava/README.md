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
- 被观察者  Observable
- 观察者 Observer
- 订阅 Subscribe
- 事件 Event

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