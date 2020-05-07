# centos
yum（ Yellow dog Updater, Modified）是一个在Fedora和RedHat以及SUSE中的Shell前端软件包管理器。

```sh
# 命令模式
systemctl set-default multi-user.target
# 图形模式
systemctl set-default graphical.target 
```

## net
```sh
# ONBOOT=yes
vi /etc/sysconfig/network-scripts/ifcfg-ens33 
sudo service network restart
ip addr
# install ifconfig
yum install -y net-tools.x86_64
```

## common
```sh
# LANG="zh_CN.UTF-8" LANG="en_US.UTF-8"
vim locale.conf

# vmware-tools
cd vmware-tools-distrib
sudo ./vmware-install.pl
# git
sudo yum install -y git
# rzsz
yum install -y lrzsz
# node
yum module list nodejs
sudo yum module install nodejs
# 将Python2建立软连接Python
ln -s /usr/bin/python2 /usr/bin/python
```

## java -jar
```sh
java -jar demo.jar
java -jar demo.jar &
nohup java -jar demo.jar &
nohup java -jar demo.jar >/dev/null & # 输出内容不打印屏幕，输出到文件中
netstat -nlp | grep :9181 # 查看某端口占用的线程的pid
```

## docker
[CentOS Docker 安装](https://www.runoob.com/docker/centos-docker-install.html)

```sh
# 安装containerd.io
wget https://download.docker.com/linux/centos/7/x86_64/edge/Packages/containerd.io-1.2.6-3.3.el7.x86_64.rpm
yum install -y  containerd.io-1.2.6-3.3.el7.x86_64.rpm

#
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
sudo yum install docker-ce docker-ce-cli containerd.io
sudo systemctl start docker
sudo docker run hello-world
```


## gitlab
```sh
cat /opt/gitlab/embedded/service/gitlab-rails/VERSION # 查看版本

gitlab-ctl start #启动全部服务
gitlab-ctl restart #重启全部服务
gitlab-ctl stop #停止全部服务
gitlab-ctl restart nginx #重启单个服务
gitlab-ctl status #查看全部组件的状态
gitlab-ctl show-config #验证配置文件
gitlab-ctl uninstall #删除gitlab(保留数据）
gitlab-ctl cleanse #删除所有数据，重新开始
gitlab-ctl tail <svc_name>  #查看服务的日志
gitlab-rails console production #进入控制台 ，可以修改root 的密码
```
### gitlab install

[清华大学开源软件镜像站](https://mirror.tuna.tsinghua.edu.cn/help/gitlab-ce/)
```sh
# 新建 /etc/yum.repos.d/gitlab-ce.repo，内容为
[gitlab-ce]
name=Gitlab CE Repository
baseurl=https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/yum/el$releasever/
gpgcheck=0
enabled=1

# 再执行
sudo yum makecache
sudo yum install gitlab-ce

# run
sudo gitlab-ctl reconfigure
```
[CentOS GitLab搭建](https://segmentfault.com/a/1190000021753705)

## 防火墙
```sh
# 查看防火墙状态
firewall-cmd --state   #running | not running
# 向firewall永久添加需要开放的端口
firewall-cmd --permanent --zone=public --add-port=8080/tcp
# 加载配置，使得修改有效。
firewall-cmd --reload
# 查看开启的端口，出现8080/tcp这开启正确
firewall-cmd --permanent --zone=public --list-ports
# 关闭防火墙
systemctl stop firewalld.service
systemctl disable firewalld.service
```
## gitlab runner
1. 安装runner
2. 注册runner
3. 运行runner

```sh
# 手动安装
curl -LJO https://gitlab-runner-downloads.s3.amazonaws.com/latest/rpm/gitlab-runner_amd64.rpm
rpm -i gitlab-runner_amd64.rpm
# 注册
gitlab-runner register
```

### 清华大学源
[gitlab](https://mirror.tuna.tsinghua.edu.cn/help/gitlab-runner/)
```sh
# 新建 /etc/yum.repos.d/gitlab-runner.repo，内容为

[gitlab-runner]
name=gitlab-runner
baseurl=https://mirrors.tuna.tsinghua.edu.cn/gitlab-runner/yum/el$releasever-$basearch/
repo_gpgcheck=0
gpgcheck=0
enabled=1
gpgkey=https://packages.gitlab.com/gpg.key

# 再执行
sudo yum makecache
sudo yum install gitlab-runner

# 命令
gitlab-runner register
gitlab-runner status
gitlab-runner start
gitlab-runner restart
```


### gitlab pages
gitlab内置的nginx
1、Pages部署目录：/var/opt/gitlab/gitlab-rails/shared/pages
2、内置Nginx目录：/var/opt/gitlab/nginx
```sh
# gitlab-ctl restart
ok: run: alertmanager: (pid 14835) 0s
ok: run: gitaly: (pid 14852) 1s
ok: run: gitlab-exporter: (pid 14876) 0s
ok: run: gitlab-workhorse: (pid 14895) 0s
ok: run: grafana: (pid 14907) 0s
ok: run: logrotate: (pid 14982) 0s
ok: run: nginx: (pid 15009) 1s
ok: run: node-exporter: (pid 15014) 0s
ok: run: postgres-exporter: (pid 15021) 1s
ok: run: postgresql: (pid 15031) 0s
ok: run: prometheus: (pid 15041) 0s
ok: run: redis: (pid 15058) 1s
ok: run: redis-exporter: (pid 15064) 0s
ok: run: sidekiq: (pid 15075) 0s
ok: run: unicorn: (pid 15087) 0s
# gitlab-ctl restart nginx
# http://IP:端口/gitlab账号/工程名/public/
```
