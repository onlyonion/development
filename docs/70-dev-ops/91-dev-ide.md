dev ide

## powerdesigner
去掉Name与Code联动
tools -> General Options -> Dialog -> Operation Modes: 去掉NameToCodeMirroring前面的√

Table视图同时显示Code和Name
Tools -> Display Preference -> Table -> Advanced -> Columns -> list colums 搜索，选中code，置顶

设置线条线为直线
Tools -> Display Preferences -> General setting的Format菜单下，点Modify，Line Style页下有个Line-->Corners

## idea
编码
Setting->File Encodings->Project Encoding选择utf-8，Default encoding for properties files 也选择 UTF-8

不折叠代码
File->Setting->Editor->General->Code Folding  将One-line methods去除即可

[去除 mybatis.xml 文件黄色警告](https://blog.csdn.net/wsjzzcbq/article/details/89528252)
- Editor，Inspections，SQL 去除 No data sources configuared；去掉 SQL dialect detection 的勾（√）
- Editor，Color Scheme， General，Code，Injected language fragment，去掉右边 Background （背景色）的勾

泛型提示
- Editor，Inspections，Java 5 raw use of parameterized class

方法提示
- Editor - General - Code Completion， Parameter Info 勾选三个

快捷鍵
- 刪除行

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

Idea启动项目报错:Command line is too long. Shorten command line for className or also for JUnit defaultconfiguration.
在该项目文件夹.idea/workspace.xml中找到
```xml
<component name="PropertiesComponent">
    <property name="dynamic.classpath" value="true" />
</component>
```

plugin不能加载
Appearence & Behavior - System Settings - Updates - User Secure Connection，取消勾选该选项

乱码
setting-->file encodings--->Global Encoding 和 Project Encoding 都设置为UTF-8
Transparent native-to-ascii conversion 主要用于转换 ascii，一般都要勾选

Field Injection warning
Preferences->Editor->Inspections->Spring->Spring Core->Core->Field Injection warning

## vscode
代码模板
setting -> 用户代码片段
触发建议（提示代码） ctrl + space  --> alt + /

```
// 针对 [markdown] 语言，配置替代编辑器设置。
"[markdown]":  {
	"editor.wordWrap": "off",
	"editor.quickSuggestions": false
}
````



## tool
- ZooViewer
- zktools

## docs

### gitbook
```sh
npm install -g gitbook-cli
gitbook init
gitbook serve
```
配置信息 book.json

### docsify
```sh
npm i docsify-cli -g
docsify init ./docs
docsify serve
```
_sidebar.md
```sh
index.html
loadSidebar: true
```

### wiki
SmartWiki

MinDoc 是一款针对IT团队开发的简单好用的文档管理系统。

MinDoc 的前身是 SmartWiki 文档系统。SmartWiki 是基于 PHP 框架 laravel 开发的一款文档管理系统。因 PHP 的部署对普通用户来说太复杂，所以改用 Golang 开发。可以方便用户部署和实用。

## charles

proxy -> windows proxy/mac proxy
proxy -> proxy setting, port:8000
查看本地IP地址help -> local ip addressess
手机配置代理 ip+port
打开调试的app，请求会发送到charles，然后验证是否允许访问
当点击允许后，可以在Proxy -> Access Control Settings里看到可以访问此代理服务器列表