架构视图 LDPPS
- 逻辑视图 功能需求，提供给最终用户的服务
- 开发视图 软件模块的组织与管理，软件编程人员
- 进程视图 运行特性，系统集成人员
- 物理视图 硬件配置，系统工程人员
- 场景 刻画构件之间的相互关系，将四个视图有机联系起来


CARP ES
- C
  - concurrent thread, lock, isolation, visibility, order
  - consistency
  - cache buffer
  - congestion
  - current-limiting
  - circuit breaker
  - cluster
- A
  - avaliable basic avaliable, soft state, eventual consisitency
  - atomic
- R
  - reliable TCP可靠性，序号、校检、确认、重试（超时重试、冗余ACk）
  - missing
  - traffic scheduling
  - timeout overtime
  - retry
  - idempotent
  - repetition
- P
  - partition tolerance
  - performance
  - persistence
- E
  - extension
  - instruct
  - rpc
  - message queue
- S
  - scalability
  - segment
  - sharding
  - slice
  - section
  - security