# centos

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

# mount
mkdir /mnt/cdrom
mount /dev/cdrom /mnt/cdrom
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
systemctl disable firewalld.service # 开机禁用防火墙
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
yum -y install wget

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