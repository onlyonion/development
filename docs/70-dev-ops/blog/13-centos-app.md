

## vmware-tools
https://blog.csdn.net/zhujing16/article/details/88677253

在虚拟机开机的时候就点击 虚拟机(M) --> 安装VMware Tools(T)
```sh
# mkdir
mkdir /mnt/cdrom
# mount
mount -t iso9660 /dev/cdrom /mnt/cdrom
# cp
cp /mnt/cdrom/VMwareTools-9.9.3-2759765.tar.gz ~
tar -zxvf VMwareTools-9.9.3-2759765.tar.gz
# 安装
./vmware-install.pl

# 安装perl编译环境，安装之前配置网络
yum -y install perl gcc gcc-c++ make cmake kernel kernel-headers kernel-devel net-tools
```

## net
内容选自《鸟哥的linux私房菜:基础学习篇》第四版

新版的CentOS 7 开始对于网卡的编号有另外一套规则，网卡的代号与网卡的来源有关

eno1：代表有主板bios内置的网卡
ens1: 代表有主板bios内置的PCI-E网卡
enp2s0: PCI-E独立网卡
eth0：如果以上都不使用，则回到默认的网卡名

ens33则属于第二种类型，即说明你的网卡是内置的PCI-E网卡

## X Window 
yum（ Yellow dog Updater, Modified）是一个在Fedora和RedHat以及SUSE中的Shell前端软件包管理器。
```sh
# 命令模式
systemctl set-default multi-user.target
# 图形模式
systemctl set-default graphical.target 
# 安装 x window
yum groupinstall "X Window System"
yum grouplist
yum groupinstall "GNOME Desktop" "Graphical Administration Tools"
startx # 进入图形界面
```

## node
ln -s /root/front/node-v12.14.0-linux-x64/bin/node /usr/bin/node
ln -s /root/front/node-v12.14.0-linux-x64/bin/npm /usr/bin/npm
ln -s /root/front/node-v12.14.0-linux-x64/bin/npx /usr/bin/npx

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



cat <<EOF > daemon.json
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "registry-mirrors": [
    "https://registry.docker-cn.com", 
    "http://f1361db2.m.daocloud.io", 
    "https://docker.mirrors.ustc.edu.cn",
    "https://hub-mirror.c.163.com"
  ]
}
EOF
mv daemon.json /etc/docker/

## docker
###
```sh
yum install -y lrzsz 
# 所有节点关闭 SELinux
setenforce 0
sed -i --follow-symlinks 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/sysconfig/selinux
# 关闭swap
swapoff -a
vim /etc/fstab #注释掉swap分区
#/dev/mapper/cl-swap swap swap defaults 0 0

# subscription-manag
vim /etc/yum/pluginconf.d/subscription-manager.conf
[main]
#enabled=1
```

### remove
systemctl stop docker
rm -rf /etc/docker
rm -rf /run/docker
rm -rf /var/lib/dockershim
rm -rf /var/lib/docker
yum remove -y docker*
yum clear all

yum list installed | grep docker
ps -ef | grep docker
kill -9 pid


```sh
yum remove -y kubelet-1.22.4 kubectl-1.22.4 kubeadm-1.22.4 docker-ce
yum install -y kubelet-1.22.4 kubectl-1.22.4 kubeadm-1.22.4 docker-ce

systemctl enable kubelet
systemctl start kubelet
systemctl enable docker
systemctl start docker

# kubernetes 官方推荐 docker 等使用 systemd 作为 cgroupdriver，否则 kubelet 启动不了
cat <<EOF > daemon.json
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "registry-mirrors": ["https://ud6340vz.mirror.aliyuncs.com"]
}
EOF
mv daemon.json /etc/docker/

# 重启生效
systemctl daemon-reload
systemctl restart docker
```

## kubeadm
kubeadm init --image-repository=registry.aliyuncs.com/google_containers
kubeadm join 192.168.0.115:6443 --token qn3u0x.mff3wqbkkzo1ilvq --discovery-token-ca-cert-hash sha256:03a7a7589da0b3d84bf6ad866f0db04cc05973e55e429a929b4e1dc1ba0eb2e4 

kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml

