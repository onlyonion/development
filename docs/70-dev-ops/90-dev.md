## Git 高级使用
language:javascript location:china
in:fullname
### 搜项目
Awesome + 关键字
star:>=50
forks:>=50
比如输入 stars:>=500 language:javascript，得到的结果 就是收藏大于和等于 500 的 javascript 项目
```
行号
#L 
T
```


## front dev ide
* webstorm
* aptana studio
* dreamweaver
* sublime text
* atorm
* textmate
* vim
* emacs
* nodepad++
* ultraedit
* uedit
* editplus
* VSCode 

## docs

### gitbook
```sh
npm install -g gitbook-cli
# init
gitbook init
# serve
gitbook serve
# build 生成一个 _book 文件夹
gitbook build
```

配置信息 book.json

导航配置 SUMMARY.md

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

### jekyll
```sh
gem install jekyll bundler
jekyll new my-awesome-site
cd my-awesome-site
bundle install
bundle exec jekyll serve
# => 打开浏览器 http://localhost:4000
```