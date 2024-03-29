## io流相关概念
- 输入输出是相对于内存设备而言的
- 字节流：一次读入或读出是8位二进制
- 字符流：一次读入或读出是16位二进制
- 字节流和字符流的原理是相同的，只不过处理的单位不同而已。后缀是Stream是字节流，而后缀是Reader，Writer是字符流。

### 字节流      
用字节流处理字符数据可能会有编码问题，因为字节流是以字节为单位，没有编码，而字符流是以字符为单位传送数据，字符流即以**字节流+编码**

## 阻塞、非阻塞、同步、异步
- 阻塞 等待数据
- 非阻塞 不等待，立即返回但是没有数据。需要不断的询问，是否准备好。需要不断的主动询问kernel数据好了没有
- 同步
- 异步

### IO多路复用
IO多路复用，如select、poll、epoll。event driven IO
select/epoll这个function会不断的轮询所负责的所有socket，当某个socket有数据到达了，就通知用户进程

如果处理的连接数不是很高的话，使用select/epoll的web server不一定比使用multi-threading + blocking IO的web server性能更好，可能延迟还更大。
select/epoll的优势并不是对于单个连接能处理得更快，而是在于**能处理更多的连接**

在IO multiplexing Model中，实际中，对于每一个socket，一般都设置成为non-blocking，但是，如上图所示，整个用户的process其实是一直被block的。
只不过process是被select这个函数block，而不是被socket IO给block

### 异步		
异步IO库，例如libevent、libev、libuv
	
用户进程发起read操作之后，立刻就可以开始去做其它的事。而另一方面，从kernel的角度，当它受到一个asynchronous read之后，首先它会立刻返回，所以不会对用户进程产生任何block。然后，kernel会等待数据准备完成，然后将数据拷贝到用户内存，当这一切都完成之后，kernel会给用户进程发送一个signal，告诉它read操作完成了。

### IO的process (or thread)，系统内核(kernel)
1.	等待数据准备 (Waiting for the data to be ready)
2.	将数据从内核拷贝到进程中 (Copying the data from the kernel to the process)

### 阻塞与非阻塞
简单理解为需要做一件事能不能立即得到返回应答，如果不能立即获得返回，需要等待，那就阻塞了，否则就可以理解为非阻塞。
调用blocking IO会一直block住对应的进程直到操作完成，而non-blocking IO在kernel还准备数据的情况下会立刻返回。