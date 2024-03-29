《揭秘Java虚拟机-JVM设计原理与实现》 封亚飞

## 第1章 Java虚拟机概述
## 第2章 Java执行引擎工作原理：方法调用
计算机核心3个功能：
1. 方法调用 方法作为原子指令的初步封装，Java语言的原子指令是字节码，Java方法是对字节码的封装。
2. 取指
3. 运算

### 2.1 方法调用
### 2.2 JVM的函数调用机制
### 2.3 函数指针
### 2.4 CallStub函数指针定义
### 2.5 _call_stub_entry例程

## 第3章 Java数据结构与面向对象
### 3.1 从Java算法到数据结构
### 3.2 数据类型简史
### 3.3 Java数据结构之偶然性
### 3.4 Java类型识别
### 3.5 大端与小端

## 第4章 Java字节码实战
### 4.1 字节码格式初探
### 4.2 魔数与版本
### 4.3 常量池
### 4.4 访问标识与继承信息
### 4.5 字段信息
### 4.6 方法信息

## 第5章 常量池解析
### 5.1 常量池内存分配
### 5.2 oop-klass模型
### 5.3 常量池klass模型（1）
### 5.4 常量池klass模型（2）
### 5.5 常量池解析

## 第6章 类变量解析
### 6.1 类变量解析
### 6.2 偏移量
### 6.3 从源码看字段继承

## 第7章 Java栈帧
### 7.1 entry_point例程生成
### 7.2 局部变量表创建
### 7.3 堆栈与栈帧
### 7.4 JVM的栈帧
### 7.5 栈帧深度与slot复用
### 7.6 最大操作数栈与操作数栈复用

## 第8章 类方法解析
### 8.1 方法签名解析与校验
### 8.2 方法属性解析
### 8.3 创建methodOop
### 8.4 Java方法属性复制
### 8.5 ＜clinit＞与＜init＞
### 8.6 查看运行时字节码指令
### 8.7 vtable

## 第9章 执行引擎
### 9.1 执行引擎概述
### 9.2 取指
### 9.3 译码
### 9.4 栈顶缓存
### 9.5 栈式指令集
### 9.6 操作数栈在哪里
### 9.7 栈帧重叠
### 9.8 entry_point例程机器指令
### 9.9 执行引擎实战
### 9.10 字节码指令实现

## 第10章 类的生命周期
### 10.1 类的生命周期概述
### 10.2 类加载
### 10.3 类的初始化
### 10.4 类加载器
### 10.5 类实例分配

