java.nio.channels.FileChannel


## Hierarchy
```
AbstractInterruptibleChannel (java.nio.channels.spi)
    FileChannel (java.nio.channels)
        4 in ZipFileSystem (jdk.nio.zipfs)
        FileChannelImpl (sun.nio.ch)
```

## Define
```plantuml
@startuml

abstract class FileChannel

@enduml
```

## Methods


```java
    public abstract long transferTo(long position, long count, WritableByteChannel target) throws IOException;

    public abstract long transferFrom(ReadableByteChannel src, long position, long count) throws IOException;
```