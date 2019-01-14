org.springframework.beans.factory.config.BeanDefinition

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