
## start
```
JVM arguments [
    -Xms1g, -Xmx1g, -Xss1m, -XX:+AlwaysPreTouch, 
    -XX:+UseConcMarkSweepGC, -XX:CMSInitiatingOccupancyFraction=75, -XX:+UseCMSInitiatingOccupancyOnly,
    -XX:-OmitStackTraceInFastThrow, 
    -XX:+HeapDumpOnOutOfMemoryError, 
    -XX:HeapDumpPath=data, 
    -XX:ErrorFile=logs/hs_err_pid%p.log, 
    -XX:UseAVX=2, 
    -Xlog:gc*,gc+age=trace,safepoint:file=logs/gc.log:utctime,pid,tags:filecount=32,filesize=64m, 

    -Djava.awt.headless=true, 
    -Dfile.encoding=UTF-8, 
    -Djna.nosys=true, 
    -Dio.netty.noUnsafe=true, 
    -Dio.netty.noKeySetOptimization=true, 
    -Dio.netty.recycler.maxCapacityPerThread=0, 
    -Dlog4j.shutdownHookEnabled=false, 
    -Dlog4j2.disable.jmx=true, 
    -Djava.io.tmpdir=/tmp/elasticsearch.8VVpxLai, 
    -Djava.locale.providers=COMPAT, 
    -Des.path.home=/mnt/hgfs/Y/opt/elasticsearch-6.4.3, 
    -Des.path.conf=/mnt/hgfs/Y/opt/elasticsearch-6.4.3/config, 
    -Des.distribution.flavor=default, 
    -Des.distribution.type=tar]
```
