
## java 对象序列化
Serializable

ObjectOutputStream.writeObject()
ObjectInputStream.readObject()

Externalizable
序列化的细节需要由程序员去完成。如果writeExternal()与readExternal()方法未作任何处理，那么该序列化行为将不会保存/读取任何一个字段


### 序列化算法一般会按步骤做如下事情：
1.	将对象实例相关的类元数据输出
2.	递归地输出类的超类描述直到不再有超类
3.	类元数据完了以后，开始从最顶层的超类开始 输出对象实例的实际数据值
4.	从上至下递归输出实例的数据


序列化 ID 的问题
静态变量序列化	序列化并不保存静态变量

父类的序列化与 Transient 关键字	
	要想将父类对象也序列化，就需要让父类也实现Serializable 接口。如果父类不实现的话的，就需要有默认的无参的构造函数
	不需要被序列化的字段抽取出来放到父类中，子类实现 Serializable 接口
对敏感字段加密
序列化存储规则


### 序列化几种方式
#### Json

优点：明文结构一目了然，可以跨语言，属性的增加减少对解析端影响较小。缺点：字节数过多，依赖于不同的第三方类库

#### Object Serializalbe 

优点：java原生支持，不需要提供第三方的类库，使用比较简单。缺点：无法跨语言，字节数占用比较大，某些情况下对于对象属性的变化比较敏感。 

#### google protobuf 

优点：字节数很小，适合网络传输节省io，跨语言 。缺点：需要依赖于工具生成代码。

Protocol Buffer是谷歌出品的一种数据交换格式，独立于语言和平台，类似于json。Google提供了多种语言的实现：java、c++、go和python。
对象序列化城Protocol Buffer之后可读性差，但是相比xml，json，它占用小，速度快。适合做数据存储或 RPC 数据交换格式。 

