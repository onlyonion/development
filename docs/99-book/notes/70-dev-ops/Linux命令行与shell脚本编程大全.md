《Linux命令行与shell脚本编程大全（第2班）》 美 Richard Blum著 吴海峰 译
# content
* 第一部分 Linux命令行 环境变量、文件系统、安装软件、编辑器
* 第二部分 shell脚本编程基础 构建、输入输出
* 第三部分 高级shell脚本编程 函数、sed、awk、正则
* 第四部分 高级shell脚本编程主题 数据库、Web、E-mail、实用工具

# 第一部分 Linux命令行
## 第1章 初试Linux shell
## 第2章 走进shell
## 第3章 基本的bash shell命令
### 3.3 bash手册
`man bash`
### 3.4 浏览文件系统
```shell
cd 
ls -l
touch # 创建文件
cp
cp -l # 链接文件
mv
rm
# 目录
mkdir
rmdir
rm
# 查看文件内容
stat # 查看文件统计信息
file  # 查看文件类型
cat # 查看整个文件
more # 每页后停下来
tail -f
head
```
## 第4章 更多的bash shell命令
```shell
# 进程
ps
top
kill
# 磁盘
mount
umount
df # 查看磁盘的使用情况
du # 显示某个特定目录的磁盘使用情况
# 处理数据文件
sort
uniq
grep
gzip
tar
```
## 第5章 使用Linux环境变量
## 第6章 理解Linux文件权限
## 第7章 管理文件系统
## 第8章 安装软件程序
### 8.1 包管理基础
### 8.2 基于Debian的系统
### 8.3 基于Red Hat的系统
### 8.4 从源码安装
## 第9章 使用编辑器
### 9.1 Vim编辑器
### 9.2 Emacs编辑器
### 9.3 KDE系编辑器
### 9.4 GNOME编辑器

# 第二部分 shell脚本编程基础
## 第10章 构建基本脚本
## 第11章 使用结构化命令
## 第12章 更多的结构化命令
## 第13章 处理用户输入
## 第14章 呈现数据
## 第15章 控制脚本

# 第三部分 高级shell脚本编程
## 第16章 创建函数
## 第17章 图形化桌面上的脚本编程
## 第18章 初试sed和awk
```shell
##### sed
sed options script file
# -e script
# -f file
# n
echo "This is a test" | sed 's/test/big test' # This is a big test

##### gawk
gawk options program file

gawk '{print $1}' /etc/passwd
gawk -F: '{print $1}' /etc/passwd # 指定字段分隔符

```
## 第19章 正则表达式
```shell
ls -al  da* # 只列出文件名以da开头的文件
```
## 第20章 sed进阶
## 第21章 gwak进阶
## 第22章 使用其他shell
bash shell
```shell
#!/bin/bash
```
dash shell
```shell
#!/bin/dash
```


# 第四部分 高级shell脚本编程主题
## 第23章 使用数据库
### 23.4 在脚本中使用数据库
```shell
$ which mysql

$ cat mtest2
#!/bin/bash
# send a command to the MySQL server
MYSQL=` which mysql`
$MYSQL test -u test e 'select * from employees'

```
## 第24章 使用Web
```shell
curl http://www.google.com
curl -s -o /home/rich/curl-6.18.0.tar.gz http://curl.haxx.se/downlaod/curl-7.18.9.tar.gz # 后台下载文件
```
## 第25章 使用E-mail
## 第26章 编写脚本使用工具
## 第27章 shell脚本编程进阶