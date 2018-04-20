

## 分类
根据Javac对异常的处理要求，将异常类分为2类。

非检查异常（unckecked exception）：Error 和 RuntimeException 以及他们的子类。javac在编译时，不会提示和发现这样的异常，不要求在程序处理这些异常
检查异常（checked exception）：除了Error 和 RuntimeException的其它异常。javac强制要求程序员为这样的异常做预备处理工作（使用try…catch…finally或者throws）

检查和非检查是对于javac来说的，这样就很好理解和区分了



### try-catch-finally-return 建议

1.	不要在fianlly中使用return。
2.	不要在finally中抛出异常。
3.	减轻finally的任务，不要在finally中做一些其它的事情，finally块仅仅用来释放资源是最合适的。
4.	将尽量将所有的return写在函数的最后面，而不是try … catch … finally中。