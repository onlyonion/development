com.lmax.disruptor.WorkerPool

## define
```plantuml
@startuml

class WorkerPool<T>
interface SequenceBarrier
interface WorkHandler<T> #pink
interface ExceptionHandler<T>
class Sequence

WorkerPool *-- SequenceBarrier
WorkerPool *-- WorkHandler
WorkerPool *-- Sequence
WorkerPool *--- ExceptionHandler

ExceptionHandler ^.. IgnoreExceptionHandler
ExceptionHandler ^.. ExceptionHandlerWrapper
ExceptionHandler ^.. FatalExceptionHandler

interface Runnable 
interface EventProcessor 
class WorkProcessor<T> #orange

Runnable ^-- EventProcessor
EventProcessor ^.. WorkProcessor
WorkerPool "1" *-- "*" WorkProcessor

interface Executor #orange
WorkerPool ..> Executor

@enduml
```

## methods

### start
```java
    public RingBuffer<T> start(final Executor executor) {
        if (!started.compareAndSet(false, true)) {
            throw new IllegalStateException("WorkerPool has already been started and cannot be restarted until halted.");
        }

        final long cursor = ringBuffer.getCursor();
        workSequence.set(cursor);

        for (WorkProcessor<?> processor : workProcessors) {
            processor.getSequence().set(cursor);
            executor.execute(processor);
        }

        return ringBuffer;
    }
```