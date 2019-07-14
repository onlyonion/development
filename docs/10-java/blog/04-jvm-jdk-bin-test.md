
### jinfo -flags
```
Attaching to process ID 27320, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.181-b13
Non-default VM flags: 
-XX:-BytecodeVerificationLocal 
-XX:-BytecodeVerificationRemote 
-XX:CICompilerCount=3 
-XX:InitialHeapSize=134217728 
-XX:+ManagementServer 
-XX:MaxHeapSize=2118123520 
-XX:MaxNewSize=705691648 
-XX:MinHeapDeltaBytes=524288 
-XX:NewSize=44564480 
-XX:OldSize=89653248 
-XX:TieredStopAtLevel=1 
-XX:+UseCompressedClassPointers 
-XX:+UseCompressedOops 
-XX:+UseFastUnorderedTimeStamps 
-XX:-UseLargePagesIndividualAllocation 
-XX:+UseParallelGC
Command line:  
-agentlib:jdwp=transport=dt_socket,address=127.0.0.1:61914,suspend=y,server=n 
-XX:TieredStopAtLevel=1 
-Xverify:none 
-Dspring.output.ansi.enabled=always 
-Dcom.sun.management.jmxremote 
-Dcom.sun.management.jmxremote.port=61913 
-Dcom.sun.management.jmxremote.authenticate=false 
-Dcom.sun.management.jmxremote.ssl=false 
-Djava.rmi.server.hostname=localhost
-Dspring.liveBeansView.mbeanDomain 
-Dspring.application.admin.enabled=true 
-javaagent:D:\opt\idea\ideaIU-2018.3.1.win\lib\rt\debugger-agent.jar 
-Dfile.encoding=UTF-8
```