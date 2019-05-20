java.sql.Driver

## hierarchy
```
Driver (java.sql)
    Driver (com.mysql.cj.jdbc)
        Driver (com.mysql.jdbc)
    DruidDriver (com.alibaba.druid.proxy)
    DriverSpy (net.sf.log4jdbc.sql.jdbcapi)
    NonRegisteringDriver (com.mysql.cj.jdbc)
        Driver (com.mysql.cj.jdbc)
    MockDriver (com.alibaba.druid.mock)
    DriverProxy in UnpooledDataSource (org.apache.ibatis.datasource.unpooled)
```

## define

```plantuml
@startuml

interface Driver {
    + Connection connect(String url, java.util.Properties info) throws SQLException
    + boolean acceptsURL(String url) throws SQLException
    DriverPropertyInfo[] getPropertyInfo(String url, java.util.Properties info) throws SQLException
}

@enduml
```



java.sql.DriverManager

## define
```plantuml
@startuml

class DriverManager {
    - final static CopyOnWriteArrayList<DriverInfo> registeredDrivers
    - static volatile int loginTimeout = 0
    - static volatile java.io.PrintWriter logWriter
    - static volatile java.io.PrintStream logStream
    - final static Object logSync
}

@enduml
```