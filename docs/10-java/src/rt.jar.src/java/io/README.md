java.io

## package
```
DataOutput
Externalizable
InputStream
InputStreamReader
OutputStream
OutputStreamWriter
PipedInputStream
PipedOutputStream
PipedReader
PipedWriter
Flushable

FileDescriptor      文件描述符，句柄
FileFilter
FilePermission      文件权限
FileSystem          文件系统

ObjectOutput
FilenameFilter
DataInput
ObjectInput
Closeable
    
IOException         IO异常
IOError             IO错误

ObjectInputStream
ObjectOutputStream

Reader
Writer
```

## 分类
* 按数据流动方向：输入流、输出流
* 按处理单位：字节流、字符流
* 按照功能：节点流、处理流
  * 节点流 直接将管道连通文件与程序
  * 处理流 在管道之上套着，对文件数据的传输起处理作用的

## core

### Buffer  
Buffer缓冲区可以减少读写的次数，减少对硬件的损伤，相当于一个小桶，一次性装许多东西，一次性写到硬盘，这样的缓冲区可以使用字节数组完成。  

### flush和close
Close的关闭非常粗暴，close将直接将管道关闭，而flush将先把缓冲区的内容全部输出，然后再用close才是最好的选择。
