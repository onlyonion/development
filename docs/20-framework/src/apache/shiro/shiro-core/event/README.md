org.apache.shiro.event

## Overview
```plantuml
@startuml
class EventObject
abstract class Event
EventObject ^-- Event

interface EventBus 
interface EventBusAware
EventBusAware ..> EventBus

interface EventListener
interface TypedEventListener
EventListener ^-- TypedEventListener

@enduml
```