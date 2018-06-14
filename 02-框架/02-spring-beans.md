Spring bean生命周期
## 1.BeanFactoyPostProcessor实例化

## 2.Bean实例化

Bean实例化，然后通过某些BeanFactoyPostProcessor来进行依赖注入
Setter注入，执行Bean的属性依赖注入

## 3.BeanPostProcessor

调用Spring内置的BeanPostProcessor负责调用Bean实现的接口: BeanNameAware, BeanFactoryAware, ApplicationContextAware等等
调用完后才会调用自己配置的BeanPostProcessor

## 4.Bean初始化

如果配置有实现BeanPostProcessor的Bean，那么调用它的postProcessBeforeInitialization方法
如果Bean有实现InitializingBean接口那么对这些Bean进行调用
如果Bean配置有init属性，那么调用它属性中设置的方法
如果配置有实现BeanPostProcessor的Bean，那么调用它的postProcessAfterInitialization方法

## 5.Bean销毁阶段

调用DisposableBean接口的destory方法
 调用Bean定义的destory方法
 
