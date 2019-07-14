## mybatis-spring

## package
org.mybatis.spring
```
annotation
    MapperScan
    MapperScannerRegistrar
batch
    MyBatisBatchItemWriter
    MyBatisCursorItemReader
    MyBatisPagingItemReader
config
    MapperScannerBeanDefinitionParser
    NamespaceHandler
mapper
    ClassPathMapperScanner
    MapperFactoryBean
    MapperScannerConfigurer
support
    SqlSessionDaoSupport
transaction
    SpringManagedTransaction
    SpringManagedTransactionFactory
MyBatisExceptionTranslator
MyBatisSystemException
SqlSessionFactoryBean
SqlSessionHolder
SqlSessionTemplate
SqlSessionUtils
```


## cache
spring结合mybatis后，一级缓存作用：
* 在未开启事物的情况之下，每次查询，spring都会关闭旧的sqlSession而创建新的sqlSession,因此此时的一级缓存是没有启作用的
* 在开启事物的情况之下，spring使用threadLocal获取当前资源绑定同一个sqlSession，因此此时一级缓存是有效的