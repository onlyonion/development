java.sql
## package
```
Array
BatchUpdateException
Blob
CallableStatement
ClientInfoStatus
Clob
Connection
DatabaseMetaData
DataTruncation
Date
Driver
DriverAction
DriverInfo
DriverManager
DriverPropertyInfo
JDBCType
NClob
ParameterMetaData
PreparedStatement
PseudoColumnUsage
Ref
ResultSet
ResultSetMetaData
RowId
RowIdLifetime
Savepoint
SQLClientInfoException
SQLData
SQLDataException
SQLException
SQLFeatureNotSupportedException
SQLInput
SQLIntegrityConstraintViolationException
SQLInvalidAuthorizationSpecException
SQLNonTransientConnectionException
SQLNonTransientException
SQLOutput
SQLPermission
SQLRecoverableException
SQLSyntaxErrorException
SQLTimeoutException
SQLTransactionRollbackException
SQLTransientConnectionException
SQLTransientException
SQLType
SQLWarning
SQLXML
Statement
Struct
Time
Timestamp
Types
Wrapper
```

## overview
```plantuml
@startuml

interface Wrapper
interface AutoCloseable

AutoCloseable ^-- DataSource
AutoCloseable ^-- Connection
AutoCloseable ^-- Statement
AutoCloseable ^-- ResultSet

Wrapper ^-- DataSource
Wrapper ^-- Connection
Wrapper ^-- Statement
Statement ^-- PreparedStatement
PreparedStatement ^-- CallableStatement
Wrapper ^-- ResultSet

interface DataSource
interface Connection
interface Statement 
interface PreparedStatement 
interface CallableStatement
interface ResultSet

Wrapper ^-- DatabaseMetaData
Wrapper ^-- ParameterMetaData
Wrapper ^-- ResultSetMetaData
interface DatabaseMetaData
interface ParameterMetaData
interface ResultSetMetaData

@enduml
```

## framework
- mybatis
- mysql
- sharding-jdbc