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

''''''''''''''''''Selector'''''''''''''''''''''''
interface AutoCloseable
interface Closeable
abstract class Selector {
    + abstract Set<SelectionKey> keys()
    + abstract Set<SelectionKey> selectedKeys()    
    + abstract int selectNow()
}

AutoCloseable ^-- Closeable
Closeable ^.. Selector

Selector o-- SelectorProvider
Selector "1" o-- "*" SelectionKey


''''''''''''''''''SelectionKey'''''''''''''''''''''''
abstract class SelectionKey

SelectionKey o-- SelectableChannel
SelectionKey o-- Selector

abstract class AbstractSelectionKey {
    - volatile boolean valid = true;
}

SelectionKey ^-- AbstractSelectionKey

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

