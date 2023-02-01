
### 多线程
```config
# 设置io-thread-do-reads配置项为yes，表示启用多线程
io-threads-do-reads yes

# 设置线程个数。一般来说，线程个数要小于Redis实例所在机器的CPU核个数
# 例如，对于一个8核的机器来说，Redis官方建议配置6个IO线程。
io-threads  6
```


### slowlog
slowlog-max-len 选项指定服务器最多保存多少条慢查询日志
slowlog-log-slower-than  选项指定执行时间超过多少微秒（1 秒等于 1,000,000 微秒）的命令请求会被记录到日志上

a、slowlog-max-len 不要设置过小，通常在1000左右
b、slowlog-log-slower-than 不要设置过大，默认10ms，通常设置1ms
c、定期持久化慢查询

```sh
config set slowlog-max-len 128
config set slowlog-log-slower-than=1000

slowlog get n # 获取慢查询队列
slowlog len # 获取慢查询队列长度
slowlog reset # 清空队列
```