
```mermaid
graph BT
    Invocation --> Joinpoint
    MethodInvocation --> Invocation
    ProxyMethodInvocation --> MethodInvocation
    ReflectiveMethodInvocation -.-> ProxyMethodInvocation
```