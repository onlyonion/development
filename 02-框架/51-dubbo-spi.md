
* spi
* proxy

## Service Provider Interface

JDK的SPI机制

1. 在classpath的META-INF/services/目录下创建名称为服务接口的全限定名(package+className)的文件，文件编码必须为UTF-8，文件内容为服务接口实现类的全限定名，一个文件内可以定义多个实现类，按行分开即可。
2. 服务接口实现类必须有一个无参的构造方法。
3. 使用java.util包下的ServiceLoader类的load方法动态加载接口的实现类。

ServiceLoader实现了Iterable接口，可以遍历每一个服务提供者，并调用它的服务。

```
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


