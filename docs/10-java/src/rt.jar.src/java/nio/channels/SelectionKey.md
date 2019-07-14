java.nio.channels.SelectionKey


* volatile
* synchronized

## hierarchy
```
SelectionKey (java.nio.channels)
    AbstractSelectionKey (java.nio.channels.spi)
        SelectionKeyImpl (sun.nio.ch)
```

## define
```plantuml
@startuml

abstract class SelectionKey

SelectionKey o-- SelectableChannel
SelectionKey o-- Selector

abstract class AbstractSelectionKey {
    - volatile boolean valid = true;
}

SelectionKey ^-- AbstractSelectionKey

@enduml
```