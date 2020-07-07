应用层协议
- http
- https
- http2
- quic

## http
### 服务端推送
ajax
websocket
轮询 
长连接
### cors
Cross-origin resource sharing   
iframe
JSONP
将不同源的服务通过代理收敛到同源

### 域
当两个域具有相同的协议(如http), 相同的端口(如80)，相同的host（如www.google.com)，那么我们就可以认为它们是相同的域（协议，域名，端口都必须相同）。
跨域就指着**协议，域名，端口**不一致，出于安全考虑，跨域的资源之间是无法交互的(例如一般情况跨域的JavaScript无法交互，当然有很多解决跨域的方案)

## https

## spdy & http2
```js
window.chrome.loadTimes()
```

NPN（Next Protocol Negotiation，下一代协议协商），是一个 TLS 扩展，由 Google 在开发 SPDY 协议时提出。
随着 SPDY 被 HTTP/2 取代，NPN 也被修订为 ALPN（Application Layer Protocol Negotiation，应用层协议协商）。


[ALPN协议](https://blog.csdn.net/yangguosb/article/details/80592777)
ALPN (Application Layer Protocol Negotiation)是TLS的扩展，允许在安全连接的基础上进行应用层协议的协商。
ALPN支持任意应用层协议的协商，目前应用最多是HTTP2的协商。在2016年，ALPN已经完全替代NPN了。

## QUIC & http3
QUIC（Quick UDP Internet Connection）是谷歌制定的一种基于UDP的低时延的互联网传输层协议。


