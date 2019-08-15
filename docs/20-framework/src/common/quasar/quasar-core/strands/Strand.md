co.paralleluniverse.strands.Strand

## hierarchy
```
Strand (co.paralleluniverse.strands)
    Fiber (co.paralleluniverse.fibers)
        VarFiber in Var (co.paralleluniverse.strands.dataflow)
    FiberStrand in Strand (co.paralleluniverse.strands)
    ThreadStrand in Strand (co.paralleluniverse.strands)
```

## define
```plantuml
@startuml

abstract class Strand

class FiberStrand
class ThreadStrand

class Fiber<V>
class VarFiber<T>

Strand ^-- FiberStrand
Strand ^-- ThreadStrand
Strand ^-- Fiber
Fiber ^-- VarFiber

Strand +-- FiberStrand
Strand +-- ThreadStrand


@enduml
```