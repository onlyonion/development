# docker
- docker 镜像命令
- docker 容器命令
- dockerfile 命令

## net
windows 服务--> 找到vmware dhcp后重启

## cmd
```sh
# docker修改时间
docker run -it --cap-add SYS_TIME --rm --name centos centos /bin/bash
# /var/lib/docker/image/overlay2：存储镜像管理数据的目录
```

### docker 镜像常用命令
```sh
docker search java # search
docker pull java
docker images
docker rmi hello-world # remove images
docker rmi -f ${docker images} # remove all images
docker commit -a zzyy -m "I am annotation"
```

### docker 容器常用命令
```sh
docker run java /bin/echo 'hello world' # 新建并启动容器
docker run -d -p 91:80 nginx # -d后台运行 -p 宿主机端口号:容器端口 http://docker宿主机ip:91
docker ps # docker ps -a
docker stop 容器ID # 停止容器
docker kill 容器ID # 强制停止
docker start 容器ID # 启动已停止的容器
docker restart 容器ID # 重启容器
docker exec -it 容器ID /bin/bash # 进入容器
docker attach 容器ID
docker inspect --format "{{.State.Pid}}" 容器ID
nsenter --target $PID --mount --uts --ipc --net --pid
docker rm 容器ID # 删除容器
docker rm -f $(docker ps -a -q)
docker rename oldName newName # 重命名
# docker-compose.yml当前所在的目录运行 -d 后台运行
docker-compose up -d
```

## dockerfile
- 构建BUILD
  - FROM 基础镜像
  - MAINTAINER 作者的姓名和邮箱
  - COPY 仅仅拷贝
  - ADD 将宿主机目录下的文件拷贝进镜像且ADD命令自动处理URL和解压tar压缩包
  - RUN 运行shell命令
  - ONBUILD
- Both
  - WORKDIR 登录进之后的工作目录
  - USER
- 运行RUN
  - ENV 用来在构建镜像过程中设置环境变量
  - ARG
  - EXPOSE 暴露端口号
  - VOLUME 容器数据卷，用于数据保存和持久化工作
  - CMD 多个只运行最后一个，CMD会被docker run之后的参数替换
  - ENTRYPONIT
  
```sh
docker build -t test:v1 .  # -t tag指定名称:版本号 .当前目录

```

## Demo
```sh
docker exec -it 容器ID /bin/bash # docker exec
docker run -d -p 91:80 nginx # nginx

# tomcat
docker search tomcat
docker pull tomcat # tomcat:latest
docker images
docker run -it -p 8080:8080 tomcat # -it 交互式运行

# mysql
docker search mysql
docker pull mysql:5.6 # tag
docker images
docker run -it -p 3306:3306 mysql

docker run -p 12345:3306 --name mysql 
-v /zzyyuser/mysql/conf:/etc/mysql/conf.d
-v /zzyyuser/mysql/logs:/logs
-v /zzyyuse/mysql/data:/var/lib/mysql
-e MYSQL_ROOT_PASSWARD=123456
-d mysql:5.6

# redis
docker pull redis:3.2
docker run -p 6379:6329 
-v /zzyyuse/myredis/data:/data
-v /zzyyuse/myredis/conf/redis.conf:/usr/local/etc/redis/redis.conf # reids.conf是目录
-d redis:3.2 redis-server /user/local/etc/redis/redis.conf --appendonly yes

docker pull redis:latest
docker run -itd --name redis-test -p 6379:6379 redis --requirepass "mypassword"
docker exec -it redis-test /bin/bash
redis-cli
# redis-cli -h 127.0.0.1 -p 6379 -a "mypass"
# config set requirepass 123456
# config get requirepass

```

### hue
```sh
docker run -it -p 8888:8888 gethue/hue:latest

# https://github.com/cloudera/hue/tree/master/tools/docker/hue
docker run -it --name hue -p 8888:8888 -v /data/hue/hue-overrides.ini:/usr/share/hue/desktop/conf/hue.ini gethue/hue

# 使用mysql管理元数据
/build/env/bin/hue syncdb
/build/env/bin/hue migrate

# [HUE—大数据web管理器](https://blog.csdn.net/qq_18769269/article/details/80705960)
```

## docker与宿主机
```sh
# 从容器里面拷文件到宿主机
docker cp hue:/usr/share/hue/desktop/conf/hue.ini /data/hue/hue.ini
docker cp hue:/usr/share/hue/desktop/conf/hue-overrides.ini /data/hue/hue-overrides.ini
# 从宿主机拷文件到容器里面
docker cp /data/hue/hue.ini hue:/usr/share/hue/desktop/conf/hue.ini
docker cp /data/hue/hue-overrides.ini hue:/usr/share/hue/desktop/conf/hue-overrides.ini

## error
```sh
# WARNING: IPv4 forwarding is disabled. Networking will not work.
systemctl restart network && systemctl restart docker
```

### rocketmq
- 启动nameserver
- 启动broker
- 启动控制台

```sh
docker pull rocketmqinc/rocketmq
docker run -d -p 9876:9876 -v D:/docker/mq/other/tmp/data/namesrv/logs:/root/logs -v D:/docker/mq/other/tmp/data/namesrv/store:/root/store --name rmqnamesrv -e "MAX_POSSIBLE_HEAP=100000000" rocketmqinc/rocketmq sh mqnamesrv


docker run -e "JAVA_OPTS=-Drocketmq.config.namesrvAddr=172.16.228.77:9876 -Drocketmq.config.isVIPChannel=false" -p  9999:8080 -t --name rmConsole styletang/rocketmq-console-ng
172.16.228.77
```



```sh
docker run -d --restart=always --name rmqnamesrv -p 9876:9876 -v /docker/rocketmq/data/namesrv/logs:/root/logs -v /docker/rocketmq/data/namesrv/store:/root/store -e "MAX_POSSIBLE_HEAP=100000000"  rocketmqinc/rocketmq  sh mqnamesrv

docker run -d -p 10911:10911 -p 10909:10909 -v D:/opt/app/Git/docker/rocketmq/data/broker/logs:/root/logs -v  D:/opt/app/Git/docker/rocketmq/data/broker/store:/root/store -v D:/opt/app/Git/docker/rocketmq/conf/broker.conf:/opt/rocketmq/conf/broker.conf --name rmqbroker --link rmqnamesrv:namesrv -e "NAMESRV_ADDR=namesrv:9876" -e "MAX_POSSIBLE_HEAP=200000000" rocketmqinc/rocketmq sh mqbroker -c /opt/rocketmq/conf/broker.conf


docker run -d --restart=always --name rmqbroker --link rmqnamesrv:namesrv -p 10911:10911 -p 10909:10909 -v D:/opt/app/Git/docker/rocketmq/data/broker/logs:/root/logs -v D:/opt/app/Git/docker/rocketmq/data/broker/store:/root/store -v  D:/opt/app/Git/docker/rocketmq/conf/broker.conf:/opt/rocketmq3/conf/broker.conf -e "NAMESRV_ADDR=namesrv:9876" -e "MAX_POSSIBLE_HEAP=200000000" rocketmqinc/rocketmq sh mqbroker -c /opt/rocketmq3/conf/broker.conf
```

### redis
```sh
# https://docker.easydoc.net/
docker run -d -p 6379:6379 --name redis redis:latest
```

