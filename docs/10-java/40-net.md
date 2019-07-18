
## TCP/IP
端到端的、面向连接、面向字节流、全双工、可靠的传输层协议

![tcp/ip](./img/tcp-ip-all-protocols.jpg)

* [TCP：三次握手、四次握手、backlog及其他](https://www.cnblogs.com/xrq730/p/6910719.html)
* [TCP/IP协议（一）网络基础知识](http://www.cnblogs.com/imyalost/p/6086808.html)
* [TCP SOCKET中backlog参数的用途是什么？](https://www.cnblogs.com/zengkefu/p/5602396.html)
                                                             
* TCP三次握手其实就是TCP连接建立的过程，三次握手的目的是同步连接双方的序列号和确认号并交换TCP窗口大小信息   
* 为什么在第3步中客户端还要再进行一次确认呢？这主要是为了防止已经失效的连接请求报文段突然又传回到服务端而产生错误的场景
* 已失效的连接请求（超时请求）被处理了                                         
* backlog的定义是已连接但未进行accept处理的socket队列大小                      
* 全连接队列的大小未必是backlog的值，它是backlog与somaxconn（一个os级别的系统参数）的较小值  

TCP/IP协议中针对TCP默认开启了Nagle算法。Nagle算法通过减少需要传输的数据包，来优化网络。在内核实现中，数据包的发送和接受会先做缓存，分别对应于写缓存和读缓存。
启动TCP_NODELAY，就意味着禁用了Nagle算法，允许小包的发送。时敏感型，同时数据传输量比较小的应用。
对于关闭TCP_NODELAY，则是应用了Nagle算法。数据只有在写缓存中累积到一定量之后，才会被发送出去，这样明显提高了网络利用率，但是这由不可避免地增加了延时。

## Socket
这是为了实现以上的通信过程而建立成来的通信管道，其真实的代表是客户端和服务器端的一个通信进程，双方进程通过socket进行通信，而通信的规则采用指定的协议。
socket只是一种连接模式，不是协议，tcp、udp，简单的说（虽然不准确）是两个最基本的协议,很多其它协议都是基于这两个协议。
如http就是基于tcp的，用socket可以创建tcp连接，也可以创建udp连接，这意味着，用socket可以创建任何协议的连接，因为其它协议都是基于此的。

## http
基于请求/响应模式的、无状态的协议

## https
HTTP 协议中没有加密机制，但可以通过和SSL（Secure Socket Layer，安全套接层）或TLS（Transport LayerSecurity，安全层传输协议）的组合使用，加密 HTTP 的通信内容。

HTTP + 加密 + 认证 + 完整性保护 = HTTPS
HTTPS 是身披 SSL 外壳的 HTTP

### http隐患
HTTP 协议的实现本身非常简单，不论是谁发送过来的请求都会返回响应，因此不确认通信方，会存在以下各种隐患。
1. 无法确定请求发送至目标的 Web 服务器是否是按真实意图返回响应的那台服务器。有可能是已伪装的 Web 服务器。
2. 无法确定响应返回到的客户端是否是按真实意图接收响应的那个客户端。有可能是已伪装的客户端。
3. 无法确定正在通信的对方是否具备访问权限。因为某些Web 服务器上保存着重要的信息， 只想发给特定用户通信的权限。
4. 无法判定请求是来自何方、出自谁手。
5. 即使是无意义的请求也会照单全收。无法阻止海量请求下的DoS 攻击（ Denial of Service， 拒绝服务攻击） 。

## Http2                                                    
1. 异步连接多路复用                                                 
2. 头部压缩                                                     
3. 请求/响应管线化                                                 
                                                             
保持与HTTP 1.1语义的向后兼容性也是该版本的一个关键目标。   

多路复用、Stream、头部压缩、流量控制、服务端推送

## SPDY                                                      
SPDY（读作“SPeeDY”）是Google开发的基于TCP的应用层协议，用以最小化网络延迟，提升网络速度
SPDY并不是一种用于替代HTTP的协议，而是对HTTP协议的增强。                           

HTTP实现的瓶颈之一是其并发要**依赖于多重连接**。HTTP管线化技术可以缓解这个问题，但也只能做到部分多路复用。      
此外，已经证实，由于存在中间干扰，浏览器无法采用管线化技术。                               
                                                             
SPDY在单个连接之上增加了一个帧层，用以多路复用多个并发流。帧层针对HTTP类的请求响应流进行了优化

### SPDY 原理                                                  
在SSL层上增加一个SPDY会话层，以在一个TCP连接中实现并发流。                           
通常的HTTP GET和POST格式仍然是一样的；然而SPDY为编码和传输数据设计了一个新的帧格式。           
流是双向的，可以在客户端和服务器端启动。                                         
SPDY旨在通过基本（始终启用）和高级（可选启用）功能实现更低的延迟。        

## QUIC
避免前序包阻塞（HOL阻塞）、零RTT建连、FEC前向纠错

QUIC（Quick UDP Internet Connection）是谷歌制定的一种基于UDP的低时延的互联网传输层协议。
QUIC很好地解决了当今传输层和应用层面临的各种需求，包括处理更多的连接，安全性，和低延迟。QUIC融合了包括TCP，TLS，HTTP/2等协议的特性，但基于UDP传输。