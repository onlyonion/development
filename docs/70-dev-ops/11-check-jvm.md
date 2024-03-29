## jvm
```sh
# linux
top # shift + s
top -H
top -Hp {pid}

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
jmap -dump:format=b,file=/root/logs/dump.hprof 1
jmap -dump:histo,format=b,file=dump.hprof 1

# 压缩
tar -czvf new.tgz file1 file2
tar -xzvf test.tar.gz # 解压
unzip test.zip # 解压
unzip -d /temp test.zip # 指定目录
```
## arthas
```sh
# 下载、运行、使用
curl -O https://arthas.aliyun.com/arthas-boot.jar
java -jar arthas-boot.jar
dashboard
```

### greys
```sh
# 下载、运行、使用
curl -sLk http://ompc.oss.aliyuncs.com/greys/install.sh|sh
./greys.sh 1
watch -b 类名 方法名 params[0]
watch -b 类名 方法名 params[0]+params[1]
watch -f 类名 方法名 returnObj -x 1
```

## check domain
```sh
# linux
yum install -y traceroute
traceroute # 网络路由分析工具

# curl http code
curl -I -m 10 -o /dev/null -s -w %{http_code} www.baidu.com
curl -I -m 10 -o /dev/null -s -w %{http_code} m.dhvcmpd.cn

# windows
tracert
```