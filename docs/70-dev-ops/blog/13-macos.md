## 黑苹果教程
### 1. 下载合适的镜像
### 2. Windows系统下分区
### 3. leopard写入分区
### 4. HFS格式读取软件安装
### 5. 删除多余驱动
### 6. 有需要替换内核、替换MBR
### 7. EasyBCD添加变色龙引导

## common
### keybord
* ⇪ Caps Lock
* ⇧ Shift
* ⌃ Control
* ⌥ Option   alt 或 windows
* ⌘ Command  花键  windows 或 alt

macOS常用的快捷键

Command + 空格键：Spotlight（推荐使用Alfred代替）
Command + {C/V/X/A/Z/F}：复制/粘帖/剪切/全选/撤销/查找
Command + Q：退出当前应用
Command + Option + Esc：强制退出应用程序管理器
Command + Delete：删除到回收站
选定文件-回车：重命名文件

[IntelliJ IDEA For Mac 快捷键](https://blog.csdn.net/rainytooo/article/details/51469126)

### 分辨率
SwitchResX For Mac v4.8.0 
解压密码：imac.hk

### 显示隐藏文件
```shell
defaults write com.apple.finder AppleShowAllFiles -bool true
# 需要重启finder
defaults write com.apple.finder AppleShowAllFiles -bool false
```

## cmd
```sh
touch .bash_profile
vi .bash_profile

alias ll='ls -alF'
alias la='ls -A'
alias l='ls -CF'
```

## jdk
```shell
# env
touch .bash_profile
vi .bash_profile
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.4.jdk/Contents/Home
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home
export JRE_HOME=${JAVA_HOME}/jre
PATH=$JAVA_HOME/bin:$PATH:.
CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:.
export JAVA_HOME
export PATH
export CLASSPATH
# exec
source .bash_profile
```

## maven
```shell
# tar mv env
export M2_HOME=/Users/lijicong/opt/java/apache-maven-3.3.9
export PATH=$PATH:$M2_HOME/bin
```
## tomcat

## ssh
```shell
ssh-keygen -t rsa -C "you_email"
# 多git账号
vim ~/.ssh/config
# default                                                                       
Host github
HostName github.com
User git
IdentityFile ~/.ssh/id_rsa
# two                                                                           
Host gitee
HostName gitee.com
User git
IdentityFile ~/.ssh/id_rsa_2
# test
ssh -T gitee
ssh -T github
```
## scp
```shell
whereis ssh
ps aux | grep ssh
# 系统偏好设置 -> 共享，选中远程登录
# ssh username@192.168.100.100
# scp 将当前路径下的.zshrc文件复制到远程主机的/Users/username目录下
scp .zshrc username@192.168.100.100:/Users/username/
```