《深入浅出Node.js》 朴灵

* 模块机制 模块规范、模块加载、包
* 异步IO
  * 异步编程、事件驱动
  * Buffer
  * 网络编程（TCP、UDP、HTTP、WebSocket）
* 内存控制、垃圾回收机制、多线程
* 工程化
  * 测试、产品化、NPM库、构建、部署、监控、Web应用

Ryal Dahl

## 第1章 Node简介
### 1.1 Node的诞生历程
2009年3月，Ryan Dahl在其博客上宣布准备基于V8创建一个轻量级的Web服务器并提供一套库。

PS：2009年7月，Netty3.0.x版本

### 1.2 Node的命名与起源
#### 1.2.2 为什么叫Node
> 每一个Node进程都构成这个网络应用中的一个节点，这是它名字所含意义的真谛。

### 1.3 Node给javascript带来的意义
V8给Chrome浏览器带来了一个强劲的心脏。Node给Javascript带来了一个新的局面。
* Chrome浏览器组件构成
  * Html, Javascript
  * WebKit, V8
  * 中间层
  * 网卡、硬盘、显卡
* Node组件构成
  * JavaScript
  * V8
  * 中间层libuv
  * 网卡、硬盘

## 第2章 模块机制
### 2.1 CommonJS规范

```js
// 引入 命名空间-路径
var math = require('math');
// define
exports.add = function() {
}

```

### 2.2 Node的模块实现
* 优先从缓存中加载
* 路径分析和文件定位
* 编译执行

### 2.3 核心模块
### 2.4 C/C++扩展模块
### 2.5 模块调用栈
### 2.6 包与NPM
### 2.7 前后端共用模块

## 第3章 异步IO
### 3.1 为什么要异步I/O
### 3.2 异步I/O实现现状
#### 3.2.1 异步I/O与非阻塞I/O
操作系统内核对于IO操作只有两种方式，阻塞和非阻塞。
- 阻塞IO造成CPU等待IO、浪费等待时间，CPU的处理能力不能充分利用。
- 非阻塞调用之后立即返回，不带数据，要获取数据，需要通过文件描述符再次获取（重复调用-轮询）。

非阻塞IO存在的问题，应用程序需要重复调用IO操作来确认是否完成。这种重复调用判断操作是否完成的技术叫做轮询。
- read 最原始、性能最低
- select 根据描述符的事件状态轮询；**1024长度的数组**存储状态，最多同时检测1024个文件描述符
- poll   **链表方式**避免数组长度的限制，避免不必要的检查。文件描述符多效率低
- epoll  事件通知、执行回调的方式，进入轮询的时候如果没有检测到IO时间，进行休眠，直到事件发生将它唤醒
- kqueue FreeBSD，类似epoll

#### 3.2.2 理想的非阻塞异步I/O
期望的异步，应用程序发起非阻塞调用，无须通过遍历或者事件唤醒等方式轮询，可以直接处理下一个任务，
只需在IO完成后通过信号或者回调将数据传递给应用程序。

#### 3.2.3 现实的异步I/O
- 线程池 + 阻塞IO
- 线程池 + 非阻塞IO + 轮询

windows IOCP
*nix libuv

需要强调的一点是，这里的IO不仅仅只限于磁盘文件的读写。*nix将计算机抽象了一番，磁盘文件、硬件、套接字等几乎所有**计算机资源**都抽象为文件，因此这里描述的阻塞和非阻塞的情况同样适合于**套接字**等。

时常说的Node是单线程的，**仅仅指JavaScript执行在单线程**。在Node中，内部完成I/O任务的另有**线程池**。

### 3.3 Node的异步I/O
#### 3.3.1 事件循环
#### 3.3.2 观察者
#### 3.3.4 执行回调
事件循环、观察者、请求对象、IO线程池这四者共同构成了Node异步IO模型的基本要素。

### 3.4 非I/O的异步API
setTimeout(); setInterval();将任务插入到定时器观察者内部的一个红黑树中。

process.nextTick();事件循环的特点，定时器的精度不够。

setInmmediate();

### 3.5 事件驱动与高性能服务器

#### Node构建高性能服务器
* 网络请求（内核）    监听端口 -> 接收网络请求 -> 发送给IO观察者形成事件
* 事件循环（libuv）   进入循环 -> 执行IO观察者中的事件回调函数 -> 业务回调?
* 执行回调（JS）      执行回调 -> 绑定请求事件 -> 执行回调函数

> 异步IO的实现，主旨IO操作与CPU操作分离

## 第4章 异步编程

### 4.1 函数式编程
高阶函数：把函数作为参数，或者把函数作为返回值
ECMAScript5 数组方法 forEach(), map(), reduce(), redureRight(), filter(), every(), some()
偏函数：指定部分参数来产生一个新的定制函数的形式
### 4.2 异步编程的优势与难点
```js
// 异常问题
// 异常作为回调函数第一个实参
async(function(err, results){
    // TODO
});
// 嵌套太深
// 阻塞代码
// 多线程编程
// 浏览器中指的JavaScript线程与UI渲染线程共用一个。
// 异步转同步
```

### 4.3 异步编程解决方案
#### 4.3.1 事件发布/订阅模式
```js
// 订阅
emitter.on('event', function(message){
    console.log(message);
});
// 发布
emitter.emit('event', 'I am message');
// 订阅时间就是一个高阶函数的应用。事件发布/订阅实现一个事件与多个回调函数的关联。
```

### 4.3.2 Promise/Deferred模式
then()
#### 4.3.3 流程控制库
next
async.series() 异步串行
async.parallel() 异步并行 
async.waterfall() 异步依赖 
async.auto();
### 4.4 异步并发控制

## 第5章 内存控制
### 5.1 V8的垃圾回收机制与内存限制
### 5.2 高效使用内存
### 5.3 内存指标
### 5.4 内存泄漏
### 5.5 内存泄漏排查
### 5.6 大内存应用

## 第6章 理解Buffer
### 6.1 Buffer结构
Buffer对象的内存分配不在V8的堆内存中，属于堆外内存，在Node的C++层面实现内存的申请。
Buffer.poolSize = 8 * 1024;
### 6.2 Buffer的转换
### 6.3 Buffer的拼接
### 6.4 Buffer与性能

## 第7章 网络编程
### 7.1 构建TCP服务
```ts
var net = require('net')
var server = net.createServer(function(socket){
    socket.on('data', function(data){
        socket.write('hello, new connect')
    })
    socket.on('end', function(){
        console.log('connect close')
    })
    socket.write('welcome')
})
server.listen(8124, function(){
    console.log('server bound')
})
```
#### 7.1.3 TCP服务的事件
1. 服务器时间
   * listening
   * connection
   * close
   * error
2. 连接事件
   * data write()
   * end
   * connect
   * drain
   * error
   * close
   * timeout

### 7.2 构建UDP服务
```ts
var dgram = require('dgram')
var socket = dgram.createSocket('udp4')
```
#### 7.2.4 UDP套接字事件
* message 当UDP套接字监听网卡端口后，接收到消息时触发该事件，触发携带的数据为消息Buffer对象和一个远程地址信息
* listening
* close
* error
  
### 7.3 构建HTTP服务
```ts
var http = require('http')
http.createServer(function(req, res){
    res.writeHead(200, {'Content-Type': 'text/plain'})
    res.end('hello')
}).listen(1337, '127.0.0.1')
```
### 7.4 构建WebSocket服务
### 7.5 网络服务与安全
SSL作为一种安全协议，在传输层提供对网络连接加密的功能。对于应用层而言是透明的，数据在传输层到应用层之前就已经完成了加密和解密的过程。
IETF将其标准化，成为TLS（Transport Layer Security）

Node在网络安全上提供了3个模块，crypto、tls、https
#### 7.5.1  TLS/SSL
1. 密钥
2. 数字证书

#### 7.5.2  TLS服务
#### 7.5.3  HTTPS服务

## 第8章 构建Web应用

## 第9章 玩转线程

## 第10章 测试

## 第11章 产品化