io.netty.channel.nio.NioEventLoop

## hierarchy
```
AbstractExecutorService (java.util.concurrent)
    AbstractEventExecutor (io.netty.util.concurrent)
        AbstractScheduledEventExecutor (io.netty.util.concurrent)
            SingleThreadEventExecutor (io.netty.util.concurrent)
                SingleThreadEventLoop (io.netty.channel)
                    NioEventLoop (io.netty.channel.nio)
```

## define
```plantuml
@startuml

abstract class AbstractExecutorService 

abstract class AbstractEventExecutor
abstract class AbstractScheduledEventExecutor
abstract class SingleThreadEventExecutor

interface EventLoop
abstract class SingleThreadEventLoop 

AbstractExecutorService ^-- AbstractEventExecutor
AbstractEventExecutor ^-- AbstractScheduledEventExecutor
AbstractScheduledEventExecutor ^-- SingleThreadEventExecutor
SingleThreadEventExecutor ^-- SingleThreadEventLoop
SingleThreadEventLoop ^-- NioEventLoop

EventLoop ^.. SingleThreadEventLoop

class NioEventLoop #orange
NioEventLoop o-- Selector
NioEventLoop o-- SelectedSelectionKeySet
NioEventLoop o-- SelectorProvider

@enduml
```

## methods
### run()
for + select