java.nio.channels.Selector

## package
```
Selector (java.nio.channels)
    AbstractSelector (java.nio.channels.spi)
        SelectorImpl (sun.nio.ch)
            WindowsSelectorImpl (sun.nio.ch)
    SelectedSelectionKeySetSelector (io.netty.channel.nio)
```
## define
```plantuml
@startuml

interface AutoCloseable
interface Closeable
abstract class Selector

AutoCloseable ^-- Closeable
Closeable ^.. Selector

Selector o-- SelectorProvider
Selector o-- SelectionKey

@enduml
```

## methods
* open
* isOpen
* provider
* keys
* selectedKeys
* selectNow
* select
* select
* wakeup
* close

