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
Node.js 是一个让 JavaScript 运行在服务端的开发平台，它让 JavaScript 成为脚本语言世界的一等公民，在服务端堪与 PHP、 Python、Perl、 Ruby 平起平坐。
它实现了诸如文件系统、模块、包、操作系统 API、网络通信等 Core JavaScript 没有或者不完善的功能。
### 1.2 Node.js能做什么
### 1.3 异步IO与事件模型
Node.js 使用的是单线程模型，对于所有 I/O 都采用异步式的请求方式，避免了频繁的上下文切换。 
Node.js 在执行的过程中会维护一个事件队列，程序在执行时进入事件循环等待下一个事件到来，每个异步式 I/O 请求完成后会被推送到事件队列，等待程序进程进行处理。

### 1.4 Node.js的性能
Node.js 用异步式 I/O 和事件驱动代替多线程，带来了可观的性能提升。 
Node.js 除了使用 V8 作为JavaScript引擎以外，还使用了高效的 libev 和 libeio 库支持事件驱动和异步式 I/O。

Node.js 的开发者在 libev 和 libeio 的基础上还抽象出了层 libuv。
对于 POSIX操作系统，libuv 通过封装 libev 和 libeio 来利用 epoll 或 kqueue。
而在 Windows 下， libuv 使用了 Windows的 IOCP（Input/Output Completion Port，输入输出完成端口）机制，以在不同平台下实现同样的高性能。

### 1.5 JavaScript简史

### 1.6 CommonJS
CommonJS 规范包括了模块（modules）、包（packages）、系统（system）、二进制（binary）、控制台（console）、
编码（encodings）、文件系统（filesystems）、套接字（sockets）、单元测试（unit testing）等部分

## 第2章 安装和配置Node.js
### 2.1　安装前的准备
### 2.2　快速安装
### 2.3　编译源代码
### 2.4　安装Node 包管理器
Node 包管理器（npm）是一个由 Node.js 官方提供的第三方包管理工具，就像 PHP 的Pear、 Python 的 PyPI 一样。 
### 2.5　安装多版本管理器

## 第3章 Node.js快速入门
### 3.1 开始Node.js编程

### 3.2 异步式IO与事件式编程
#### 3.2.1 阻塞与线程
什么是阻塞（block）呢？线程在执行中如果遇到**磁盘读写或网络通信**（统称为 I/O 操作），通常要耗费较长的时间，
这时操作系统会剥夺这个线程的 CPU 控制权，使其暂停执行，同时将资源让给其他的工作线程，这种线程调度方式称为 
阻塞。当 I/O 操作完毕时，操作系统将这个线程的阻塞状态解除，恢复其对CPU的控制权，令其继续执行。这种 I/O 模
式就是通常的同步式 I/O（Synchronous I/O）或阻塞式 I/O （Blocking I/O）。

异步式 I/O （Asynchronous I/O）或非阻塞式 I/O （Non-blocking I/O）则针对所有 I/O 操作不采用阻塞的策略。当
线程遇到 I/O 操作时，不会以阻塞的方式等待 I/O 操作的完成或数据的返回，而只是将 I/O 请求发送给操作系统，继
续执行下一条语句。当操作系统完成 I/O 操作时，以事件的形式通知执行 I/O 操作的线程，线程会在特定时候处理这个
事件。为了处理异步 I/O，线程必须有事件循环，不断地检查有没有未处理的事件，依次予以处理

#### 3.2.2 回调函数
在Node.js中，异步式IO是通过回调函数来实现的。
#### 3.2.3 事件
Node.js 的事件循环机制
Node.js 程序由事件循环开始，到事件循环结束，所有的逻辑都是事件的回调函数，所以 Node.js 始终在事件循环中，程
序入口就是事件循环第一个事件的回调函数。

Node.js 的事件循环对开发者不可见，由 libev 库实现。libev支持多种类型的事件，如 ev_io、 ev_timer、 ev_signal、 ev_idle 等，在 Node.js 中均被 EventEmitter 封装。 

### 3.3 模块和包
Node.js 提供了 require 函数来调用其他模块，而且模块都是基于文件的，机制十分简单。
#### 3.3.1 什么是模块
模块是 Node.js 应用程序的基本组成部分，文件和模块是一一对应的。
换言之，**一个Node.js 文件**就是一个模块，这个文件可能是 JavaScript 代码、 JSON 或者编译过的 C/C++ 扩展。
#### 3.3.2 创建及加载模块
Node.js 提供了 exports 和 require 两个对象，其中 exports 是模块公开的接口， require 用于从外部获取一个模块的接口，即所获取模块的 exports 对象。
#### 3.3.3 创建包
包是在模块基础上更深一步的抽象， Node.js 的包类似于 C/C++ 的函数库或者 Java/.Net的类库。
它将某个独立的功能封装起来，用于发布、更新、依赖管理和版本控制。
Node.js 的包是一个目录，其中包含一个 JSON 格式的包说明文件 package.json。

1. 作为文件夹的模块

包通常是一些模块的集合，在模块的基础上提供了更高层的抽象，相当于提供了一些固定接口的函数库。

2. package.json

Node.js 在调用某个包时，会首先检查包中 package.json 文件的 main 字段，将其作为包的接口模块，如果 package.json 或 main 字段不存在，会尝试寻找 index.js 或 index.node 作为包的接口。

PS: *.jar包 java ant build.xml; maven pom.xml

#### 3.3.4 Node.js 包管理器
1. 获取一个包
2. 本地模式和全局模式
3. 创建全局链接
4. 包的发布

### 3.4 调试

## 第4章 Node.js核心模块
### 4.1 全局对象
* 浏览器jsavscript 全局对象window
* 全局对象global processs, console是全局对象的属性

#### 4.1.1　全局对象与全局变量
#### 4.1.2　process
process.argv是命令行参数数组，第一个元素是 node， 第二个元素是脚本文件名，从第三个元素开始每个元素是一个运行参数。
process.nextTick(callback)的功能是为事件循环设置一项任务， Node.js 会在下次事件循环调响应时调用 callback。
#### 4.1.3　console

### 4.2 常用工具util
#### 4.2.1　util.inherits
util.inherits(constructor, superConstructor)是一个实现对象间原型继承的函数。 
#### 4.2.2　util.inspect 
util.inspect(object,[showHidden],[depth],[colors])是一个将任意对象转换为字符串的方法，通常用于调试和错误输出。
util.inspect 并不会简单地直接把对象转换为字符串，即使该对象定义了 toString 方法也不会调用。

util还提供了util.isArray()、util.isRegExp()、util.isDate()、 util.isError() 四个类型测试工具，以及 util.format()、 util.debug() 等工具。
### 4.3 事件驱动events
#### 4.3.1　事件发射器
events 模块只提供了一个对象： events.EventEmitter。 EventEmitter 的核心就是事件发射与事件监听器功能的封装。
EventEmitter 的每个事件由一个事件名和若干个参数组成，事件名是一个字符串，通常表达一定的语义。
对于每个事件， EventEmitter 支持若干个事件监听器。当事件发射时，注册到这个事件的事件监听器被依次调用，事件参数作为回调函数参数传递。

#### 4.3.2　error 事件
#### 4.3.3　继承 EventEmitter
多数时候我们不会直接使用 EventEmitter，而是在对象中继承它。包括 fs、 net、http 在内的，只要是支持事件响应的核心模块都是 EventEmitter 的子类。

为什么要这样做呢？原因有两点。首先，具有某个实体功能的对象实现事件符合语义，事件的监听和发射应该是一个对象的方法。
其次 JavaScript 的对象机制是基于原型的，支持部分多重继承，继承 EventEmitter 不会打乱对象原有的继承关系。

### 4.4 文件系统fs
#### 4.4.1　fs.readFile
fs.readFile(filename,[encoding],[callback(err,data)])
#### 4.4.2　fs.readFileSync
fs.readFileSync(filename, [encoding])是 fs.readFile 同步的版本。
#### 4.4.3　fs.open
fs.open(path, flags, [mode], [callback(err, fd)])是 POSIX open 函数的封装，与 C 语言标准库中的 fopen 函数类似。
#### 4.4.4　fs.read
fs.read(fd, buffer, offset, length, position, [callback(err, bytesRead, buffer)])是 POSIX read 函数的封装，相比 fs.readFile 提供了更底层的接口。

### 4.5 Http服务器与客户端
#### 4.5.1　HTTP 服务器
http.Server 是 http 模块中的 HTTP 服务器对象，用 Node.js 做的所有基于 HTTP 协议的系统，如网站、社交应用甚至代理服务器，都是基于 http.Server 实现的。
它提供了一套封装级别很低的 API，仅仅是流控制和简单的消息解析，所有的高层功能都要通过它的接口来实现。
1. http.Server 的事件 request请求事件 connection连接事件 close连接关闭事件
2. http.ServerRequest 
   1. 请求对象：请求头、请求体；
   2. data事件 请求体数据到来时、end事件 请求体数据传输完成时，close事件 请求结束时
3. 获取 GET 请求内容
4. 获取 POST 请求内容
5. http.ServerResponse

#### 4.5.2　HTTP 客户端
http 模块提供了两个函数 http.request 和 http.get，功能是作为客户端向 HTTP 服务器发起请求。
1. http.ClientRequest
2. http.ClientResponse

## 第5章 使用Node.js进行Web开发
### 5.1 准备工作
### 5.2 快速开始
### 5.3 路由控制
### 5.4 模板引擎
### 5.5 建立微博网站
#### 5.5.1 功能分析
#### 5.5.2 路由规则
#### 5.5.3 界面设计
#### 5.5.4 使用Bootstrap
### 5.6 用户注册和登录
#### 5.6.1 访问数据库
#### 5.6.2 会话支持
#### 5.6.3 注册和登入
#### 5.6.4 页面权限控制
### 5.7 发表微博

## 第6章 Node.js进阶话题
### 6.1 模块加载机制

PS：Java的类加载机制。加载、链接（验证、准备、解析）、初始化。双亲委派模型。

#### 6.1.1 模块类型 
* 核心模块 fs、http、net、vm，二进制代码
* 文件模块 可以是javascr代码、json或编译好的c/c++; .js .json .node

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

BootstrapClassLoader, ExtClassLoader, AppClassLoader

### 6.2 控制流
基于异步 I/O 的事件式编程容易将程序的逻辑拆得七零八落，给控制流的疏理制造障碍。
#### 6.2.1 循环的陷进
#### 6.2.2 解决控制流难题
深层的回调函数嵌套

async 是一个控制流解耦模块，它提供了async.series、 async.parallel、 async.waterfall 等函数

### 6.3 Node.js应用部署
#### 6.3.1 日志功能
#### 6.3.2 使用cluster模块
#### 6.3.3 启动脚本
#### 6.3.4 共享80端口
### 6.4 Node.js不是银弹

#### Node.js不适合做什么
1. 计算密集型的程序
2. 单用户多任务型应用
3. 逻辑十分复杂的事务
4. Unicode与国际化

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