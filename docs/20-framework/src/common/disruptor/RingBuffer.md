com.lmax.disruptor.RingBuffer

- 环行数组

## hierarchy
```
RingBufferPad (com.lmax.disruptor)
    RingBufferFields (com.lmax.disruptor)
        RingBuffer (com.lmax.disruptor)
        
RingBuffer (com.lmax.disruptor)
    RingBufferFields (com.lmax.disruptor)
        RingBufferPad (com.lmax.disruptor)
    Cursored (com.lmax.disruptor)
    EventSequencer (com.lmax.disruptor)
        DataProvider (com.lmax.disruptor)
        Sequenced (com.lmax.disruptor)
    EventSink (com.lmax.disruptor)
```

## define
```plantuml
@startuml

abstract class RingBufferPad
abstract class RingBufferFields<E>
class RingBuffer<E>

RingBufferPad ^-- RingBufferFields
RingBufferFields ^-- RingBuffer

RingBuffer *- Sequencer
Sequencer ..> SequenceBarrier
Sequencer ..> EventPoller

interface Sequencer
interface SequenceBarrier
class EventPoller<T>

@enduml
```