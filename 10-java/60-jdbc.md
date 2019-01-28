
## java connect db

1.	加载数据库驱动程序
2.	连接数据
3.	执行SQL语句，并返回执行状态和查询结果
4.	关闭数据库连接


jdbc是sun公司发布的一套数据库实现标准，只提供接口，不具体实现。具体的实现过程由各数据库开发厂商决定。

## java.sql包中主要的类和接口包括
1.	Connection接口，定义为：public interface Connection
2.	Driver接口，定义为：public interface Driver
3.	DriverManager类，定义为：public class DriverManager
4.	PreparedStatement接口，定义为：public interface PreparedStatement extends Statement
5.	ResultSet接口，定义为：public interface ResultSet
6.	SQLException类，定义为：public class SQLException extends java.lang.Exception
7.	Statement接口，定义为：public interface Statement

## SPI