io.netty.channel.FileRegion

## hierachy
```
FileRegion (io.netty.channel)
    DefaultFileRegion (io.netty.channel)
    ManyMessageTransfer (org.apache.rocketmq.broker.pagecache)
    OneMessageTransfer (org.apache.rocketmq.broker.pagecache)
    QueryMessageTransfer (org.apache.rocketmq.broker.pagecache)
```

## define

```plantuml
@startuml

interface ReferenceCounted 
ReferenceCounted ^-- FileRegion

interface FileRegion {
    + long transferTo(WritableByteChannel target, long position)
}

interface ReferenceCounted 
abstract class AbstractReferenceCounted

ReferenceCounted ^.. AbstractReferenceCounted
AbstractReferenceCounted ^-- DefaultFileRegion
FileRegion ^.. DefaultFileRegion

class DefaultFileRegion {
    - FileChannel file
}


@enduml
```
