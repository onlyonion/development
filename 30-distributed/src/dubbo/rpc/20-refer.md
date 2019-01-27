## refer 时序
![refer](../../../img/dubbo-service-refer-sequence.png)

## refer 过程
1. ReferenceBean.getObject()
2. ReferenceConfig.init() ConsumerConfig MethodConfig 
3. ReferenceConfig.createProxy()
4. Protocol.refer() 得到 Invoker
    - InjvmProtocol
    - DubboProtocol Cluster.join()
5. ProxyFactory.getProxy(invoker)


```mermaid
graph LR
    subgraph config
        %% 1. 服务配置
       config["处理配置"]
    end

    subgraph refer
        config  --> decide{"服务引用方式"}
        %% 2. injvm, p2p, registries
        decide --> injvm["本地引用"]
        decide --> remote{"远程引用"}

        remote --> p2p["直连方式"]
        remote --> registries["注册中心方式"]

        p2p --> cluster["集群合并"]
        registries --> cluster["集群合并"]
    end

    subgraph invoker
        %% 3. invoker
        injvm --> invoker["可执行体"]
        cluster --> invoker
    end

    subgraph proxy
        %% 4. 生成代理
        invoker --> proxyFactory["代理类"]
    end
```