## netty

## 组件 components

* core 
    - zero-copy-capabie, rich byte buffer
    - universal communication api
    - extensible event model
* transport services
    - socket & datagram
    - http tunnel
    - in-vm pipe
* protocol support
    - http & websocket
    - ssl startTLS
    - google protobuf
    - zlib/gzip comppression
    - large file transfer
    - rtsp Real Time Streaming Protocol 实时流传输协议
    - legacy text binary protocols with unit testablility

![components](../../img/netty-components.png)


## io.netty 4.x
4.1.5.Final
jdk.1.7.0_85

```
io.netty

bootstrap
buffer
channel
    embedded
    epoll
    group
    local
    nio
    oio
    pool
    rxtx
    sctp
    socket
    udt
    unix
handler    
    codec
    flow
    flush
    ipfilter
    logging
    proxy
    ssl
    stream
    timeout
    traffic
resovler
util
    collection
    concurrent
    internal    
```

## links
* [《Netty权威指南（第2版）》李林锋](/99-book/notes/21-server/Netty权威指南.md)