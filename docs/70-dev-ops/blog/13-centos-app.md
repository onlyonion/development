

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

