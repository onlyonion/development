
[TCP：三次握手、四次握手、backlog及其他](https://www.cnblogs.com/xrq730/p/6910719.html)

[TCP/IP协议（一）网络基础知识](http://www.cnblogs.com/imyalost/p/6086808.html)

[TCP SOCKET中backlog参数的用途是什么？](https://www.cnblogs.com/zengkefu/p/5602396.html)


TCP三次握手其实就是TCP连接建立的过程，三次握手的目的是同步连接双方的序列号和确认号并交换TCP窗口大小信息

为什么在第3步中客户端还要再进行一次确认呢？这主要是为了防止已经失效的连接请求报文段突然又传回到服务端而产生错误的场景

已失效的连接请求（超时请求）被处理了

backlog的定义是已连接但未进行accept处理的socket队列大小

全连接队列的大小未必是backlog的值，它是backlog与somaxconn（一个os级别的系统参数）的较小值