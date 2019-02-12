
## 几个context

### FileSystemXmlApplicationContext
org.springframework.context.support.FileSystemXmlApplicationContext
```
DefaultResourceLoader
    AbstractApplicationContext
        AbstractRefreshableApplicationContext
            AbstractRefreshableConfigApplicationContext
                AbstractXmlApplicationContext
                    FileSystemXmlApplicationContext
```

### ClassPathXmlApplicationContext
org.springframework.context.support.ClassPathXmlApplicationContext
```
DefaultResourceLoader
    AbstractApplicationContext
        AbstractRefreshableApplicationContext
            AbstractRefreshableConfigApplicationContext
                AbstractXmlApplicationContext
                    ClassPathXmlApplicationContext
```

### XmlWebApplicationContext
org.springframework.web.context.support.XmlWebApplicationContext
```
DefaultResourceLoader
    AbstractApplicationContext
        AbstractRefreshableApplicationContext
            AbstractRefreshableConfigApplicationContext
                AbstractRefreshableWebApplicationContext
                    XmlWebApplicationContext
```

### AnnotationConfigEmbeddedWebApplicationContext
org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext
```
DefaultResourceLoader
    AbstractApplicationContext
        GenericApplicationContext
            GenericWebApplicationContext
                EmbeddedWebApplicationContext
                    AnnotationConfigEmbeddedWebApplicationContext
```


## context加载

```mermaid
graph LR
    ResourceLoader定位 --> BeanDefinition加载与解析
    BeanDefinition加载与解析 --> BeanDefinitionRegistry注册
```

### BeanDefinition的ResourceLoader定位
```mermaid
sequenceDiagram
    FileSystemXmlApplicationContext ->> AbstractXmlApplicationContext: refresh()
    AbstractXmlApplicationContext ->> AbstractXmlApplicationContext: obtainFreshBeanFactory()

    AbstractXmlApplicationContext ->> AbstractRefreshableApplicationContext: refreshBeanFactory()
    AbstractRefreshableApplicationContext ->> AbstractRefreshableApplicationContext: createBeanFactory();

    %% 加载
    AbstractRefreshableApplicationContext ->> AbstractXmlApplicationContext: loadBeanDefinitions();

    %% 资源定位
    AbstractRefreshableApplicationContext ->> FileSystemResource: getResourceByPath()
    FileSystemResource -->> FileSystemXmlApplicationContext: FileSystemResource
```
### BeanDefinition的载入于解析

```mermaid
sequenceDiagram
    AbstractApplicationContext ->> AbstractRefreshableApplicationContext: refresh()

    AbstractRefreshableApplicationContext ->> DefaultListableBeanFactory: createBeanFactory()

    AbstractRefreshableApplicationContext ->> XmlWebApplicationContext: loadBeanDefinitions()
    XmlWebApplicationContext ->> XmlBeanDefinitionReader: loadBeanDefinitions()

    XmlBeanDefinitionReader ->> BeanDefinitionParserDelegate: getResourceByPath()

    BeanDefinitionParserDelegate -->> AbstractApplicationContext: BeanDefinitionHolder
```

### BeanDefinition在IoC容器中的注册

