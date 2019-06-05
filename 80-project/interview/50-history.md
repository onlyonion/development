
### NoClassDefFoundError与ClassNotFoundException
NoClassDefFoundError 是程序在编译时候可以顺利找到所需要依赖的类的，但是在运行时依赖的类找不到或者可以找到多个，就会抛出这个Error。
NoClassDefFoundError异常，看命名后缀是一个Error。从类继承层次上看，NoClassDefFoundError是从Error继承的。
和ClassNotFoundException相比，明显的一个区别是，NoClassDefFoundError并不需要应用程序去关心catch的问题。

ClassNotFoundException在类路径错误，或者类名称发生更改，都会导致这个Exception，这两个异常的出现在生产环境中，通常是由于依赖jar包多版本存在，jar包升级中类或者方法不再提供引起的
ClassNotFoundException是一个运行时异常。从类继承层次上来看，ClassNotFoundException是从Exception继承的，所以ClassNotFoundException是一个检查异常。
一般在执行Class.forName()、ClassLoader.loadClass()或ClassLoader.findSystemClass()的时候抛出

### jvm graceful shutdown

### 线程切换

### netty 特点

### soa vs microservice

### 服务治理

### 分布式事务一致性

### 设计原则

### mysql 索引实现

### b-tree, b+tree, red/black tree

### project comprehend

### project improvement


