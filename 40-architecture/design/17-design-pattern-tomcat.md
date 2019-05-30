
[Tomcat 设计模式总结(Tomcat源代码阅读系列之八)](https://blog.csdn.net/jiaomingliang/article/details/47426683)

### 适配器

### 装饰器

### 代理

### 外观
在Tomcat中对于Request,Response,StandardSession,ApplicationContext,StandardWrapper都采用了外观模式

### 策略模式

### 模板方法
Tomcat中关于生命周期管理的地方很好应用了模板方法模式，在一个组件的生命周期中都会涉及到init(初始化)，start（启动），stop(停止)，destory（销毁）

### 观察者
Tomcat抽象了一个LifecycleSupport的类，而所有需要生命周期管理的组件通过LifecycleSupport类通知对某个生命周期事件感兴趣的观察者，
而所有的观察者都需要实现LifecycleListener。

EventObject
LifecycleSupport

### 职责链
当我们系统在处理某个请求的时候，请求需要经过很多个节点进行处理，每个节点只关注自己的应该做的工作，做完自己的工作以后，
将工作转给下一个节点进行处理，直到所有节点都处理完毕。如快递、路由器。

### 命令模式
命令模式将请求封装为一个命令，将命令发送者和命令接受者解耦，并且所有命令对客户端来说都有统一的调用接口，使用命令模式还可以支持命令的撤销操作，
在很多GUI程序中大量使用了此模式。

命令模式在Tomcat中主要是应用在对请求的处理过程中，Tomcat的实现中，根据它支持两种协议AJP和Http,而在具体的IO实现中，又分为Java同步阻赛IO,Java同步非祖塞IO，
以及采用APRApache Portable Runtime 支持库,因此Tomcat统一了org.apache.coyote.Processor接口
