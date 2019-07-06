## 1.简介
Dubbo 服务导出过程始于 Spring 容器发布刷新事件，Dubbo 在接收到事件后，会立即执行服务导出逻辑。

整个逻辑大致可分为三个部分
1. 前置工作，主要用于检查参数，组装 URL。
2. 导出服务，包含导出服务到本地 (JVM)，和导出服务到远程两个过程。
3. 向注册中心注册服务，用于服务发现。

## 2.源码分析


服务导出的入口方法是 ServiceBean 的 onApplicationEvent。onApplicationEvent 是一个事件响应方法，该方法会在收到 Spring 上下文刷新事件后执行服务导出操作。

### 2.1 前置工作
Dubbo 使用 URL 作为配置载体，所有的拓展点都是通过 URL 获取配置
> 采用 URL 作为配置信息的统一格式，所有扩展点都通过传递 URL 携带配置信息。

#### 2.1.1 检查配置
前置工作主要包含两个部分，分别是配置检查，以及 URL 装配。
#### 2.1.2 多协议多注册中心导出服务
* doExportUrls()
* loadRegistries(true)

loadRegistries 方法主要包含如下的逻辑：
1. 检测是否存在注册中心配置类，不存在则抛出异常
2. 构建参数映射集合，也就是 map
3. 构建注册中心链接列表
4. 遍历链接列表，并根据条件决定是否将其添加到 registryList 中

#### 2.1.3 组装 URL
doExportUrlsFor1Protocol()

### 2.2 导出 Dubbo 服务
服务导出分为导出到本地 (JVM)，和导出到远程
* scope = none，不导出服务
* scope != remote，导出到本地
* scope != local，导出到远程

#### 2.2.1 Invoker 创建过程

> Invoker 是实体域，它是 Dubbo 的核心模型，其它模型都向它靠扰，或转换成它，它代表一个可执行体，可向它发起 invoke 调用，
它有可能是一个本地的实现，也可能是一个远程的实现，也可能一个集群实现。

Invoker 是由 ProxyFactory 创建而来，Dubbo 默认的 ProxyFactory 实现类是 JavassistProxyFactory。

#### 2.2.2 导出服务到本地

#### 2.2.3 导出服务到远程
服务导出与服务注册

RegistryProtocol.export()
1. 调用 doLocalExport 导出服务
2. 向注册中心注册服务
3. 向注册中心进行订阅 override 数据
4. 创建并返回 DestroyableExporter

### 2.3 服务注册
##### 2.3.1 创建注册中心
##### 2.3.2 节点创建

### 2.5 订阅 override 数据