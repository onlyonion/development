java.lang.reflect.InvocationHandler

## hierarchy
```
InvocationHandler (java.lang.reflect)
    JdkDynamicAopProxy (org.springframework.aop.framework)
    InvokerInvocationHandler (com.alibaba.dubbo.rpc.proxy)
    RestHandler in RestAdapter (retrofit)
    MapperProxy (org.apache.ibatis.binding)
    SqlSessionInterceptor in SqlSessionTemplate (org.mybatis.spring)
    SqlSessionInterceptor in SqlSessionManager (org.apache.ibatis.session)
    SynthesizedAnnotationInvocationHandler (org.springframework.core.annotation)
    EarlySingletonInvocationHandler in AbstractFactoryBean (org.springframework.beans.factory.config)
    DeferredQueryInvocationHandler in SharedEntityManagerCreator (org.springframework.orm.jpa)
    SharedEntityManagerInvocationHandler in SharedEntityManagerCreator (org.springframework.orm.jpa)
    TransactionAwareInvocationHandler in TransactionAwareConnectionFactoryProxy (org.springframework.jca.cci.connection)
    PooledConnection (org.apache.ibatis.datasource.pooled)
    JdkProxyHandler (org.apache.commons.pool2.proxy)
    JSONObject (com.alibaba.fastjson)
```