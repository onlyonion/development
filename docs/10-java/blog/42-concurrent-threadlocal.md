[Java多线程编程-（11）-面试常客ThreadLocal出现OOM内存溢出的场景和原理分析](https://blog.csdn.net/bntx2jsqfehy7/article/details/78315161)


ThreadLocal内存泄漏的根源是：由于ThreadLocalMap的生命周期跟Thread一样长，如果没有手动删除对应key就会导致内存泄漏，而不是因为弱引用。

每次使用完ThreadLocal，都调用它的remove()方法，清除数据。

### 场景
ThreadLocal是用在多线程的场景的。
保存线程上下文信息，在任意需要的地方可以获取。
线程安全的，避免某些情况需要考虑线程安全必须**同步带来的性能损失**。
每个线程往ThreadLocal中读写数据是线程隔离，互相之间不会影响的，所以ThreadLocal无法解决共享对象的更新问题。

常用的比如每个请求怎么把一串后续关联起来，就可以用ThreadLocal进行set，在后续的任意需要记录日志的方法里面进行get获取到请求id，从而把整个请求串起来。
还有比如Spring的事务管理，用ThreadLocal存储Connection，从而各个DAO可以获取同一Connection，可以进行事务回滚，提交等操作。

### 弱引用
弱引用也是用来描述非必需对象的，当JVM进行垃圾回收时，无论内存是否充足，该对象仅仅被弱引用关联，那么就会被回收。
当仅仅只有ThreadLocalMap中的Entry的key指向ThreadLocal的时候，ThreadLocal会进行回收的

ThreadLocal被垃圾回收后，在ThreadLocalMap里对应的Entry的键值会变成null，但是Entry是强引用，那么Entry里面存储的Object，并没有办法进行回收，所以ThreadLocalMap 做了一些额外的回收工作。

由于线程的生命周期很长，如果我们往ThreadLocal里面set了很大很大的Object对象，虽然set、get等等方法在特定的条件会调用进行额外的清理，但是ThreadLocal被垃圾回收后，在ThreadLocalMap里对应的Entry的键值会变成null，但是后续在也没有操作set、get等方法了。

所以最佳实践，应该在我们不使用的时候，主动调用remove方法进行清理。

这里把ThreadLocal定义为static还有一个好处就是，由于ThreadLocal有强引用在，那么在ThreadLocalMap里对应的Entry的键会永远存在，那么执行remove的时候就可以正确进行定位到并且删除！！！

### Netty
对于ThreadLocal，我在看Netty源码的时候，还了解过FastThreadLocal，xxxxx一些列内容，那就是一个升级了。