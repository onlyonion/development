com.alibaba.dubbo.rpc.RpcInvocation

## define

```plantuml
@startuml

interface Invocation
Invocation ^.. RpcInvocation

class RpcInvocation {
    - String methodName
    - Class<?>[] parameterTypes
    - Object[] arguments
    - Map<String, String> attachments
    - transient Invoker<?> invoker
}

@enduml
```

```java
public class RpcInvocation implements Invocation, Serializable {
    private static final long serialVersionUID = -4355285085441097045L;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] arguments;
    private Map<String, String> attachments;
    private transient Invoker<?> invoker;
}    
```