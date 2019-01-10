org.springframework.context.support.FileSystemXmlApplicationContext

```
FileSystemXmlApplicationContext
    AbstractXmlApplicationContext
        AbstractRefreshableConfigApplicationContext
            AbstractRefreshableApplicationContext
                AbstractApplicationContext
                    DefaultResourceLoader
```


```mermaid
graph LR
    ResourceLoader定位 --> BeanDefinition加载与解析
    BeanDefinition加载与解析 --> BeanDefinitionRegistry注册
```

#### BeanDefinition的ResourceLoader定位
```mermaid
sequenceDiagram
    FileSystemXmlApplicationContext ->> AbstractRefreshableApplicationContext: refreshBeanFactory()

    AbstractRefreshableApplicationContext ->> AbstractRefreshableApplicationContext: createBeanFactory();

    %% 加载
    AbstractRefreshableApplicationContext ->> AbstractXmlApplicationContext: loadBeanDefinitions();

    %% 资源定位
    AbstractRefreshableApplicationContext ->> FileSystemResource: getResourceByPath()
    FileSystemResource -->> FileSystemXmlApplicationContext: FileSystemResource
```
#### BeanDefinition的载入于解析

```mermaid
sequenceDiagram
    AbstractApplicationContext ->> AbstractRefreshableApplicationContext: refresh()

    AbstractRefreshableApplicationContext ->> DefaultListableBeanFactory: createBeanFactory();
    AbstractRefreshableApplicationContext ->> XmlBeanDefinitionReader: loadBeanDefinitions();

    XmlBeanDefinitionReader ->> BeanDefinitionParserDelegate: getResourceByPath()

    BeanDefinitionParserDelegate -->> AbstractApplicationContext: BeanDefinitionHolder
```

#### BeanDefinition在IoC容器中的注册

