org.springframework.web.method.support.InvocableHandlerMethod

## hierarchy
```
HandlerMethod (org.springframework.web.method)
    InvocableHandlerMethod (org.springframework.web.method.support)
        ServletInvocableHandlerMethod (org.springframework.web.servlet.mvc.method.annotation)
            ConcurrentResultHandlerMethod in ServletInvocableHandlerMethod (org.springframework.web.servlet.mvc.method.annotation)
```
## define
```plantuml
@startuml

''''''''''''''''''''''''HandlerMethod''''''''''''''''''''''''
class HandlerMethod
HandlerMethod +-- HandlerMethodParameter
HandlerMethod +-- ReturnValueMethodParameter
HandlerMethodParameter ^-- ReturnValueMethodParameter

''''''''''''''''''''''''InvocableHandlerMethod''''''''''''''''''''''''
HandlerMethod ^-- InvocableHandlerMethod
class InvocableHandlerMethod

''''''''''''''''''''''''ServletInvocableHandlerMethod''''''''''''''''''''''''
InvocableHandlerMethod ^-- ServletInvocableHandlerMethod
class ServletInvocableHandlerMethod

HandlerMethodParameter ^-- ConcurrentResultMethodParameter 
ServletInvocableHandlerMethod +-- ConcurrentResultHandlerMethod
ServletInvocableHandlerMethod +-- ConcurrentResultMethodParameter

ServletInvocableHandlerMethod ^-- ConcurrentResultHandlerMethod


@enduml
```


## 桥接方法

桥接方法是 JDK 1.5 引入泛型后，为了使Java的泛型方法生成的字节码和 1.5 版本前的字节码相兼容，由编译器自动生成的方法。
可以通过Method.isBridge()方法来判断一个方法是否是桥接方法。
在字节码中桥接方法会被标记为ACC_BRIDGE和ACC_SYNTHETIC，其中ACC_BRIDGE用于说明这个方法是由编译生成的桥接方法，ACC_SYNTHETIC说明这个方法是由编译器生成，并且不会在源代码中出现。

一个子类在继承（或实现）一个父类（或接口）的泛型方法时，在子类中明确指定了泛型类型，那么在编译时编译器会自动生成桥接方法

org.springframework.core.BridgeMethodResolver

[java中什么是bridge method（桥接方法）](https://blog.csdn.net/mhmyqn/article/details/47342577 )