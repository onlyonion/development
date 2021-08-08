jinfo -flags 1
Attaching to process ID 1, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.161-b12
Non-default VM flags: -XX:+AlwaysPreTouch -XX:AutoBoxCacheMax=20000 -XX:CICompilerCount=18 -XX:ConcGCThreads=2 -XX:+ExplicitGCInvokesConcurrent -XX:G1HeapRegionSize=4194304 -XX:G1ReservePercent=15 -XX:GCLogFileSize=10485760 -XX:+HeapDumpOnOutOfMem
oryError -XX:HeapDumpPath=null -XX:InitialHeapSize=12884901888 -XX:InitialTenuringThreshold=6 -XX:InitiatingHeapOccupancyPercent=45 -XX:MarkStackSize=4194304 -XX:MaxDirectMemorySize=2147483648 -XX:MaxGCPauseMillis=200 -XX:MaxHeapSize=12884901888 -
XX:MaxMetaspaceSize=536870912 -XX:MaxNewSize=7730102272 -XX:MaxTenuringThreshold=6 -XX:MetaspaceSize=268435456 -XX:MinHeapDeltaBytes=4194304 -XX:NumberOfGCLogFiles=2 -XX:ParallelGCThreads=8 -XX:+ParallelRefProcEnabled -XX:+PrintCodeCache -XX:+Prin
tGC -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=5 -XX:ThreadStackSize=256 -XX:-UseBiasedLocking -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnordered
TimeStamps -XX:+UseG1GC -XX:+UseGCLogFileRotation -XX:-UseLargePages 
Command line:  -Xmx12g -Xms12g -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m -Xss256k -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1ReservePercent=15 -XX:InitiatingHeapOccupancyPercent=45 -XX:SurvivorRatio=5 -XX:MaxDirectMemorySize=1g -XX:+Always
PreTouch -XX:-UseLargePages -XX:+ParallelRefProcEnabled -XX:+ExplicitGCInvokesConcurrent -XX:ParallelGCThreads=8 -XX:-UseBiasedLocking -XX:AutoBoxCacheMax=20000 -XX:MaxTenuringThreshold=6 -Xloggc:/dev/shm/gc.log -XX:+PrintGCApplicationStoppedTime 
-XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintCodeCache -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=2 -XX:GCLogFileSize=10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/root/logs/ -XX:MaxDirectMemorySize=2G


https://stackoverflow.com/questions/30796111/g1-young-gc-does-not-free-memory-to-space-exhausted/30796879
https://www.infoq.com/articles/tuning-tips-G1-GC/
https://www.oracle.com/technical-resources/articles/java/g1gc.html
XX:G1ReservePercent


C:\Users\Administrator>jinfo.exe -flags 26152
Attaching to process ID 26152, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.221-b11
Non-default VM flags: -XX:-BytecodeVerificationLocal -XX:-BytecodeVerificationRemote -XX:CICompilerCount=4 -XX:ConcGCThreads=3 -XX:ErrorFile=null -XX:G1HeapRegionSize=1048576 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=null -XX:InitialHeapSize=786432000 -XX:MarkStackSize=4194304 -XX:MaxHeapSize=2147483648 -XX:MaxNewSize=1287651328 -XX:MinHeapDeltaBytes=1048576 -XX:-OmitStackTraceInFastThrow -XX:ReservedCodeCacheSize=251658240 -XX:SoftRefLRUPolicyMSPerMB=50 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseG1GC -XX:-UseLargePagesIndividualAllocation
Command line:  -Xms750m -Xmx2048m -XX:ReservedCodeCacheSize=240m -XX:+UseG1GC -XX:SoftRefLRUPolicyMSPerMB=50 -Xverify:none -ea -Dsun.io.useCanonCaches=false -Djava.net.preferIPv4Stack=true -Djdk.http.auth.tunneling.disabledSchemes="" -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -Djb.vmOptionsFile=C:\Users\Administrator\.IntelliJIdea2018.2\config\idea64.exe.vmoptions -Didea.jre.check=true -Dide.native.launcher=true -Didea.paths.selector=IntelliJIdea2018.2 -XX:ErrorFile=C:\Users\Administrator\java_error_in_idea_%p.log -XX:HeapDumpPath=C:\Users\Administrator\java_error_in_idea.hprof