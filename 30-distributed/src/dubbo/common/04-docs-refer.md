## 2.服务引用原理
Dubbo 服务引用的时机有两个，第一个是在 
1. 饿汉式 Spring 容器调用 ReferenceBean 的 afterPropertiesSet 方法时引用服务。默认。
2. 懒汉式 在 2.ReferenceBean 对应的服务被注入到其他类中时引用

整个分析过程从 ReferenceBean 的 getObject 方法开始。当我们的服务被注入到其他类中时，Spring 会第一时间调用 getObject 方法，并由该方法执行服务引用逻辑。

1. 配置检查与收集工作
2. 根据收集到的信息决定服务用的方式
   * 引用本地 (JVM) 服务
   * 直联方式引用远程服务
   * 注册中心引用远程服务
3. 如果有多个注册中心，多个服务提供者，这个时候会得到一组 Invoker 实例，此时需要通过集群管理类 Cluster 将多个 Invoker 合并成一个实例。
4. 通过代理工厂类 (ProxyFactory) 为服务接口生成代理类，并让代理类去调用 Invoker 逻辑。

## 3.源码分析

### 3.1 处理配置

### 3.2 引用服务
createProxy 似乎只是用于创建代理对象的。但实际上并非如此，该方法还会调用其他方法构建以及合并 Invoker 实例

1. 根据配置检查是否为本地调用，若是，则调用 InjvmProtocol 的 refer 方法生成 InjvmInvoker 实例。
2. 若不是，则读取直联配置项，或注册中心 url，并将读取到的 url 存储到 urls 中。若 urls 元素数量为1，则直接通过 Protocol 自适应拓展类构建 Invoker 实例接口。若 urls 元素数量大于1，即存在多个注册中心或服务直联 url。
3. 根据 url 构建 Invoker。然后再通过 Cluster 合并多个 Invoker，
4. 最后调用 ProxyFactory 生成代理类。

#### 3.2.1 创建 Invoker
Invoker 是 Dubbo 的核心模型，代表一个**可执行体**。
1. 在服务提供方，Invoker 用于调用服务提供类。
2. 在服务消费方，Invoker 用于执行远程调用。
3. Invoker 是由 Protocol 实现类构建而来。常用的实现类，RegistryProtocol 和 DubboProtocol。

#### 3.2.2 创建代理
Invoker 创建完毕后，接下来要做的事情是为服务接口生成代理对象。有了代理对象，即可进行远程调用。代理对象生成的入口方法为 ProxyFactory 的 getProxy