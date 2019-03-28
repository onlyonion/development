com.alibaba.dubbo.remoting.exchange.support.DefaultFuture

## hierarchy
```
ResponseFuture (com.alibaba.dubbo.remoting.exchange)
    DefaultFuture (com.alibaba.dubbo.remoting.exchange.support)
    SimpleFuture (com.alibaba.dubbo.remoting.exchange.support)
```

## define

```plantuml
@startuml

interface ResponseFuture {
}
ResponseFuture ^.. DefaultFuture

class DefaultFuture {

}

DefaultFuture o-- Response
DefaultFuture o-- ResponseCallback
DefaultFuture +-- RemotingInvocationTimeoutScan

class Response { 

}

interface ResponseCallback {
    + void done(Object response)
    + void caught(Throwable exception)
}

class RemotingInvocationTimeoutScan {
}

@enduml
```
