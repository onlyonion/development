[TCP Nagle算法](https://blog.csdn.net/asklw/article/details/79246959)

减少网络上小包数量

TCP/IP协议中，无论发送多少数据，总是要在数据前面加上协议头，同时，对方接收到数据，也需要发送ACK表示确认。为了尽可能的利用网络带宽，TCP总是希望尽可能的发送足够大的数据。

#### TCP_NODELAY
默认情况下，发送数据采用Negle 算法。这样虽然提高了网络吞吐量，但是实时性却降低了，在一些交互性很强的应用程序来说是不允许的，使用TCP_NODELAY选项可以禁止Negale 算法。