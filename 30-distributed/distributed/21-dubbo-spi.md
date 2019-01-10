
* spi
* proxy

## Service Provider Interface

JDK的SPI机制

1. 在classpath的META-INF/services/目录下创建名称为服务接口的全限定名(package+className)的文件，文件编码必须为UTF-8，文件内容为服务接口实现类的全限定名，一个文件内可以定义多个实现类，按行分开即可。
2. 服务接口实现类必须有一个无参的构造方法。
3. 使用java.util包下的ServiceLoader类的load方法动态加载接口的实现类。

ServiceLoader实现了Iterable接口，可以遍历每一个服务提供者，并调用它的服务。

```java
	private void delivery(String companyName, String productName) {
		ServiceLoader<Delivery> loader = ServiceLoader.load(Delivery.class);
		Iterator<Delivery> iterator = loader.iterator();
		while(iterator.hasNext()){
			Delivery delivery = iterator.next();
			// 匹配指定的快递公司
			if(delivery.match(companyName)){
				delivery.deliveryProduct(productName);
				break;
			}
		}
	}
```

Dubbo对SPI的改进

1. JDK的SPI机制会一次性实例化所有服务提供者实现，如果有提供者的初始化很耗时，但并不会使用会很耗费资源。Dubbo则只存储了所有提供者的Class对象，实际使用时才构造对象。
2. JDK的SPI机制只在配置文件中记录了实现类的全限定名，并没有定义一个配置名。而Dubbo的服务接口往往提供多个实现方式，需要在每个服务接口中定义一个匹配方法去选择要使用哪种实现方式。这种方式不利于框架的扩展，因而规定在提供者实现类的配置文件中对每个实现类定义一个配置名，用"="隔开，形成key-value的方式。
3. 增加了对服务接口的IOC和AOP的支持，服务接口内的其他服务接口成员直接通过SPI的方式加载注入。


Dubbo作为一个微内核+插件的框架设计，其内核就是基于SPI机制动态地组装插件。插件都是以接口的方式定义在框架中，每个接口提供的服务也称为扩展点，表示每个服务接口都可以根据不同的条件进行动态扩展，Dubbo的扩展点接口以@SPI注解标识。Dubbo自身的功能也是通过扩展点实现的，也就是Dubbo的所有功能点都可以被用户自定义扩展所代替。


## Proxy

### 1. ProxyFactory

### 2. getProxy方法

消费方基于调用接口获取代理对象，使用动态代理，动态代理有多种方式，Dubbo默认支持了JDK和javassist两种。

### 3. getInvoker方法

Invoker，Dubbo的核心模型，代表一个可执行体。在我理解，它就是**一个指向最终服务实现的执行路径**。
在Dubbo的实现中，提供方的服务实现首先被包装成一个ProxyInvoker(代理执行器)，然后这个ProxyInvoker被Filter封装成链式结构，被Listener包装，被Cluster封装，而消费方的Invoker也是一个执行远程调用的执行器，区别在于执行路径的不同。


## dubbo设计模式

* 工厂模式
* 装饰者模式
* 观察者模式
* 动态代理模式

### 1. 工厂模式
ServiceConfig 中有个字段，代码是这样的：
private static final Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();Dubbo 里有很多这种代码。这也是一种工厂模式，只是实现类的获取采用了jdk spi 的机制。这么实现的优点是可扩展性强，想要扩展实现，只需要在classpath 下增加个文件就可以了，代码零侵入。另外，像上面的Adaptive 实现，可以做到调用时动态决定调用哪个实现，但是由于这种实现采用了动态代理，会造成代码调试比较麻烦，需要分析出实际调用的实现类。
### 2. 装饰器模式
Dubbo 在启动和调用阶段都大量使用了装饰器模式。以Provider 提供的调用链为例，具体的调用链代码是在ProtocolFilterWrapper 的buildInvokerChain 完成的，具体是将注解中含有group=provider 的Filter 实现，按照order 排序，最后的调用顺序是EchoFilter-》ClassLoaderFilter-》GenericFilter-》ContextFilter-》 ExceptionFilter-》TimeoutFilter-》MonitorFilter-》TraceFilter。更确切地说，这里是装饰器和 责任链模式的混合使用。例如，EchoFilter 的作用是判断是否是回声测试请求，是的话直接返回内容，这是一种责任链的体现。而像ClassLoaderFilter 则只是在主功能上添加了功能，更改当前线程的ClassLoader，这是典型的装饰器模式。
### 3. 观察者模式
Dubbo 的provider 启动时，需要与注册中心交互，先注册自己的服务，再订阅自己的服务，订阅时，采用了观察者模式，开启一个listener。注册中心会每5 秒定时检查是否有服务更新，如果有更新，向该服务的提供者发送一个notify 消息，provider 接受到notify消息后，即运行NotifyListener 的notify 方法，执行监听器方法。
### 4. 动态代理模式
Dubbo 扩展jdk spi 的类ExtensionLoader 的Adaptive 实现是典型的动态代理实现。Dubbo 需要灵活地控制实现类，即在调用阶段动态地根据参数决定调用哪个实现类，所以采用先生成代理类的方法，能够做到灵活的调用。生成代理类的代码是 ExtensionLoader的createAdaptiveExtensionClassCode 方法。代理类的主要逻辑是，获取URL 参数中指定参数的值作为获取实现类的key。

