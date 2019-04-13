org.apache.ibatis.executor.keygen.KeyGenerator

## hierarchy
```
KeyGenerator (org.apache.ibatis.executor.keygen)
    NoKeyGenerator (org.apache.ibatis.executor.keygen)
    Jdbc3KeyGenerator (org.apache.ibatis.executor.keygen)
        MultipleJdbc3KeyGenerator (com.github.abel533.mapperhelper)
    SelectKeyGenerator (org.apache.ibatis.executor.keygen)
```

## define
```plantuml
@startuml

interface KeyGenerator {
    + void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter)
    + void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter);
}

KeyGenerator ^.. NoKeyGenerator
KeyGenerator ^.. Jdbc3KeyGenerator
KeyGenerator ^.. SelectKeyGenerator

Jdbc3KeyGenerator ^-- MultipleJdbc3KeyGenerator

class NoKeyGenerator
class Jdbc3KeyGenerator
class MultipleJdbc3KeyGenerator
class SelectKeyGenerator

@enduml
```