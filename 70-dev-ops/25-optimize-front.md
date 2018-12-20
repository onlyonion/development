
前端优化的目的是什么 ?
1. 从用户角度而言，优化能够让页面加载得更快、对用户的操作响应得更及时，能够给用户提供更为友好的体验。
2. 从服务商角度而言，优化能够减少页面请求数、或者减小请求所占带宽，能够节省可观的资源。

按粒度大致可以分为两类，
* 页面级别的优化，例如 HTTP请求数、脚本的无阻塞加载、内联脚本的位置优化等

一个完整的请求都需要经过 DNS寻址、与服务器建立连接、发送数据、等待服务器响应、接收数据这样一个 “漫长” 而复杂的过程

* 代码级别的优化，例如 Javascript中的DOM 操作优化、CSS选择符优化、图片优化以及 HTML结构优化等等

[Web前端性能优化——如何提高页面加载速度](https://www.cnblogs.com/MarcoHan/p/5295398.html)

[前端性能优化方案都有哪些？](https://www.cnblogs.com/coober/p/8078847.html)

* 网络节点：HttpDNS优化
* 建连复用：SSL化，SPDY建连高复用
* 容器层面：离线化和预加载方案
* 前端组件：请求控制，域名收敛，图片库，前端性能CheckList