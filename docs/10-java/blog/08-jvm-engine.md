# jvm执行引擎
Java虚拟机规范指定了虚拟机字节码执行引擎的概念模型，提供了统一Facade。不同的虚拟机实现里，执行的时候，可能会有解释执行、编译执行两种，或者两者兼备。

## 运行时栈帧结构

栈帧是用于支持虚拟机进行方法调用和方法执行的数据结构， 存储了方法的
* 局部变量表
* 操作数栈
* 动态连接 
* 每个栈帧都包含一个指向运行时常量池中，该帧所属方法的引用，以支持动态连接
* 方法返回地址

## 方法调用

### 解析
“编译期可知，运行期不可变”，这类方法的调用叫“解析”

### 分派
* 静态分派 依赖静态类型(编译时确定)来定位方法的执行版本的分派动作，叫做静态分派	**方法重载**
* 动态分派 在运行期根据实际类型确定方法执行版本的分派，叫做动态分派	**方法重写**(invokevirutal把常量池中的方法的符号引用解析到不同的直接引用上)

方法的接收者和方法的参数统称为方法的宗量。根据分派基于多少种宗量，可以将分派划分为单分派和多分派。
单分派是根据一个宗量对目标方法进行选择，多分派则是根据多于一个宗量对目标方法进行选择。

静态多分派
动态单分派

Java虚拟机调用字节码指令有哪些？
invokestatic：调用静态方法
invokespecial：调用实例构造器方法、私有方法和父类方法
invokevirtual：调用所有的虚方法
invokeinterface：调用接口方法

### 动态语言支持
JDK 1.7 增加的指令 invokedynamic
JDK 1.8 javascript引擎

## 字节码解释执行引擎

### 虚拟机是如何执行方法里面的字节码指令的

* 解释执行（通过解释器执行）
* 编译执行（通过即时编译器JIT产生本地代码）

### 基于栈的指令集和基于寄存器的指令集

基于栈的指令集
Java编译器输出的指令流，里面的指令大部分都是零地址指令，它们依赖操作数栈进行工作。
基于寄存器的指令集
最典型的是x86的地址指令集，依赖寄存器工作

## 编译优化技术

### Javac编译过程分为哪些步骤

### 即时编译器JIT

为了提高热点代码的执行效率，在运行时，虚拟机将会把这些代码编译成与本地平台相关的机器码，并进行各种层次的优化，完成这个任务的编译器成为即时编译器（Just In Time 
Compiler，JIT编译器）

### 热点代码

* 多次调用的方法
* 被多次执行的循环体

判断是否为热点代码-热点探测

### 优化技术类型

代表性优化技术
公共子表达式消除
数组边界检查消除
方法内联
逃逸分析

## Java与C/C++编译器




[摘自-JVM之执行引擎](https://blog.csdn.net/qq_33938256/article/details/52584658) 
