[Java多线程编程-（11）-面试常客ThreadLocal出现OOM内存溢出的场景和原理分析](https://blog.csdn.net/bntx2jsqfehy7/article/details/78315161)


ThreadLocal内存泄漏的根源是：由于ThreadLocalMap的生命周期跟Thread一样长，如果没有手动删除对应key就会导致内存泄漏，而不是因为弱引用。

每次使用完ThreadLocal，都调用它的remove()方法，清除数据。