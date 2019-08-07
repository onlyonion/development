hotspot/src/share/vm/classfile/classLoader.hpp


```plantuml
@startuml

class CHeapObj
class MetaIndex

class ClassPathEntry
class ClassPathDirEntry
class ClassPathZipEntry
class LazyClassPathEntry

ClassPathEntry ^-- ClassPathDirEntry
ClassPathEntry ^-- ClassPathZipEntry
ClassPathEntry ^-- LazyClassPathEntry

class PackageHashtable
class PackageInfo

class PerfClassTraceTime

@enduml
```