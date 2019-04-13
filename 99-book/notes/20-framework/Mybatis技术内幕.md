《MyBatis技术内幕》徐郡明 中国工信出版集团 电子工业出版社

## 第1章 Mybatis快速入门

### 1.4 Mybatis整体架构
#### 1.4.1 基础支持层
* 反射模块
* 类型转换模块
* 日志模块
* 资源加载模块
* 数据源模块
* 事务管理
* 缓存模块
* Binding模块
* 解析器模块

#### 1.4.2 核心处理层
* 配置解析
* 参数映射
* sql解析与scripting模块
* sql执行
* 结果集映射
* 插件

#### 1.4.3 接口层
* SqlSession

## 第2章 基础支持层
### 2.1 解析器模块
* DOM document object model 基于树形结构的XML解析方式，将整个XML文档读入内存中并构建一个DOM树
* SAX simple api for xml 基于事件模型的xml解析方式，不需要将整个xml加载，当sax解析到某个类型节点事，触发注册到该类型节点上的回调函数
* StAX streaming api for xml JAXP是JDK提供，支持DOM和SAX解析方式。

#### 2.1.1 XPath简介
是使用DOM解析，并结合使用Xpath解析XML。XPath之于XML好比SQL语言之于数据库。  
XPath使用路径表达式来选取XML文档中指定的节点或节点集合。  

### 2.2 反射工具箱
### 2.3 类型转换
### 2.4 日志模块
### 2.5 资源加载
### 2.6 DataSource
### 2.7 Transaction
### 2.8 binding模块
### 2.9 缓存模块
#### 2.9.1 装饰器模式
装饰器模块可以动态的为对象添加功能，基于组合的方式实现。
#### 2.9.2 Cache接口及其实现
* PerpetualCache HashMap实现
* BlockingCache 加锁，保证只有一个线程到数据库中查询指定key对应的数据
* FifoCache & LruCache 
  * LinkedList 
  * LinkedHashMap get()方法访问到元素，会将这个元素移动到队列尾部
* SoftCache & WeakCache
  * ReferenceQueue 引用队列收集一个对象的可达性变化
  * SoftReference 创建SoftReference对象时，可以为其关联到一个引用队列，当ReferenceQueue所引用的对象被GC回收时，
  jvm就会将SoftReference对象添加到与之关联的引用队列中。
  * WeakReference

#### 2.9.4 Cachekey


## 第3章 核心处理层
### 3.1 Mybatis初始化
### 3.2 SqlNode&SqlSource
### 3.3 ResultSetHandler
### 3.4 KeyGenerator
### 3.5 StatementHandler
### 3.6 Executor
#### 3.6.1 模板方法模式
将模板方法一起固定不变的基本方法同一封到父类中，而将变化的部分封装到子类中实现。
#### 3.6.2 BaseExecutor
1. 一级缓存 会话级别
   伴随一次session的打开与关闭。
   PrepetualCache

#### 3.6.3 SimpleExecutor
#### 3.6.4 ReuseExecutor
#### 3.6.5 BatchExecutor
#### 3.6.5 CachingExecutor
1. 二级缓存 应用级别
   
### 3.7 接口层

## 第4章 高级主题
### 4.1 插件模块
### 4.2 Mybatis与Spring集成
### 4.3 拾遗