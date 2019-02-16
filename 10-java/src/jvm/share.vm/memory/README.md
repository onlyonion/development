hotspot/src/share/vm/memory

## package
```
allocation.hpp 内存分配
defNewGeneration.hpp 分代
generation.hpp
space.hpp
EdenSpace
TenuredSpace
threadLocalAllocBuffer.hpp
```

## 新生代
defNewGeneration.hpp
```c++
EdenSpace*       eden() const { return _eden_space; }
ContiguousSpace* from() const { return _from_space; }
ContiguousSpace* to()   const { return _to_space;   }
```
                