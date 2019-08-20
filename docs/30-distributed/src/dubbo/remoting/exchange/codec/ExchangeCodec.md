com.alibaba.dubbo.remoting.exchange.codec.ExchangeCodec

## hierarchy
```
AbstractCodec (com.alibaba.dubbo.remoting.transport)
    TransportCodec (com.alibaba.dubbo.remoting.transport.codec)
        TelnetCodec (com.alibaba.dubbo.remoting.telnet.codec)
            ExchangeCodec (com.alibaba.dubbo.remoting.exchange.codec)
                DubboCodec (com.alibaba.dubbo.rpc.protocol.dubbo)
```

## define
```java
public class ExchangeCodec extends TelnetCodec {

    // header length.
    protected static final int HEADER_LENGTH = 16;
    // magic header.
    protected static final short MAGIC = (short) 0xdabb;
    protected static final byte MAGIC_HIGH = Bytes.short2bytes(MAGIC)[0];
    protected static final byte MAGIC_LOW = Bytes.short2bytes(MAGIC)[1];
    // message flag.
    protected static final byte FLAG_REQUEST = (byte) 0x80;
    protected static final byte FLAG_TWOWAY = (byte) 0x40;
    protected static final byte FLAG_EVENT = (byte) 0x20;
    protected static final int SERIALIZATION_MASK = 0x1f;
    private static final Logger logger = LoggerFactory.getLogger(ExchangeCodec.class);
}    
```