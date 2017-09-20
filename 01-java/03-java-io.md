
## io流相关概念
输入输出是相对于内存设备而言的

字节流：一次读入或读出是8位二进制
字符流：一次读入或读出是16位二进制

字节流和字符流的原理是相同的，只不过处理的单位不同而已。后缀是Stream是字节流，而后缀是Reader，Writer是字符流。

## 字节流      
用字节流处理字符数据可能会有编码问题，因为字节流是以字节为单位，没有编码，而字符流是以字符为单位传送数据，字符流即以字节流+编码


## java 对象序列化
Serializable

ObjectOutputStream.writeObject()
ObjectInputStream.readObject()

Externalizable
序列化的细节需要由程序员去完成。如果writeExternal()与readExternal()方法未作任何处理，那么该序列化行为将不会保存/读取任何一个字段