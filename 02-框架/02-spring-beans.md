
# spring 启动

1.	ApplicationContext	读取配置文件（资源文件定位、解析）
2.	Configuration
3.	instantiate init BeanFactory, BeanFactoryPostProcessor工厂后处理器
4.	Registry 注册Bean后处理器；将BeanDefinition注册到BeanDefinitionRegistry中
5.	MessageSource
6.	ApplicationEventMulticaster
7.	SingletoneBeanInit	单例初始化
8.	Strategy	初始化策略

![spring-beanfactory-init.png](./img/spring-beanfactory-init.png "spring-beanfactory-init.png") 

```java

@Override
	public void refresh() throws BeansException, IllegalStateException {
		synchronized (this.startupShutdownMonitor) {
			// Prepare this context for refreshing.
			prepareRefresh();

			// 初始化BeanFactory
			// Tell the subclass to refresh the internal bean factory.
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			// Prepare the bean factory for use in this context.
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				postProcessBeanFactory(beanFactory);

				// 调用工厂后处理器
				// Invoke factory processors registered as beans in the context.
				invokeBeanFactoryPostProcessors(beanFactory);

				// 注册Bean后处理器
				// Register bean processors that intercept bean creation.
				registerBeanPostProcessors(beanFactory);

				// 初始化容器的国际化信息资源
				// Initialize message source for this context.
				initMessageSource();

				// 初始化应用上下文事件广播器
				// Initialize event multicaster for this context.
				initApplicationEventMulticaster();

				// 初始化其他特殊的Bean：这是一个钩子方法，子类可以借助这个钩子方法执行一些特殊的操作
				// Initialize other special beans in specific context subclasses.
				onRefresh();

				// 注册事件监听器；（观察者模式中的观察者角色）
				// Check for listener beans and register them.
				registerListeners();

				// 初始化singleton的Bean：实例化所有singleton的Bean，并将它们放入Spring容器的缓存中；
				// 这就是和直接在应用中使用BeanFactory的区别之处，在创建ApplicationContext对象时，不仅创建了一个BeanFactory对象，并且还应用它实例化所有单实例的bean。
				//（在spring的配置文件中，bean默认为单例，除非在bean的配置中显式指定scope="prototype"）
				// Instantiate all remaining (non-lazy-init) singletons.
				finishBeanFactoryInitialization(beanFactory);
				
				// 发布上下文刷新事件：在此处时容器已经启动完成，发布容器refresh事件（ContextRefreshedEvent）
				// Last step: publish corresponding event.
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}
	
```

### analysis
1. 初始化BeanFactory BeanDefinition -> BeanDefinitionRegistry
2. 调用工厂后处理器：根据反射机制从BeanDefinitionRegistry中找出所有BeanFactoryPostProcessor类型的Bean，并调用其postProcessBeanFactory()接口方法；
3. 注册Bean后处理器：根据反射机制从BeanDefinitionRegistry中找出所有BeanPostProcessor类型的Bean，并将它们注册到容器Bean后处理器的注册表中；
4. 初始化消息源：初始化容器的国际化信息资源；
5. 初始化应用上下文事件广播器；（观察者模式中的具体主题角色，持有观察者角色的集合，称为注册表）
6. 初始化其他特殊的Bean：这是一个钩子方法，子类可以借助这个钩子方法执行一些特殊的操作
7. 注册事件监听器；（观察者模式中的观察者角色）
8. 初始化singleton的Bean：实例化所有singleton的Bean，并将它们放入Spring容器的缓存中；这就是和直接在应用中使用BeanFactory的区别之处，在创建ApplicationContext对象时，不仅创建了一个BeanFactory对象，并且还应用它实例化所有单实例的bean。（在spring的配置文件中，bean默认为单例，除非在bean的配置中显式指定scope="prototype"）
9. 发布上下文刷新事件：在此处时容器已经启动完成，发布容器refresh事件（ContextRefreshedEvent）

## Spring bean作用域

1.	single	每个spring容器一个实例，不是线程安全
2.	prototype 使用一次创建一次
3.	request	http请求（springmvc）
4.	session	http会话（springmvc）
5.	globalSession

![spring-beans-scope](./img/spring-beans-scope.png "spring-beans-scope") 

## Spring bean生命周期

对象的生命周期：创建（实例化-初始化）-使用-销毁

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

## spring-beans-life.png

![spring-beans-life](./img/spring-beans-life.png "spring-beans-life") 
 
## spring-beans-life-00.png

![spring-beans-life](./img/spring-beans-life-00.png "spring-beans-life") 

## spring-beans-life-01.png 

![spring-beans-life](./img/spring-beans-life-01.png "spring-beans-life") 



