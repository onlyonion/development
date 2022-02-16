## jdk
```sh
jps
jinfo -flags 1

# thread
jstack pid > pid.log

# memory
jstat -gcutil 1 1000
jmap -histo:live 1 | more
jmap -histo 1 | more
jmap -dump:live,format=b,file=dump.hprof 1
jmap -dump:format=b,file=dump.hprof 1
jmap -heap 1
jmap -histo 1 | head -50
jmap -histo:live 1 | head -20
jmap -dump:live,format=b,file=/root/logs/dump.hprof 1
jmap -dump,format=b,file=/root/logs/dump.hprof 1
jmap -dump:live,format=b,file=nezha-admin.hprof 1
```
## arthas
curl -O https://arthas.aliyun.com/arthas-boot.jar
java -jar arthas-boot.jar

dashboard

### greys
curl -sLk http://ompc.oss.aliyuncs.com/greys/install.sh|sh
./greys.sh 1
watch -b 类名 方法名 params[0]
watch -b 类名 方法名 params[0]+params[1]
watch -f 类名 方法名 returnObj -x 1