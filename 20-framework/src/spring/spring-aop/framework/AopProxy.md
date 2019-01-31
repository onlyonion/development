# AopProxy

## 1. AopProxy 定义
```java
public interface AopProxy {
	Object getProxy();
	Object getProxy(ClassLoader classLoader);
}
```

## hierachy

```
AopProxy (org.springframework.aop.framework)
    JdkDynamicAopProxy (org.springframework.aop.framework)
    CglibAopProxy (org.springframework.aop.framework)
        ObjenesisCglibAopProxy (org.springframework.aop.framework)
```

## 2. AopProxy 类图
```yuml
// {type:class}

[AopProxy]
[JdkDynamicAopProxy]
[CglibAopProxy]

// 1. jdk实现代理
[AopProxy]^-.-[JdkDynamicAopProxy]
[InvocationHandler]^-.-[JdkDynamicAopProxy]
[JdkDynamicAopProxy]++-.-[AdvisedSupport]

// 2. cglib实现代理
[AopProxy]^-.-[CglibAopProxy]
[CglibAopProxy]++-.-[AdvisedSupport]

// 已通知的支持
[ProxyConfig]^-[AdvisedSupport]
[Advised]^-.-[AdvisedSupport]
[TargetClassAware]^-[Advised]
```
## 3. 动态代理实现：jdk与cglib

### 3.1 JdkDynamicAopProxy
org.springframework.aop.framework.JdkDynamicAopProxy

Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)

#### jdk动态代理类图
```yuml
// {type:class}

// 代理类对应目标类方法的实现
// super.h.invoke(this, m3, (Object[])null)

// 代理类继承Proxy
[Proxy]^-[$ProxyXXX]

// Proxy持有调用处理器
[Proxy]++-[InvocationHandler]

// 代理类的方法
[$ProxyXXX]++1-*[Method]

```

#### JdkDynamicAopProxy调用

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
    
    %% 3. 返回
    [JdkDynamicAopProxy]-->>[Actor]:返回结果
```

### 3.2 CglibAopProxy
org.springframework.aop.framework.CglibAopProxy

#### CglibMethodInvocation
org.springframework.aop.framework.CglibAopProxy.CglibMethodInvocation

DynamicAdvisedInterceptor

#### ObjenesisCglibAopProxy

## 4. 获得拦截器链

### 4.1 生成拦截器链
List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);

```mermaid
sequenceDiagram
    %% 1. 动态代理 getInterceptorsAndDynamicInterceptionAdvice(method, targetClass)
    JdkDynamicAopProxy->>AdvisedSupport:获得拦截器和动态通知(method, targetClass)
    
    %% 2. 默认拦截器链工厂生产 getInterceptorsAndDynamicInterceptionAdvice(config, method, targetClass)
    AdvisedSupport->>DefaultAdvisorChainFactory:获得拦截器和动态通知(config, method, targetClass)
    
    %% 3. 从已通知的配置项中遍历切面，进行方法匹配或者类过滤，匹配的添加到拦截器链中
    DefaultAdvisorChainFactory->>Advised:config.getAdvisors()
    loop
        alt [advisor instanceof PointcutAdvisor]
            
            %% 3.1 强转、判断；切点的类过滤器匹配并且方法匹配器匹配
            opt 是前置过滤或者 切面的切点的类过滤器 匹配
                DefaultAdvisorChainFactory->>AdvisorAdapterRegistry:getInterceptors()
                opt 方法匹配器匹配
                    alt 方法匹配器是运行时
                        loop
                            DefaultAdvisorChainFactory->>InterceptorAndDynamicMethodMatcher:封装成新的对象
                            DefaultAdvisorChainFactory->>List:添加拦截通知
                        end
                    else
                        DefaultAdvisorChainFactory->>List:添加拦截通知
                    end
                end
            end
        else [advisor instanceof IntroductionAdvisor]
            %% 3.2 强转、判断；类过滤器匹配
            opt 是前置过滤或者 切面的类过滤器 匹配
                DefaultAdvisorChainFactory->>AdvisorAdapterRegistry:getInterceptors()
                DefaultAdvisorChainFactory->>List:添加拦截通知
            end
        else
            %% 3.3
            DefaultAdvisorChainFactory->>AdvisorAdapterRegistry:getInterceptors()   
            DefaultAdvisorChainFactory->>List:添加拦截通知
        end
    end
    
```
### 4.2 AdvisorChainFactory类图
```yuml
// {type:class}

[AdvisorChainFactory]^-.-[DefaultAdvisorChainFactory]
[AdvisorChainFactory]uses-.->[Advised]

// 已通知的切面，包含很多通知器（切面）
[Advised]++-[Advisor]

// Advisor通知器（aop切面），持有通知（在何时）和切点（在哪里）
[Advisor]++-[Advice]
[Advisor]^-[PointcutAdvisor]
[PointcutAdvisor]++-[Pointcut]

// 默认切面拦截器链工厂
[DefaultAdvisorChainFactory]uses-.->[GlobalAdvisorAdapterRegistry]
[DefaultAdvisorChainFactory]uses-.->[GlobalAdvisorAdapterRegistry]

// 单例 全局切面适配注册表
[GlobalAdvisorAdapterRegistry]++-[AdvisorAdapterRegistry]
[AdvisorAdapterRegistry]^-.-[DefaultAdvisorAdapterRegistry]

[DefaultAdvisorAdapterRegistry]++-[AdvisorAdapter]

```

### 4.3 AdvisorAdapter适配器

```yuml
// {type:class}

[AdvisorAdapter||+supportsAdvice(Advice);+getInterceptor(Advisor)]

// 3个实现 方法执行之前、抛出异常时、返回时
[AdvisorAdapter]^-.-[MethodBeforeAdviceAdapter]
[MethodBeforeAdviceAdapter]uses-.->[MethodBeforeAdviceInterceptor]

[AdvisorAdapter]^-.-[ThrowsAdviceAdapter]
[ThrowsAdviceAdapter]uses-.->[ThrowsAdviceInterceptor]

[AdvisorAdapter]^-.-[AfterReturningAdviceAdapter]
[AfterReturningAdviceAdapter]uses-.->[AfterReturningAdviceInterceptor]

// 方法依赖
[AdvisorAdapter]uses-.->[Advice]
[AdvisorAdapter]uses-.->[Advisor]

// Advice Interceptor MethodInterceptor
[MethodInterceptor]^-.-[MethodBeforeAdviceInterceptor]
[MethodInterceptor]^-.-[ThrowsAdviceAdapter]
[MethodInterceptor]^-.-[AfterReturningAdviceInterceptor]
```

## 5. 反射方法调用器
