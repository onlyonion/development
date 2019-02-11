org.springframework.core.io.ResourceLoader
org.springframework.context.support.AbstractApplicationContext

## hierachy
```
ResourceLoader (org.springframework.core.io)
    DefaultResourceLoader (org.springframework.core.io)
        AbstractApplicationContext (org.springframework.context.support)
            GenericApplicationContext (org.springframework.context.support)
            AbstractRefreshableApplicationContext (org.springframework.context.support)
        ClassRelativeResourceLoader (org.springframework.core.io)
        FileSystemResourceLoader (org.springframework.core.io)
        ServletContextResourceLoader (org.springframework.web.context.support)
    ResourcePatternResolver (org.springframework.core.io.support)
        PathMatchingResourcePatternResolver (org.springframework.core.io.support)
        ApplicationContext (org.springframework.context)
```

## class
```
@startuml

interface ResourceLoader {
	+ String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX
	+ Resource getResource(String location)
	+ ClassLoader getClassLoader()
}

'''''''''''''''''''''''' 默认实现'''''''''''''''''''''''' 
class DefaultResourceLoader {
    - ClassLoader classLoader
    - final Set<ProtocolResolver> protocolResolvers
    - final Map<Class<?>, Map<Resource, ?>> resourceCaches
}

ResourceLoader <|.. DefaultResourceLoader
abstract class AbstractApplicationContext
DefaultResourceLoader <|-- AbstractApplicationContext

'''''''''''''''''''''''' 资源匹配解析'''''''''''''''''''''''' 
interface ResourcePatternResolver {
    + String CLASSPATH_ALL_URL_PREFIX = "classpath*:"
    + Resource[] getResources(String locationPattern)
}
ResourceLoader <|-- ResourcePatternResolver

interface ApplicationContext
interface ConfigurableApplicationContext
ResourcePatternResolver <|-- ApplicationContext
ApplicationContext <|-- ConfigurableApplicationContext
ConfigurableApplicationContext <|.. AbstractApplicationContext

@enduml
```