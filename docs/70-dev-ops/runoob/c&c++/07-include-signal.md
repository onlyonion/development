## 预处理
预处理器是一些指令，指示编译器在实际编译之前所需完成的预处理。
所有的预处理器指令都是以井号（#）开头，只有空格字符可以出现在预处理指令之前。预处理指令不是 C++ 语句，所以它们不会以分号（;）结尾。
比如 #include、#define、#if、#else、#line 

```c++
// #define 预处理指令用于创建符号常量。该符号常量通常称为宏，指令的一般形式是：
#define macro-name replacement-text 


// 条件编译 
// 有几个指令可以用来有选择地对部分程序源代码进行编译。这个过程被称为条件编译。
#ifndef NULL
   #define NULL 0
#endif

// 只在调试时进行编译
#ifdef DEBUG
   cerr <<"Variable x = " << x << endl;
#endif

#if 0
   不进行编译的代码
#endif
```

* 预定义宏

```c++
__LINE__    //这会在程序编译时包含当前行号。
__FILE__	//这会在程序编译时包含当前文件名。
__DATE__	//这会包含一个形式为 month/day/year 的字符串，它表示把源文件转换为目标代码的日期。
__TIME__	//这会包含一个形式为 hour:minute:second 的字符串，它表示程序被编译的时间。
```

## C++ 信号处理
信号是由操作系统传给进程的中断，会提早终止一个程序。在 UNIX、LINUX、Mac OS X 或 Windows 系统上，可以通过按 Ctrl+C 产生中断。
有些信号不能被程序捕获，但是下表所列信号可以在程序中捕获，并可以基于信号采取适当的动作。这些信号是定义在 C++ 头文件 <csignal> 中。

```c++
SIGABRT	// abrt程序的异常终止，如调用 abort。
SIGFPE	// fpe错误的算术运算，比如除以零或导致溢出的操作。
SIGILL	// ill检测非法指令。
SIGINT	// int接收到交互注意信号。
SIGSEGV	// segv非法访问内存。
SIGTERM	// term发送到程序的终止请求。
```