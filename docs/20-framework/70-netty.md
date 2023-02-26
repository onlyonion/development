`# netty

## 优点
1. 高性能，低延迟
   - Netty的I/O模型基于非阻塞I/O实现，底层依赖JDK NIO框架的多路复用器Selector。
   - 一个多路复用器Selector可以同时轮询多个Channel，采用epoll模式后，只需要一个线程负责Selector的轮询，接入成千上万的客户端
   - 基于Reactor模式进行事件分发，支持单Reactor、多线程Reacor和主从多线程Reactor，避免同步问题和多线程切换带来的资源消耗
2. 弥补Java NIO的缺陷
3. 资源消耗低 内存池、对象池，零拷贝

https://blog.csdn.net/weixin_41402069/article/details/125673949