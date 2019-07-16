## Java虚拟机指什么
* 抽象规范
* 一个具体的实现 [hotspot.src](/10-java/src/openjdk/hotspot.src/README.md)
* 一个运行中的虚拟机实例

## Java虚拟机架构

JVM = 类加载器 classloader + 执行引擎 executionengine + 运行时数据区域 runtime data area

* ClassLoader 把硬盘上的class文件加载到JVM中的运行时数据区域，但是它不负责这个类文件能否执行
* 执行引擎 执行字节码，或者执行本地方法
* Runtime DataArea JVM在运行期间，在运行时数据区对JVM内存空间的**划分、分配**，内存回收GC
	- PC计数器
	- JVM栈
	- 本地方法栈
	- 方法区
	- 运行时常量池
	- Java堆
* 本地接口
* 本地库	

## simple description

```
.java             	--> .class -->  
ClassLoader 		--> RuntimeDataArea(JavaStack, NativeMethodStack, ProgramCounterRegister, Heap, MethodArea) -->
ExecutionEngine   	--> 
NativeInterfaces 	--> NativeLibraries	
```

## jvm-architecture
![java-jvm](./img/jvm-architecture.png)


## GC
算法
1. 引用计数
2. 标记清除
3. 标记整理
4. 复制

收集器
1. serial
2. parnew
3. parallel
4. serial old
5. cms
6. parallel
7. g1
8. zgc 着色指针 读屏障 分区 标记压缩 并发

## 类加载


## 编译优化


## 运行期优化


## 并发



