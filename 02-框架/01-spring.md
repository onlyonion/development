
## Spring的优点

1.	轻量：Spring 是轻量的，基本的版本大约2MB。
2.	控制反转：Spring通过控制反转实现了松散耦合，对象们给出它们的依赖，而不是创建或查找依赖的对象们。
3.	面向切面的编程(AOP)：Spring支持面向切面的编程，并且把应用业务逻辑和系统服务分开。
4.	容器：Spring 包含并管理应用中对象的生命周期和配置。
5.	MVC框架：Spring的WEB框架是个精心设计的框架，是Web框架的一个很好的替代品。
6.	事务管理：Spring 提供一个持续的事务管理接口，可以扩展到上至本地事务下至全局事务（JTA）。
7.	异常处理：Spring 提供方便的API把具体技术相关的异常（比如由JDBC，Hibernate or JDO抛出的）转化为一致的unchecked 异常

## 组成模块

1.	spring-core(asm, cglib, core(annotation, convert, env, io, serializer, task, type))
2.	spring-beans
3.	spring-context, spring-context-support(cache, mail, scheduling, ui)
4.	spring-aop, (aopalliance)
5.	spring-web, spring-webmvc
6.	spring-tx, spring-jdbc, spring-orm-hibernate, (mybatis-spring)
7.	spring-orm
8.	spring-jms
9.	spring-expression

![spring-jars](./img/spring-jars.jpg "spring-jars")



### 装配

装配，或bean 装配是指在Spring 容器中把bean组装到一起，前提是容器需要知道bean的依赖关系，如何通过依赖注入来把它们装配到一起 

*	no：默认的方式是不进行自动装配，通过显式设置ref 属性来进行装配。
*	byName：通过参数名 自动装配，Spring容器在配置文件中发现bean的autowire属性被设置成byname，之后容器试图匹配、装配和该bean的属性具有相同名字的bean。
*	byType:：通过参数类型自动装配，Spring容器在配置文件中发现bean的autowire属性被设置成byType，之后容器试图匹配、装配和该bean的属性具有相同类型的bean。如果有多个bean符合条件，则抛出错误。
*	constructor：这个方式类似于byType， 但是要提供给构造器参数，如果没有确定的带参数的构造器参数类型，将会抛出异常。
*	autodetect：首先尝试使用constructor来自动装配，如果无法工作，则使用byType方式。


## 模块解析

### spring-core
asm, cglib, core, lang, objenesis, util

### spring-beans

beans.factory

BeanDefinition
BeanFactory
FactoryBean

InitializingBean
DisposableBean

Aware
BeanFactoryPostProcessor
BeanPostProcessor

``` java

public interface BeanFactoryPostProcessor {

	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
	
}

public interface BeanPostProcessor {

	Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

	Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}

```

beans.propertyeditors

beans.support

### spring-context

cache
context
ejb
format
jmx
jndi
remoting
scheduling
scripting
stereotype
ui
validation

### spring-context-support
cache, mail, scheduling, ui

> cache

cache, cacheManager

*  ehcache
*  guava
*  jcache
*  transaction

> mail

javamail

> scheduling

* commonj
* quartz

> ui

* freemarker
* jasperreports
* velocity

### spring-expression

### spring-dao
Template 和 Callback

### spring-tx
dao, jca, transaction

PlatformTransactionManager
AbstractPlatformTransactionManager
TransactionStatus
TransactionTemplate
TransactionCallback

### spring-jdbc
JdbcTemplate

### spring-orm
hibernate3, hibernate4, hibernate5, jdo, jpa


### spring-web
http: client, server, converter(feed, json, protobuf, xml)

remoting: caucho, httpinvoker, jaxws

web: accept, bind, client, context, cors, filter, jsf, method, multipart, util

### spring-webmvc

HandlerAdapter
HandlerExecutionChain
HandlerMapping
HandlerInterceptor
ViewResovlver
View

DispatcherServlet -> FrameworkServlet -> HttpServletBean



