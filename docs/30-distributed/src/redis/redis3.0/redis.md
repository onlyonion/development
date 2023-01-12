redis.h
redis.c


## hierarchy
```plantuml
@startuml

class redisServer {
    redisDb *db
    int dbnum
}

class redisDb {
    dict *dict
    dict *expires
    int id
}

class redisClient

class dict {
    dictType *type
    dictht ht[2]
    int rehashidx
}

class dictht {
    dictEntry **table
}

class dictEntry {
    void *key
    union {
        void *val;
        uint64_t u64;
        int64_t s64;
    } v
}

redisServer "1" *- "*" redisDb
redisServer "1" *-- "*" redisClient
redisDb "1" *- "*" dict
dict "1" *- "2" dictht
dictht *- dictEntry
dictEntry *-- key
dictEntry *-- value

class redisObject {
    unsigned type:4
    unsigned encoding:4
    int refcount
    void *ptr
}
key -- redisObject
value -- redisObject

@enduml
```


## redisDb
[dict](/docs/30-distributed/src/redis/redis3.0/ds/dict.md)

```c

struct redisServer {
    pid_t pid;  /* 主进程 pid. */
    pthread_t main_thread_id; /* 主线程 id */
    char *configfile;  /*redis.conf 文件绝对路径*/
    redisDb *db; /* 存储键值对数据的 redisDb 实例 */
   int dbnum;  /* DB 个数 */
    dict *commands; /* 当前实例能处理的命令表，key 是命令名，value 是执行命令的入口 */
    aeEventLoop *el;/* 事件循环处理 */
    int sentinel_mode;  /* true 则表示作为哨兵实例启动 */

   /* 网络相关 */
    int port;/* TCP 监听端口 */
    list *clients; /* 连接当前实例的客户端列表 */
    list *clients_to_close; /* 待关闭的客户端列表 */

    client *current_client; /* 当前执行命令的客户端*/
};


/* Redis database representation. There are multiple databases identified
 * by integers from 0 (the default database) up to the max configured
 * database. The database number is the 'id' field in the structure. */
typedef struct redisDb {

    // 数据库键空间，保存着数据库中的所有键值对
    dict *dict;                 /* The keyspace for this DB */

    // 键的过期时间，字典的键为键，字典的值为过期事件 UNIX 时间戳
    dict *expires;              /* Timeout of keys with a timeout set */

    // 正处于阻塞状态的键
    dict *blocking_keys;        /* Keys with clients waiting for data (BLPOP) */

    // 可以解除阻塞的键
    dict *ready_keys;           /* Blocked keys that received a PUSH */

    // 正在被 WATCH 命令监视的键
    dict *watched_keys;         /* WATCHED keys for MULTI/EXEC CAS */

    struct evictionPoolEntry *eviction_pool;    /* Eviction pool of keys */

    // 数据库号码
    int id;                     /* Database ID */

    // 数据库的键的平均 TTL ，统计信息
    long long avg_ttl;          /* Average TTL, just for stats */

} redisDb;

```
