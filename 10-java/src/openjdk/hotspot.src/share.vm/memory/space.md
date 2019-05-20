

## hierarchy
```
- Space               -- an asbtract base class describing a heap area
  - CompactibleSpace  -- a space supporting compaction
    - CompactibleFreeListSpace -- (used for CMS generation)
    - ContiguousSpace -- a compactible space in which all free space is contiguous
      - EdenSpace     -- contiguous space used as nursery
        - ConcEdenSpace -- contiguous space with a 'soft end safe' allocation
      - OffsetTableContigSpace -- contiguous space with a block offset array that allows "fast" block_start calls
        - TenuredSpace -- (used for TenuredGeneration)
```

## define
```plantuml
@startuml


@enduml
```
