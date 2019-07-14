[Mybatis使用的设计模式](https://blog.csdn.net/u012387062/article/details/54719114)

[mybatis源码中的设计模式解读](https://blog.csdn.net/qq_16713463/article/details/78110354)


### 简单工厂
MapperProxyFactory

### 工厂方法
定义一个用于创建对象的接口，让子类决定实例化哪一个类。Factory Method 使一个类的实例化延迟到其子类。

* reflection
  * ReflectorFactory
  * ObjectFactory
  * ObjectWrapperFactory
* datasource
  * DataSourceFactory
* executor
  * Executor
* transaction
  * TransactionFactory
* session
  * SqlSessionFactory

### 单例
保证一个类仅有一个实例，并提供一个访问它的全局访问点。

* ErrorContext
* LogFactory

### 建造者模式
将一个复杂对象的**构建**与它的**表示**分离，使得同样的构建过程可以创建不同的表示。

* BaseBuilder
  * XMLConfigBuilder
  * XMLMapperBuilder
  * XMLStatementBuilder
  * SqlSourceBuilder
* MapperAnnotationBuilder
* SqlSessionFactoryBuilder

### 适配器模式
将一个类的接口转换成客户希望的另外一个接口。Adapter 模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。 

Log、LogFactory

### 装饰模式
动态地给一个对象添加一些额外的职责。就增加功能来说，Decorator 模式相比生成子类更为灵活。 

最明显的就是cache包下面的实现Cahe、LoggingCache、LruCache、TransactionalCahe...等

### 动态代理
为其他对象提供一种代理以控制对这个对象的访问。

* plugin
  * Interpreter、InterpreterChain、Plugin、Invocation
* mybatis-spring
  * SqlSessionInterceptor

### 策略模式
定义一系列的算法,把它们一个个封装起来, 并且使它们可相互替换。本模式使得算法可独立于使用它的客户而变化。

* executor
  * Executor 一些列的算法接口
    * CacheExecutor
    * BaseExecutor SimpleExecutor、ReuseExecutor、BatchExecutor
  * key
    * KeyGenerator
      * NoKeyGenerator
      * SelectKeyGenerator
      * Jdbc3KeyGenerator

### 模板方法
定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。TemplateMethod 使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。

* executor
  * Executor
    * BaseExecutor 算法骨架
      * SimpleExecutor、ReuseExecutor、BatchExecutor 一些步骤延迟到子类

### 责任链模式
Interceptor、InterceptorChain

### 解释器模式
OgnlCache

