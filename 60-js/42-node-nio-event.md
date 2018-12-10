### 网络库
* libevent :名气最大，应用最广泛，历史悠久的跨平台事件库；
* libev :较libevent而言，设计更简练，性能更好，但对Windows支持不够好；
* libuv :开发node的过程中需要一个跨平台的事件库，他们首选了libev，但又要支持Windows，故重新封装了一套，linux下用libev实现，Windows下用IOCP实现；

[网络库](https://blog.csdn.net/lijinqi1987/article/details/71214974 )