# dubbo服务

## dubbo 异步调用

异步通讯对于服务端响应时间较长的方法是必须的，能够有效地利用客户端的资源，

```
<dubbo:reference id="xxx" ....>
    <dubbo:method name="method1" async="true" />
</dubbo:reference>
```

### 1、NIO future主动获取结果，返回结果放在RpcContext中

需要注意的是，由于RpcContext是单例模式，所以每次调用完后，需要保存一个Future实例；如：

```
      fooService.findFoo(fooId);
      Future<Foo> fooFuture = RpcContext.getContext().getFuture();
      barService.findBar(barId);
      Future<Bar> barFuture = RpcContext.getContext().getFuture();
      barService.findBar(barId);
      Bar bar = barFuture.get();
```

### 2、通过回调（Callback）参数

Callback并不是dubbo内部类或接口，而是由应用自定义的、实现了Serializable的接口；

分两步：1）服务提供者需在方法中声明Callback参数，其后在Service实现中显示地调用Callback的方法；

```
      <dubbo:service ..>
         <dubbo:method name="method1">
            <dubbo:argument index="1" callback="true" />  #标识第二个参数是callback类型
         </dubbo:method>
      </dubbo:service>
```

2）Callback接口的实现类在消费端，当方法发生调用时，消费端会自动export一个Callback服务，在Rpc调用完成后，不能立即结束线程。

```
      <dubbo:reference ...>
         <dubbo:method name="method1" async="true">
      </dubbo:reference>
```

### 3、事件通知（推荐）

 这种方式更简单，对服务提供方来讲是透明的，包括配置和代码上，均无需做任何改动。
消费端定义一个“通知者”的Spring Bean，指定方法的onreturn和onthrow事件action就可以。

```
      <bean id="notify" class="com.alibaba.dubbo.callback.implicit.NofifyImpl" />
      
      <dubbo:reference >
		<dubbo:method name="method1" async="true" onreturn="notify.onreturn" onthrow="notify.onthrow" />
      </dubbo:reference>
      
```