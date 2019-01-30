
@startuml

class MapperMethod {
    - final SqlCommand command
    - final MethodSignature method
}

class SqlCommand {

}

class MethodSignature {

}

MapperMethod +- SqlCommand
MapperMethod +- MethodSignature
MapperMethod --> SqlCommandType

enum SqlCommandType {
  UNKNOWN, INSERT, UPDATE, DELETE, SELECT, FLUSH;
}
@enduml