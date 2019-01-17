
## java logger framework
在Java开发中，常用的日志记录框架有JDKLog、Log4J、LogBack、SLF4J、SLF4J。

SLF4J：Simple Logging Facade for Java

正式因为在实际的项目应用中，有时候可能会从一个日志框架切换到另外一个日志框架的需求，这时候往往需要在代码上进行很大的改动。为了避免切换日志组件时要改动代码，这时候一个叫做 SLF4J（Simple Logging Facade for Java，即Java简单日志记录接口集）的东西出现了。

SLF4J（Simple Logging Facade for Java，即Java简单日志记录接口集）是一个日志的接口规范，它对用户提供了统一的日志接口，屏蔽了不同日志组件的差异。这样我们在编写代码的时候只需要看 SLF4J 这个接口文档即可，不需要去理会不同日之框架的区别。而当我们需要更换日志组件的时候，我们只需要更换一个具体的日志组件Jar包就可以了。

<!-- trace < debug < info < warn < error -->

[log](https://www.jianshu.com/p/f67c721eea1b)
* Logback：logback-spring.xml, logback-spring.groovy, logback.xml, logback.groovy
* Log4j：log4j-spring.properties, log4j-spring.xml, log4j.properties, log4j.xml
* Log4j2：log4j2-spring.xml, log4j2.xml
* JDK (Java Util Logging)：logging.properties