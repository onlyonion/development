# docker
- docker 镜像命令
- docker 容器命令
- dockerfile 命令

## cmd
```sh
# docker修改时间
docker run -it --cap-add SYS_TIME --rm --name centos centos /bin/bash

# /var/lib/docker/image/overlay2：存储镜像管理数据的目录
```

### docker 镜像常用命令
```sh
# search
docker search java
# pull
docker pull java
# images
docker images
# remove images
docker rmi hello-world
# remove all images
docker rmi -f ${docker images}
# 
docker commit -a zzyy -m "I am annotation"
```

### docker 容器常用命令
```sh
# 新建并启动容器
docker run java /bin/echo 'hello world'
docker run -d -p 91:80 nginx # -d后台运行 # -p 宿主机端口号:容器端口 http://docker宿主机ip:91
# 列出运行中的容器
docker ps # docker ps -a
# 停止容器
docker stop 容器ID
# 强制停止
docker kill 容器ID
# 启动已停止的容器
docker start 容器ID
# 重启容器
docker restart 容器ID
# 进入容器
docker attach 容器ID
docker inspect --format "{{.State.Pid}}" 容器ID
nsenter --target $PID --mount --uts --ipc --net --pid
# 删除容器
docker rm 容器ID
docker rm -f $(docker ps -a -q)
# 重命名
docker rename oldName newName
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

## Demo
```sh
# docker exec
docker exec -it 容器ID /bin/bash

# nginx
docker run -d -p 91:80 nginx

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