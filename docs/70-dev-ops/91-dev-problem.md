dev tools

## design

### powerdesigner
去掉Name与Code联动
tools -> General Options -> Dialog -> Operation Modes: 去掉NameToCodeMirroring前面的√

Table视图同时显示Code和Name
Tools -> Display Preference -> Advanced -> column -> list colums 搜索，选中code，置顶

设置线条线为直线
Tools -> Display Preferences -> General setting的Format菜单下，点Modify，Line Style页下有个Line-->Corners

## idea
### 编码
Setting->File Encodings->Project Encoding选择utf-8，Default encoding for properties files 也选择 UTF-8

### vscode
代码模板
setting -> 用户代码片段
触发建议（提示代码） ctrl + space  --> alt + /


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