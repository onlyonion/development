CGLIB（Code Generation Library），是一个强大的，高性能，高质量的Code生成类库，它可以在运行期扩展Java类与实现Java接口

cglib 创建某个类A的动态代理类的模式是：
1. 查找A上的所有非final 的public类型的方法定义；
2. 将这些方法的定义转换成字节码；
3. 将组成的字节码转换成相应的代理的class对象；
4. 实现 MethodInterceptor接口，用来处理 对代理类上所有方法的请求（这个接口和JDK动态代理InvocationHandler的功能和角色是一样的）

不足：
* final Class不支持, 因为CGLIB是生成子类来实现AOP,所以final Class自然无法支持了.
* 需要强制无参数构造函数 