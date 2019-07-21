org.apache.catalina.mapper.Mapper

## hierarchy
```

```

## define
```plantuml
@startuml



@enduml
```

## fields
```java
    /**
     * Array containing the virtual hosts definitions.
     */
    // Package private to facilitate testing
    volatile MappedHost[] hosts = new MappedHost[0];


    /**
     * Default host name.
     */
    private String defaultHostName = null;
    private volatile MappedHost defaultHost = null;


    /**
     * Mapping from Context object to Context version to support
     * RequestDispatcher mappings.
     */
    private final Map<Context, ContextVersion> contextObjectToContextVersionMap = new ConcurrentHashMap<>();

```

## methods