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

```
allocation.cpp
allocation.hpp
allocation.inline.hpp
barrierSet.cpp
barrierSet.hpp
barrierSet.inline.hpp
binaryTreeDictionary.cpp
binaryTreeDictionary.hpp
blockOffsetTable.cpp
blockOffsetTable.hpp
blockOffsetTable.inline.hpp
cardTableModRefBS.cpp
cardTableModRefBS.hpp
cardTableRS.cpp
cardTableRS.hpp
collectorPolicy.cpp
collectorPolicy.hpp
defNewGeneration.cpp
defNewGeneration.hpp
defNewGeneration.inline.hpp
filemap.cpp
filemap.hpp
freeBlockDictionary.cpp
freeBlockDictionary.hpp
freeList.cpp
freeList.hpp
gcLocker.cpp
gcLocker.hpp
gcLocker.inline.hpp
genCollectedHeap.cpp
genCollectedHeap.hpp
generation.cpp
generation.hpp
generation.inline.hpp
generationSpec.cpp
generationSpec.hpp
genMarkSweep.cpp
genMarkSweep.hpp
genOopClosures.hpp
genOopClosures.inline.hpp
genRemSet.cpp
genRemSet.hpp
genRemSet.inline.hpp
heap.cpp
heap.hpp
heapInspection.cpp
heapInspection.hpp
iterator.cpp
iterator.hpp
memRegion.cpp
memRegion.hpp
metachunk.cpp
metachunk.hpp
metadataFactory.hpp
metaspace.cpp
metaspace.hpp
metaspaceCounters.cpp
metaspaceCounters.hpp
metaspaceShared.cpp
metaspaceShared.hpp
modRefBarrierSet.hpp
oopFactory.cpp
oopFactory.hpp
padded.hpp
padded.inline.hpp
referencePolicy.cpp
referencePolicy.hpp
referenceProcessor.cpp
referenceProcessor.hpp
referenceProcessorStats.hpp
referenceType.hpp
resourceArea.cpp
resourceArea.hpp
sharedHeap.cpp
sharedHeap.hpp
space.cpp
space.hpp
space.inline.hpp
specialized_oop_closures.cpp
specialized_oop_closures.hpp
tenuredGeneration.cpp
tenuredGeneration.hpp
threadLocalAllocBuffer.cpp
threadLocalAllocBuffer.hpp
threadLocalAllocBuffer.inline.hpp
universe.cpp
universe.hpp
universe.inline.hpp
watermark.hpp
```

## 新生代
defNewGeneration.hpp
```c++
EdenSpace*       eden() const { return _eden_space; }
ContiguousSpace* from() const { return _from_space; }
ContiguousSpace* to()   const { return _to_space;   }
```
                