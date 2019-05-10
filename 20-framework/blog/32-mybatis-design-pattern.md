[Mybatis使用的设计模式](https://blog.csdn.net/u012387062/article/details/54719114)

[mybatis源码中的设计模式解读](https://blog.csdn.net/qq_16713463/article/details/78110354)

### 建造者模式
BaseBuilder、XMLConfigBuilder、XMLMapperBuilder、XMLStatementBuilder、SqlSourceBuilder

### 工厂方法
SqlSessionFactory
ObjectFactory

### 单例
ErrorContext和LogFactory

### 适配器模式
Log、LogFactory

### 模板方法
BaseExecutor、SimpleExecutor

### 装饰模式
最明显的就是cache包下面的实现Cahe、LoggingCache、LruCache、TransactionalCahe...等


### 动态代理
Interpreter、InterpreterChain、Plugin、Invocation

### 责任链模式
Interceptor、InterceptorChain
