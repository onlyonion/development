org.apache.tomcat.util.net.Nio2Channel

## hierarchy
```
Nio2Channel (org.apache.tomcat.util.net)
    SecureNio2Channel (org.apache.tomcat.util.net)
Nio2Channel (org.apache.tomcat.util.net)
    AsynchronousByteChannel (java.nio.channels)
        AsynchronousChannel (java.nio.channels)
            Channel (java.nio.channels)
                Closeable (java.io)
                    AutoCloseable (java.lang)
```

## define
```plantuml
@startuml

class Nio2Channel
abstract class AsynchronousSocketChannel
abstract class SocketWrapperBase<E>
SocketBufferHandler

@enduml
```

## fields
```java
    protected static final ByteBuffer emptyBuf = ByteBuffer.allocate(0);

    protected AsynchronousSocketChannel sc = null;
    protected SocketWrapperBase<Nio2Channel> socket = null;
    protected final SocketBufferHandler bufHandler;
    
    private ApplicationBufferHandler appReadBufHandler;
```

## methods