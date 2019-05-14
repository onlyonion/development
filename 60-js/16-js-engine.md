
## links
* [JavaScript V8引擎](https://www.jianshu.com/p/81f6ded64ab2)
* [什么是 Google V8](https://www.jianshu.com/p/906eafb41ea6)
* [javascript-引擎工作原理](https://www.cnblogs.com/970119449blog/p/8080133.html)

## js 引擎
* V8 - 开源，由Google开发，用C ++编写
* Rhin- 由Mozilla基金会开源，完全用Java开发
* SpiderMonkey 第一个JavaScript引擎，Netscape Navigator，Firefox
* JavaScriptCore 苹果公司为Safari开发
* KJS 最初由Harri Porten为KDE项目的Konqueror网络浏览器开发
* Chakra** (JScript9) Microsoft Edge
* Chakra** (JavaScript) Microsoft IE9-IE11
* Nashorn 作为OpenJDK的一部分，由Oracle Java语言和工具组编写
* JerryScript 一个物联网的轻量级引擎

广义的虚拟机包括一切跟任何真实机器无关的虚拟架构。而当前虚拟机的实现主要分成三类：
* 系统虚拟机：虚拟了一个运行完整系统的操作平台。典型代表：VirtualBox。
* 程序虚拟机：为单个计算机程序的运行虚拟必要的环境。典型代表：Java 虚拟机。
* 操作系统层虚拟化：介于系统和单个程序之间，可以运行多个独立应用程序，但是又不用虚拟完整操作系统。典型代表：Docker。

## 什么是JavaScript解析引擎
编译器是将源代码编译为另外一种代码（比如机器码，或者字节码），而解释器是直接解析并将代码运行结果输出。 比方说，firebug的console就是一个JavaScript的解释器。

## JavaScript引擎中的基本概念
JavaScript引擎中的基本概念：
* Parser：解析器，负责词法分析和语法分析。
* Bytecode :满足某种指令集架构的指令序列
* Bytecompiler : 字节码编译器，用于根据语法树生成字节码。
* Assembler：封装了目标机器的汇编代码
* JIT：实时编译器，用于将中间字节码编译成机器代码
* Interpreter : JavaScript解释器，本身实现了虚拟机功能，同时通过调用Parser、 Bytecompiler、 JIT各个模块的接口，以实现JavaScript解释器的功


解析器需要编译吗？
解释器 = 编译器 + 虚拟机

解释器的“解释”过程：
1. 源代码 -> 编译器 -> 虚拟机的字节码
2. 虚拟机的字节码 -> 虚拟机
   * 逐条指令直接执行
   * 转换成native code -> native code -> 执行

JS脚本执行
* js解释器
  * 编译器
    * 词法分析、语法分析
    * 语法树
    * 字节码生成
    * 字节码指令
  * 虚拟机
    * 字节码执行

js引擎中其他关键机制
* 对象的表示
* 内存回收机制
* 原型链的实现

执行环境
* 全局变量
* 局部变量
* 调用关系

虚拟寄存器的解析执行原理
* 基于栈的与基于寄存器的指令集架构
  * 栈 iconst_1 iconst_2 iadd istore_0
  * 寄存器 mov eax,1 add eax,2

JIT即时编译器
* 执行代码的生成
* 生成后如何执行
* 机器相关
* 权限与对齐
* 指令缓存
* 常数池