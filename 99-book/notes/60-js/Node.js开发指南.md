《Node.js开发指南》 BYVoid 编著 人民邮电出版社

清华大学计算机系2010级本科生。中学开始涉足开源开发。

1. Node.js简介 异步式I/O与事件模型
2. 安装和配置Node.js
3. Node.js入门 异步式I/O与事件编程、模块和包
4. Node.js核心模块 global util events fs http
5. Node.js进行Web开发  Web开发 路由控制、模板引擎
6. Node.js进阶话题 模块加载机制、控制流

## 第1章 Node.js简介
### 1.1 Node.js是什么
### 1.2 Node.js能做什么
### 1.3 异步IO与事件模型
Node.js 使用的是单线程模型，对于所有 I/O 都采用异步式的请求方式，避免了频繁的上下文切换。 Node.js 
在执行的过程中会维护一个事件队列，程序在执行时进入事件循环等待下一个事件到来，每个异步式 I/O 请求
完成后会被推送到事件队列，等待程序进程进行处理。

### 1.4 Node.js的性能
Node.js 用异步式 I/O 和事件驱动代替多线程，带来了可观的性能提升。 Node.js 除了使用 V8 作为JavaScript
引擎以外，还使用了高效的 libev 和 libeio 库支持事件驱动和异步式 I/O。

Node.js 的开发者在 libev 和 libeio 的基础上还抽象出了层 libuv。对于 POSIX操作系统，libuv 通过封装 libev
和 libeio 来利用 epoll 或 kqueue。而在 Windows 下， libuv 使用了 Windows的 IOCP（Input/Output Completion
Port，输入输出完成端口）机制，以在不同平台下实现同样的高性能。

### 1.5 JavaScript简史
### 1.6 CommonJS
CommonJS 规范包括了模块（modules）、包（packages）、系统（system）、二进制（binary）、控制台（console）、
编码（encodings）、文件系统（filesystems）、套接字（sockets）、单元测试（unit testing）等部分

## 第2章 安装和配置Node.js

## 第3章 Node.js快速入门
### 3.1 开始Node.js编程

### 3.2 异步式IO与事件式编程
#### 3.2.1 阻塞与线程
什么是阻塞（block）呢？线程在执行中如果遇到磁盘读写或网络通信（统称为 I/O 操作），通常要耗费较长的时间，
这时操作系统会剥夺这个线程的 CPU 控制权，使其暂停执行，同时将资源让给其他的工作线程，这种线程调度方式称为 
阻塞。当 I/O 操作完毕时，操作系统将这个线程的阻塞状态解除，恢复其对CPU的控制权，令其继续执行。这种 I/O 模
式就是通常的同步式 I/O（Synchronous I/O）或阻塞式 I/O （Blocking I/O）。

异步式 I/O （Asynchronous I/O）或非阻塞式 I/O （Non-blocking I/O）则针对所有 I/O 操作不采用阻塞的策略。当
线程遇到 I/O 操作时，不会以阻塞的方式等待 I/O 操作的完成或数据的返回，而只是将 I/O 请求发送给操作系统，继
续执行下一条语句。当操作系统完成 I/O 操作时，以事件的形式通知执行 I/O 操作的线程，线程会在特定时候处理这个
事件。为了处理异步 I/O，线程必须有事件循环，不断地检查有没有未处理的事件，依次予以处理

#### 3.2.2 回调
#### 3.2.3 事件
Node.js 的事件循环机制
Node.js 程序由事件循环开始，到事件循环结束，所有的逻辑都是事件的回调函数，所以 Node.js 始终在事件循环中，程
序入口就是事件循环第一个事件的回调函数。

### 3.3 模块和包
#### 3.3.1 什么是模块
模块是 Node.js 应用程序的基本组成部分，文件和模块是一一对应的。换言之，**一个Node.js 文件**就是一个模块，
这个文件可能是 JavaScript 代码、 JSON 或者编译过的 C/C++ 扩展。

#### 3.3.3 创建包
包是在模块基础上更深一步的抽象， Node.js 的包类似于 C/C++ 的函数库或者 Java/.Net
的类库。它将某个独立的功能封装起来，用于发布、更新、依赖管理和版本控制。
Node.js 的包是一个目录，其中包含一个 JSON 格式的包说明文件 package.json。

### 3.4 调试
java ant, build.xml; maven, pom.xml

## 第4章 Node.js核心模块
### 4.1 全局对象
global processs, console
### 4.2 常用工具util
util.inherits(constructor, superConstructor), util.inspect(object,[showHidden],[depth],[colors])是一个将任意
对象转换为字符串的方法，通常用于调试和错误输出

### 4.3 事件驱动events
### 4.4 文件系统fs
fs fs.readFile, fs.readFileSync, fs.open, fs.read
### 4.5 Http服务器与客户端

## 第5章 使用Node.js进行Web开发
### 5.1 准备工作
### 5.2 快速开始
### 5.3 路由控制
### 5.4 模板引擎
### 5.5 建立微博网站
### 5.6 用户注册和登录
### 5.7 发表微博

## 第6章 Node.js进阶话题
### 6.1 模块加载机制

PS：Java的类加载机制。加载、链接（验证、准备、解析）、初始化。双亲委派模型。

#### 6.1.1 模块类型 
* 核心模块
* 文件模块

#### 6.1.2 按路径加载模块
文件模块的加载有两种方式，一种是按路径加载，一种是查找 node_modules 文件夹
#### 6.1.3 通过查找 node_modules 目录加载模块
如果require参数不以“/ ”、“./ ”或“../ ”开头，而该模块又不是核心模块，那么就要通过查找 node_modules 加载
模块了。我们使用npm获取的包通常就是以这种方式加载的

#### 6.1.4 加载缓存
#### 6.1.5 加载顺序
require(some_module)的加载顺序
1. 如果some_module是一个核心模块，直接加载，结束
2. 如果some_module以“/ ”、“./ ”或“../ ”开头，按路径加载 some_module，结束
3. 假设当前目录为 current_dir，按路径加载 current_dir/node_modules/some_module
   * 如果加载成功，结束
   * 如果加载失败，令current_dir为其父目录
   * 重复这一过程，直到遇到根目录，抛出异常，结束

### 6.2 控制流
基于异步 I/O 的事件式编程容易将程序的逻辑拆得七零八落，给控制流的疏理制造障碍。
#### 6.2.1 循环的陷进
#### 6.2.2 解决控制流难题
深层的回调函数嵌套

### 6.3 Node.js应用部署
### 6.4 Node.js不是银弹

#### Node.js不适合做什么
1. 计算密集型的程序
2. 单用户多任务型应用
3. 逻辑十分复杂的事务
4. Unicode 与国际化

## 附录A JavaScript的高级特性

### A.1 作用域
全局作用域
函数作用域
作用域嵌套

JavaScript作用域是通过函数来定义的，在一个函数中定义的变量只对这个函数内部可见，我们称为函数作用域。
在函数中引用一个变量时， JavaScript 会先搜索当前函数作用域，或者称为“局部作用域”，如果没有找到则搜索其上层作用
域，一直到全局作用域。

### A.2 闭包

闭包的严格定义是“由函数（环境）及其封闭的自由变量组成的集合体。”  

由于在javascript语言中，只有函数内部的子函数才能读取局部变量，因此可以把闭包简单理解成‘定义在一个函数内部的函数’。
所以，在本质上，闭包就是将函数内部和函数外部连接的一座桥梁。

闭包是由函数以及创建该函数的词法环境组合而成。这个环境包含了这个闭包创建时所能访问的所有局部变量。
* 作用域
* 嵌套函数

闭包的特性
当一个函数返回它内部定义的一个函数时，就产生了一个闭包，闭包不但包括被返回的函数，还包括这个函数的定义环境 

用途
* 嵌套回调函数
* 实现私有成员 私有属性前加下划线不正式。通过闭包实现属性封装、细节隐藏--封闭的自由变量
* 保存在内存中

### A.3 对象
#### 构造函数
```javascript
function User(name, uri) {
    this.name = name;
    this.uri = uri;
    this.display = function() {
    console.log(this.name);
    }
}
var someuser = new User('byvoid', 'http://www.byvoid.com');
// 用户创建的对象 someuser
// 构造函数对象 ƒ User(name, uri)
// 原型对象 构造函数 ƒ User(name, uri) 的 prototype 属性指向的对象
```

#### 上下文对象
在 JavaScript 中，本质上，函数类型的变量是指向这个函数实体的一个引用，在引用之间赋值不会对对象产生复制行为

改变上下文对象 call(), apply(), bind()
call 和 apply 的功能是一致的，两者细微的差别在于 call 以参数表来接受被调用函数的参数，而 apply 以数组来接受被调用函数的参数。 

```javascript
func.call(thisArg[, arg1[, arg2[, ...]]])
func.apply(thisArg[, argsArray])

// java proxy
// Object method.invoke(Object obj, Object... args);
// js arguments caller callee
// call apply bind

```

#### 原型链
JavaScript 中有两个特殊的对象： Object 与 Function，它们都是构造函数，用于生成对象。

* Object.prototype 是所有对象的祖先，
* Function.prototype 是所有函数的原型，包括构造函数。

我把 JavaScript 中的对象分为三类，一类是用户创建的对象，一类是构造函数对象，一类是原型对象。
* 用户创建的对象，即一般意义上用 new 语句显式构造的对象。
* 构造函数对象，指的是普通的构造函数，即通过 new 调用生成普通对象的函数。
* 原型对象，特指构造函数 prototype 属性指向的对象。

原型链
* 这三类对象中每一类都有一个__proto__属性，它指向**该对象的原型**，从任何对象沿着它开始遍历都可以追溯到 Object.prototype。
* 构造函数对象有 prototype 属性，指向一个**原型对象**，通过该构造函数创建对象时，被创建对象的__proto__属性将会指向
  构造函数的 prototype 属性。
* 原型对象有 constructor 属性，指向它对应的构造函数

在 JavaScript 中，继承是依靠一套叫做原型链（prototype chain）的机制实现的。
属性继承的本质就是一个对象可以访问到它的原型链上任何一个原型对象的属性。

原型链查找，一层一层的链接关系
* 普通对象的__proto__指向它的构造函数的prototype对象去查找属性或者方法，如果有返回；
* 如果没有，那就会继续沿着构造函数的prototype.__proto__向上找
* 一直到而Object.prototype。

### A.3.5 对象的复制

```javascript
// 浅拷贝
Object.prototype.clone = function() {
    var newObj = {};
    for (var i in this) {
        newObj[i] = this[i];
    }
    return newObj;
}

// 深拷贝 递归方式；不完美，循环引用致使死循环
// 必须设计一套图论算法，分析对象之间的依赖关系，建立一个拓扑结构图，然后分别依次复制每个顶点，并重新构建它们之间的依赖关系
Object.prototype.clone = function() {
    var newObj = {};
    for (var i in this) {
        if (typeof(this[i]) == 'object' || typeof(this[i]) == 'function') {
            newObj[i] = this[i].clone();
        } else {
            newObj[i] = this[i];
        }
    }
    return newObj;
};
Array.prototype.clone = function() {
    var newArray = [];
    for (var i = 0; i < this.length; i++) {
        if (typeof(this[i]) == 'object' || typeof(this[i]) == 'function') {
            newArray[i] = this[i].clone();
        } else {
            newArray[i] = this[i];
        }
    }
    return newArray;
};
Function.prototype.clone = function() {
    var that = this;
    var newFunc = function() {
        return that.apply(this, arguments);
    };
    for (var i in this) {
        newFunc[i] = this[i];
    }
    return newFunc;
};
```