java.lang.reflect.Proxy

## define
```plantuml
@startuml

class Proxy
Proxy *-- InvocationHandler
Proxy *-- WeakCache
Proxy *-- ConcurrentHashMap

@enduml
```

```conf
-Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
-Dcglib.debugLocation=C:/var/java/cglib/
System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "类文件指定输出目录");
```