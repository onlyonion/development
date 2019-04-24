## spring cache

Cache

CacheManger

annotation
SpEL
cacheName, key, condition

@Cacheable
@CachePut
@CacheEvict
beforeInvocation=false, 方法出现异常，缓存不会清空

```java
public @interface CachePut {  
    String[] value();              //缓存的名字，可以把数据写到多个缓存  
    String key() default "";       //缓存key，如果不指定将使用默认的KeyGenerator生成，后边介绍  
    String condition() default ""; //满足缓存条件的数据才会放入缓存，condition在调用方法之前和之后都会判断  
    String unless() default "";    //用于否决缓存更新的，不像condition，该表达只在方法执行之后判断，此时可以拿到返回值result进行判断了  
} 
```



#### 原理
Spring AOP 动态代理

和 spring 的事务管理类似，spring cache 的关键原理就是 spring AOP，通过 spring AOP，其实现了在方法调用前、调用后获取方法的**入参和返回值**，进而实现了缓存的逻辑。

内部调用（即 this 引用）而不是外部引用，则会导致 proxy 失效，切面失效

#### 运行流程
* 首先执行@CacheEvict（如果beforeInvocation=true且condition 通过），如果allEntries=true，则清空所有  
* 接着收集@Cacheable（如果condition 通过，且key对应的数据不在缓存），放入cachePutRequests（也就是说如果cachePutRequests为空，则数据在缓存中）  
* 如果cachePutRequests为空且没有@CachePut操作，那么将查找@Cacheable的缓存，否则result=缓存数据（也就是说只要当没有cache put请求时才会查找缓存）  
* 如果没有找到缓存，那么调用实际的API，把结果放入result  
* 如果有@CachePut操作(如果condition 通过)，那么放入cachePutRequests  
* 执行cachePutRequests，将数据写入缓存（unless为空或者unless解析结果为false）；  
* 执行@CacheEvict（如果beforeInvocation=false 且 condition 通过），如果allEntries=true，则清空所有 

#### 使用场景
缓存适用于读多写少的场合，查询时缓存命中率很低、写操作很频繁等场景不适宜用缓存。


## links
* [spring-cache](https://www.cnblogs.com/yueshutong/p/9381540.html)
* [基于Redis的Spring cache 缓存介绍](https://www.cnblogs.com/junzi2099/p/8301796.html)