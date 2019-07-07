
## define
```plantuml
@startuml

interface AopProxy
interface InvocationHandler
AopProxy <|.. JdkDynamicAopProxy
InvocationHandler <|.. JdkDynamicAopProxy

class JdkDynamicAopProxy {
    - final AdvisedSupport advised
}

class AdvisedSupport {
    TargetSource targetSource
    AdvisorChainFactory advisorChainFactory
    - List<Advisor> advisors
    .. 获得拦截器和动态拦截通知 ..
    + List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass)
}
JdkDynamicAopProxy o-- AdvisedSupport

@enduml
```


## JdkDynamicAopProxy.invoke()
* 特殊方法处理
* 获得拦截器链
    - 拦截器为空，直接调用
    - 拦截器不为空，执行拦截器链
* 结果处理、返回

```mermaid
sequenceDiagram

    [Actor]->>[JdkDynamicAopProxy]:invoke(proxy, method, args)
    
    %% 1. 特殊方法处理
    alt equals,hashCode特殊方法处理
        [JdkDynamicAopProxy]->>[JdkDynamicAopProxy]:特殊方法处理
    else 透明的
        [JdkDynamicAopProxy]->>[AopUtils]:工具类invokeJoinpointUsingReflection
    end
    
    %% 2. 获得拦截器链
    [JdkDynamicAopProxy]->>[AdvisedSupport]:getInterceptorsAndDynamicInterceptionAdvice(method, targetClass)
    
    alt 拦截器链为空
        
        %% 2.1 反射工具类 method.invoke(obj, args)
        [JdkDynamicAopProxy]->>[AopUtils]:工具类invokeJoinpointUsingReflection
        
    else 不为空，拦截器链调用
    
        %% 2.2 创建反射方法调用器，执行拦截器链
        [JdkDynamicAopProxy]->>[ReflectiveMethodInvocation]:创建反射方法调用器
        [ReflectiveMethodInvocation]->>[ReflectiveMethodInvocation]:proceed() 继续执行
        
    end
    
    %% 3. 结果处理、返回
    [JdkDynamicAopProxy]-->>[Actor]:返回结果
```