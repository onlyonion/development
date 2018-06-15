
## aop
面向切面的编程，是一种编程技术，允许程序模块化横向切割关注点，或横切典型的责任划分，如日志和事务管理

AOP核心就是切面，它将多个类的通用行为封装成可重用的模块，该模块含有一组API提供横切功能。

## concept

1.	连接点	一个应用程序的某个位置
2.	通知		在方法执行前或执行后要做的动作。
	spring五种类型通知
	-	before
	-	after
	-	after-returing
	-	after-throwing
	-	around
3.	切点		一个或一组连接点，通知将在这些位置执行。可以通过表达式或匹配的方式指明切入点

![spring-aop-concept.png](./img/spring-aop-concept.png "spring-aop-concept.png") 