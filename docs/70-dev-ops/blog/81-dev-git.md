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
### merge + rebase
* merge 提交commit合并修改
* rebase 修改提交历史记录 


### git只拉取部分目录
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