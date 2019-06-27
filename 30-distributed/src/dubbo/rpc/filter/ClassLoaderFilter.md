com.alibaba.dubbo.rpc.filter.ClassLoaderFilter

Thread.currentThread().setContextClassLoader(); 
只影响动态加载类，不会影响 new 。
ClassLoaderFilter 避免后续动态加载类时出现异常。


### [ClassLoader](https://www.jianshu.com/p/7ffa2320b2f0)
* BootstrapClassLoader 
* ExtClassLoader  
* AppClassLoader

一个ClassLoader创建时如果没有指定parent，那么它的parent默认就是AppClassLoader

### 类加载器的命名空间
1、同一个命名空间内的类是相互可见的，即可以互相访问。		            
2、父加载器的命名空间对子加载器可见。		            
3、子加载器的命名空间对父加载器不可见。		            
4、如果两个加载器之间没有直接或间接的父子关系，那么它们各自加载的类相互不可见。

### 双亲委托
双亲委托解释 (委托是从下向上，然后具体查找过程却是自上至下)     