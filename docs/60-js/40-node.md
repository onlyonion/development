# Node

Node.js是一个让JavaScript运行在服务端的开发平台

## CommonJS
* 模板 modules
* 包 packages
* 系统 system
* 二进制 binary
* 控制台 console
* 编码 encodings
* 文件系统 filesystems
* 套接字 sockets
* 单元测试 unit testing

CommonJS定义的模块分为:{模块引用(require)} {模块定义(exports)} {模块标识(module)}
require()用来引入外部模块；exports对象用于导出当前模块的方法或变量，唯一的导出口；module对象就代表模块本身。

```js
//sum.js
exports.sum = function(){...做加操作..};

//calculate.js
var math = require('sum'); //  require 是同步的，服务器端位于磁盘；浏览器通过网络加载
exports.add = function(n){
    return math.sum(val,n);
};
```


CommonJS是主要为了JS在后端的表现制定的，不适合前端
AMD(异步模块定义)出现了，它就主要为前端JS的表现制定规范

 ```js
// Asynchronous Module Definition

// define(id?,dependencies?,factory);
// 声明模块的时候制定所有的依赖(dep)，并且还要当做形参传到factory中
// RequireJS
define(['dep1','dep2'],function(dep1,dep2){...});

// define(function(require,exports,module){...});
// seajs 更贴近 CommonJS Modules/1.1 和 Node Modules 规范


// RequireJS 和 Sea.js 都是模块加载器，倡导模块化开发理念，核心价值是让 JavaScript 的模块化开发变得简单自然
 ```

|  module   | endpoint  | impl      |
| :---:     | :---:     | :---:     |
| commonjs  | backend   | nodejs    |
| amd       | frontend  | requirejs |
| cmd       | frontend  | seajs     |

## 网络库
* libevent :名气最大，应用最广泛，历史悠久的跨平台事件库；
* libev :较libevent而言，设计更简练，性能更好，但对Windows支持不够好；
* libuv :开发node的过程中需要一个跨平台的事件库，他们首选了libev，但又要支持Windows，故重新封装了一套，linux下用libev实现，Windows下用IOCP实现；

[网络库](https://blog.csdn.net/lijinqi1987/article/details/71214974 )

## Promise