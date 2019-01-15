
## http

## https

## 服务端推送
ajax
websocket
轮询 
长连接

## cors
Cross-origin resource sharing

iframe JSONP

### 配置项
1. Access-Control-Allow-Origin
2. Access-Control-Allow-Methods
3. Access-Control-Expose-Headers
该字段可选。CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。
4. Access-Control-Allow-Credentials
该字段可选。它的值是一个布尔值，表示是否允许发送Cookie.默认情况下，不发生Cookie，即：false。对服务器有特殊要求的请求，比如请求方法是PUT或DELETE，或者Content-Type字段的类型是application/json，这个值只能设为true。如果服务器不要浏览器发送Cookie，删除该字段即可。
5. Access-Control-Max-Age
该字段可选，用来指定本次预检请求的有效期，单位为秒。在有效期间，不用发出另一条预检请求。