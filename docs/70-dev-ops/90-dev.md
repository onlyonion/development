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

### git
```sh
git init 
git add 
git commit -m "first commit"
git remote add origin https://github.com/yourspace/yourproject.git
git push -u origin master
```

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

### git - unable to clone Host key verification failed. fatal: Could not read from remote repository
```sh
# 添加SSH公钥
ssh-keygen -t rsa -C "youraccount" # 注意是邮箱或者账号，或者两个都是 youraccount@email.com 
```

### git safe directory
```sh
git config --global --add safe.directory "*"
# gitignore
git config --global core.excludesfile ~/.gitignore_global
```

### mybatis
\,jdbcType\=[A-Z]*