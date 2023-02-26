# centos


## vmware 蓝屏
WIN+R键打开运行窗口输入“OptionalFeatures ”打开windows功能，打开Windows虚拟机监控程序平台和虚拟机平台

## net
内容选自《鸟哥的linux私房菜:基础学习篇》第四版

新版的CentOS 7 开始对于网卡的编号有另外一套规则，网卡的代号与网卡的来源有关

eno1：代表有主板bios内置的网卡
ens1: 代表有主板bios内置的PCI-E网卡
enp2s0: PCI-E独立网卡
eth0：如果以上都不使用，则回到默认的网卡名

ens33则属于第二种类型，即说明你的网卡是内置的PCI-E网卡
```sh
# ONBOOT=yes
vi /etc/sysconfig/network-scripts/ifcfg-ens33
sudo service network restart
ip addr
# install ifconfig
yum install -y net-tools.x86_64
```

## hostname
```sh
hostnamectl set-hostname node4
hostnamectl status
cat /etc/hostname
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

# 判断是不是虚拟机
lscpu
dmidecode -s system-product-name
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
yum list installed | grep java
yum -y remove java-1.7*
yum install java-1.8.0-openjdk

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