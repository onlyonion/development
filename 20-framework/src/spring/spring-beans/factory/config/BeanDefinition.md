org.springframework.beans.factory.config.BeanDefinition

## 1. 定义及其实现类

```
BeanDefinition (org.springframework.beans.factory.config)
    AnnotatedBeanDefinition (org.springframework.beans.factory.annotation)
        ScannedGenericBeanDefinition (org.springframework.context.annotation)
        ConfigurationClassBeanDefinition in ConfigurationClassBeanDefinitionReader (org.springframework.context.annotation)
        AnnotatedGenericBeanDefinition (org.springframework.beans.factory.annotation)
    AbstractBeanDefinition (org.springframework.beans.factory.support)
        RootBeanDefinition (org.springframework.beans.factory.support)
            ConfigurationClassBeanDefinition in ConfigurationClassBeanDefinitionReader (org.springframework.context.annotation)
        ChildBeanDefinition (org.springframework.beans.factory.support)
        GenericBeanDefinition (org.springframework.beans.factory.support)
            ScannedGenericBeanDefinition (org.springframework.context.annotation)
            AnnotatedGenericBeanDefinition (org.springframework.beans.factory.annotation)
```

![beanDefinition](../../../../../img/spring-idea-BeanDefinition.png)

## 2. 类图
* AnnotatedBeanDefinition
* AbstractBeanDefinition
    - RootBeanDefinition
    - ChildBeanDefinition
    - GenericBeanDefinition
        - ScannedGenericBeanDefinition
        - AnnotatedGenericBeanDefinition

```yuml
// {type:class}

[BeanDefinition]<-.-[AbstractBeanDefinition{bg:wheat}]
[AbstractBeanDefinition]^-[ChildBeanDefinition]

// 抽象实现
[AbstractBeanDefinition]^-[GenericBeanDefinition{bg:wheat}]
[GenericBeanDefinition]^-[AnnotatedGenericBeanDefinition{bg:yellow}]
[GenericBeanDefinition]^-[ScannedGenericBeanDefinition{bg:violet}]

// 根
[AbstractBeanDefinition]^-[RootBeanDefinition]
[BeanDefinition]^-[AnnotatedBeanDefinition]
[AnnotatedBeanDefinition]<-.-[AnnotatedGenericBeanDefinition]
[AnnotatedBeanDefinition]<-.-[ScannedGenericBeanDefinition]

```