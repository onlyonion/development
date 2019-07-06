
## 名词
* TTL Time To Live      生存时间
* RTT Round-Trip Time   往返时延

## dns
```
@startuml
title 域名解析流程图
skinparam state {
  StartColor MediumBlue
  EndColor Red
  BorderColor Gray
  ArrowColor Gray
  ArrowFontColor Indigo
  BackgroundColor<<Request>> AliceBlue
  BackgroundColor<<Answer>> HotPink
}

state UserClient {
    [*] --> 请求访问域名
    请求访问域名 --> 本机host
    本机host -right-> 成功访问域名 : 有映射
    本机host --> 本机DNS解析器 : 没有映射
    本机DNS解析器 -up-> 成功访问域名 : 有缓存
    成功访问域名 --> 本机DNS解析器 : 缓存更新
    本机DNS解析器 --> [*]
    note left : 本图展示正常解析流程\n未成功访问域名不能结束\n（最左支路径无效）
    
    state 请求访问域名 : ephen.me.
    state 请求访问域名<<Request>>
    state 本机host : Windows 目录：
    state 本机host : C:\Windows\System32\drivers\etc
    state 成功访问域名 : ephen.me.
    state 成功访问域名<<Answer>>
    state 本机DNS解析器 : Windows 查看命令：
    state 本机DNS解析器 : ipconfig /displaydns
}

state DNSServer {
    本机DNS解析器 -right-> 本地DNS : 没有缓存
    本地DNS -right-> 根域名服务器 : 没有解析
    本地DNS -left-> 成功访问域名 : 有解析
    根域名服务器 --> 顶级域名服务器
    顶级域名服务器 --> 域名服务器
    域名服务器 --> 本地DNS : 获取结果并缓存

    state 本地DNS : 用户配置（8.8.8.8）
    state 根域名服务器 : a.root-servers.net.
    state 根域名服务器 : b.root-servers.net.
    state 根域名服务器 : ...
    state 根域名服务器 : m.root-servers.net.
    state 顶级域名服务器 : a0.nic.me.
    state 顶级域名服务器 : b0.nic.me.
    state 顶级域名服务器 : ...
    state 域名服务器 : lv3ns1.ffdns.net.
    state 域名服务器 : lv3ns2.ffdns.net.
    state 域名服务器 : lv3ns3.ffdns.net.
    state 域名服务器 : lv3ns4.ffdns.net.
}
@enduml
```