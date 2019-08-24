
## 安装
安装路径 /usr/local/lib
- apt方式
- dpkg方式 dpkg -L packagename
- 源码安装

apt安装
下载路径 /var/cache/apt/archives
文档一般在 /usr/share
可执行文件 /usr/bin
配置文件 /etc
lib文件 /usr/lib

## docker
```sh
wget -qO- https://get.docker.com/ | sh # 获取最新版本的 Docker 安装包
sudo usermod -aG docker onion 
sudo service docker start
docker run hello-world
```

### docker redis
```
docker run -p 6379:6379 
-v /home/user/myredis/data:/data
-v /home/user/myredis/conf/redis.conf:/usr/local/etc/redis/redis.conf
-d redis:3.2 redis-server /usr/local/etc/redis/redis.conf
-- appendonly yes
```

### docker tomcat
### docker mysql
### docker redis
### docker nginx

## 应用软件

### 输入法
```sh
# 安装依赖fcitx
sudo apt-get install fcitx-bin
sudo apt-get install fcitx-table

# 下载搜狗.deb 双击安装

sudo apt-get update
sudo add-apt-repository ppa:fcitx-team/nightly
sudo apt-get -f install
sudo apt-get upgrade

```
### ubuntu theme
```sh
sudo apt-get update
sudo apt-get install gnome-tweak-tool
sudo apt-get install gnome-sh-extensions
```

### 安装仿宋
```sh
# copy
sudo cp simfang.ttf /usr/share/fonts
cd  /usr/share/fonts
sudo chmod 644 simfang.ttf
# update cache
sudo mkfontscale
sudo mkfontdir
sudo fc-cache -fsv
```
### 快捷方式
``` sh
# vim jetbrains-idea.desktop  # 注意文件的后缀是 .desktop
[Desktop Entry]
Version=2019.1.1     
Name=jetbrains-idea
Comment=jetbrains-idea
Exec=/usr/local/lib/idea-IU-191.6707.61/bin/idea.sh
Icon=/usr/local/lib/idea-IU-191.6707.61/bin/idea.png
Terminal=false
Type=Application
Categories=Utility;Application;

# 复制到 Unity 启动器中 
/usr/share/applications/
```

### windows + ubuntu + easybcd
```sh
# easybcd 添加grub启动项
# 注意 vmlinuz.efi 与 initrd.lz 是否有后缀名
title Install Ubuntu
root (hd0,0)
kernel (hd0,0)/vmlinuz.efi boot=casper iso-scan/filename=/ubuntu-14.04-desktop-amd64.iso locale=zh_CN.UTF-8
initrd (hd0,0)/initrd.lz

title reboot
reboot

title halt
halt
```
安装后没有ubuntu启动项，回到演示盘
```sh
sudo fdisk -l
sudo -i
mkdir /media/ubuntu
mount /dev/sdb6 /media/ubuntu # sda8 安装的ubuntu分区
```
### vscode
```sh
# 双击.deb 安装之后 运行code启动
/usr/share/code
```