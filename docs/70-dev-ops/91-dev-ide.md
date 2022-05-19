dev ide

## Visual_Paradigm

## powerdesigner
去掉Name与Code联动
tools -> General Options -> Dialog -> Operation Modes: 去掉NameToCodeMirroring前面的√

Table视图同时显示Code和Name
Tools -> Display Preference -> Table -> Advanced -> Columns -> list colums 搜索，选中code，置顶

设置线条线为直线
Tools -> Display Preferences -> General setting的Format菜单下，点Modify，Line Style页下有个Line-->Corners

用例图带箭头
Tools -> Display Preferences -> General，Use Case Association，选中Orientation

## idea
JVM参数
```sh
-Dfile.encoding=UTF-8
```
### 初始
- JDK
- 文件编码 Editor -> File Encodings
- 自动导包 General Setting -> Auto Import

### 常用

插件
- lombok
- mybatisx
- plantuml

plugin不能加载
Appearence & Behavior - System Settings - Updates - User Secure Connection，取消勾选该选项

编码
Setting->File Encodings->Project Encoding选择utf-8，Default encoding for properties files 也选择 UTF-8

乱码
setting-->file encodings--->Global Encoding 和 Project Encoding 都设置为UTF-8
Transparent native-to-ascii conversion 主要用于转换 ascii，一般都要勾选

Field Injection warning
Preferences->Editor->Inspections->Spring->Spring Core->Core->Field Injection warning

不折叠代码
File->Setting->Editor->General->Code Folding  将One-line methods去除即可


Idea启动项目报错:Command line is too long. Shorten command line for className or also for JUnit defaultconfiguration.
在该项目文件夹.idea/workspace.xml中找到
```xml
<component name="PropertiesComponent">
    <property name="dynamic.classpath" value="true" />
</component>
```

### [去除 mybatis.xml 文件黄色警告](https://blog.csdn.net/wsjzzcbq/article/details/89528252)
- Editor，Inspections，SQL 去除 No data sources configuared；去掉 SQL dialect detection 的勾（√）
- Editor，Color Scheme， General，Code，Injected language fragment，去掉右边 Background （背景色）的勾

泛型提示
- Editor，Inspections，Java 5 raw use of parameterized class

方法提示
- Editor - General - Code Completion， Parameter Info 勾选三个

未使用提示
 Editor -> Inspections -> Declaration redundancy -> Unused declaration

序列化ID提示
Preferences–>Editor–>Inspections 勾选Serializable class without 'serialVersionUID'

快捷键冲突
- ide快捷键与sougou输入搜索输入快捷键冲突 ctrl+shif+f
- debug快捷键冲突，有道词典F8快捷键

注释
类注释 Editor -> File and Code Templates -> Files -> Class

文档提示
Editor -> General 勾选 show quick documentation on mouse move

check autowiring problems in a bean class

提示不区分大小写
Editor -> general -> code completion

### Could not autowire. No beans of '' type found. less... (Ctrl+F1) 
Inspection info:Checks autowiring problems in a bean class.

Settings - Editor - Inspections - Spring - Spring Core - Code - Autowiring for Bean Class - disable

### IDEA Error:java: Compilation failed: internal java compiler error
解决办法很简单：File-->Setting...-->Build,Execution,Deployment-->Compiler-->Java Compiler 设置相应Module的target bytecode version的合适版本

### idea mac man
sudo vim /etc/hosts，在localhost后面追加你的电脑名.local即可
终端输入 scutil --set HostName "localhost" 本地回环地址

### idea快捷键
Ctrl+Alt+V，可以引入变量。例如：new String(); 自动导入变量定义
Ctrl+Alt+T，可以把代码包在一个块内，例如：try/catch
Ctrl+Enter，导入包，自动修正
Ctrl+Alt+L，格式化代码

### idea注释模板
```java
/**
 *
 * @author 
 * @since ${YEAR}-${MONTH}-${DAY}
 */
```

### idea lombok 编译不生效
setting -> build,execution,deployment -> compiler -> annotation processors 勾选 enbale annotation processing

### idea模板

### idea gradle unable to resolve dependency 
检查 File-->Settings...-->Build, Execution, Deployment-->Gradle-->取消Offline work的勾选

### 日志颜色
VM options：
-Dspring.output.ansi.enabled=ALWAYS

## vscode

### plugin
markdown all in one
platuml

### 缩放
setting里搜索mouseWheelZoom，打钩

### 查找删除，正则删除
```sh
# 块注释（多行注释）
/\*(.|\r\n|\n)*?\*/
# 删除空行
^\s*(?=\r?$)\n
# 匹配换行
\n
```

### 代码模板
setting -> 用户代码片段

```json
"uml": {
		"scope": "",
		"prefix": "uml",
		"body": [
			"```plantuml",
			"@startuml",
			"$1",
			"@enduml",
			"```"
		],
		"description": "plant uml"
	}
```

### 快捷键
键盘绑定 -> 触发建议（提示代码） ctrl + space  --> alt + /

### markdown
```js
// 针对 [markdown] 语言，配置替代编辑器设置。
"[markdown]":  {
	"editor.wordWrap": "off",
	"editor.quickSuggestions": false
}
```

### uml
```sh
# mac
sudo port install graphviz
brew install graphviz

```

## tool
- ZooViewer
- zktools

### wiki
SmartWiki

MinDoc 是一款针对IT团队开发的简单好用的文档管理系统。

MinDoc 的前身是 SmartWiki 文档系统。SmartWiki 是基于 PHP 框架 laravel 开发的一款文档管理系统。因 PHP 的部署对普通用户来说太复杂，所以改用 Golang 开发。可以方便用户部署和实用。

## charles

proxy -> windows proxy/mac proxy
proxy -> proxy setting, port:8000
查看本地IP地址 help -> local ip addressess
手机配置代理 ip+port
打开调试的app，请求会发送到charles，然后验证是否允许访问
当点击允许后，可以在Proxy -> Access Control Settings里看到可以访问此代理服务器列表


截取 Https 通讯信息

Proxy –> SSL Proxying Setting –> Enable SSL Proxying 点击 Add，编辑Location，输入 Host: * , Port: 443
PC 端证书安装：Help –> SSL Proxying –> Install Charles Root Certificate，选择 Charles 的证书，并信任此证书
移动端证书安装：Help –> SSL Proxying –>Install Charles Root Certificate on a Mobile Device， 安装弹出的对话框要求，手机配置好端口为 8888 的代理之后，在手机浏览器访问 http://chls.pro/ssl 就可以下载证书并安装了
安卓手机安装证书 Help –> SSL Proxying –>Save Charles Root Certificate...，选择要保存的文件目录，点击 Save, 将保存的文件传到手机，点击手机的 设置 -> WLAN -> 高级设置 -> 安装证书，找到文件保存目录，安装完成。
注意：对于 iOS 10.3以上的手机需要设置证书信任：点击 通用 -> 关于本机 -> 证书信任设置 -> 选择 Charles 的证书打开。


小米安装证书
（miui10及以上）点击设置-更多设置-系统安全-加密与凭据-从存储卡安装文件
（miui10一下）点击设置-更多设置-系统安全-从存储卡安装文件

## Navicat
Navicat闲置一段时间卡死 编辑连接，进入高级项，勾选保持连续间隔(秒)：时间设置短一些，比如30秒

## git
```sh
git init 
git add 
git commit -m "first commit"
git remote add origin https://github.com/yourspace/yourproject.git
git push -u origin master
```

只拉取部分目录
```sh
mkdir pro1
cd pro1
git init
git remote add -f origin https://github.com/XXXXX/test.git    #拉取remote的all objects信息
git config core.sparsecheckout true   #开启sparse clone
echo "build" >> .git/info/sparse-checkout   #设置需要pull的目录，*表示所有，!表示匹配相反的
less .git/info/sparse-checkout
git pull origin master  #拉取
```

### git - unable to clone Host key verification failed. fatal: Could not read from remote repository
```sh
# 添加SSH公钥
ssh-keygen -t rsa -C "youraccount" # 注意是邮箱或者账号，或者两个都是 youraccount@email.com 
```

### git safe directory
```sh
git config --global --add safe.directory "*"
```