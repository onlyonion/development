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

Command+空格键：Spotlight（推荐使用Alfred代替）
Command+{C/V/X/A/Z/F}：复制/粘帖/剪切/全选/撤销/查找
Command+Q：退出当前应用
Command+Option+Esc：强制退出应用程序管理器
Command+Delete：删除到回收站
选定文件-回车：重命名文件

[IntelliJ IDEA For Mac 快捷键](https://blog.csdn.net/rainytooo/article/details/51469126)

### 分辨率
SwitchResX For Mac v4.8.0 
imac.hk

## jdk
```shell
# env
sudo vi /etc/profile
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home
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

## tomcat

## ssh
```shell
ssh-keygen -t rsa -C "you_email"
```

