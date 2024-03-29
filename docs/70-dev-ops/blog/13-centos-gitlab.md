
## gitlab
```sh
cat /opt/gitlab/embedded/service/gitlab-rails/VERSION # 查看版本

gitlab-ctl start #启动全部服务
gitlab-ctl restart #重启全部服务
gitlab-ctl stop #停止全部服务
gitlab-ctl restart nginx #重启单个服务
gitlab-ctl status #查看全部组件的状态
gitlab-ctl show-config #验证配置文件
gitlab-ctl uninstall #删除gitlab(保留数据）
gitlab-ctl cleanse #删除所有数据，重新开始
gitlab-ctl tail <svc_name>  #查看服务的日志
gitlab-rails console production #进入控制台 ，可以修改root 的密码
```
### gitlab install

[清华大学开源软件镜像站](https://mirror.tuna.tsinghua.edu.cn/help/gitlab-ce/)
```sh
# 新建 /etc/yum.repos.d/gitlab-ce.repo，内容为
[gitlab-ce]
name=Gitlab CE Repository
baseurl=https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/yum/el$releasever/
gpgcheck=0
enabled=1

# 再执行
sudo yum makecache
sudo yum install gitlab-ce

# run
sudo gitlab-ctl reconfigure
```
[CentOS GitLab搭建](https://segmentfault.com/a/1190000021753705)


## gitlab runner
1. 安装runner
2. 注册runner
3. 运行runner

```sh
# 手动安装
curl -LJO https://gitlab-runner-downloads.s3.amazonaws.com/latest/rpm/gitlab-runner_amd64.rpm
rpm -i gitlab-runner_amd64.rpm
# 注册
gitlab-runner register
```

### 清华大学源
[gitlab](https://mirror.tuna.tsinghua.edu.cn/help/gitlab-runner/)
```sh
# 新建 /etc/yum.repos.d/gitlab-runner.repo，内容为

[gitlab-runner]
name=gitlab-runner
baseurl=https://mirrors.tuna.tsinghua.edu.cn/gitlab-runner/yum/el$releasever-$basearch/
repo_gpgcheck=0
gpgcheck=0
enabled=1
gpgkey=https://packages.gitlab.com/gpg.key

# 再执行
sudo yum makecache
sudo yum install gitlab-runner

# 命令
gitlab-runner register
gitlab-runner status
gitlab-runner start
gitlab-runner restart
```


### gitlab pages
gitlab内置的nginx
1、Pages部署目录：/var/opt/gitlab/gitlab-rails/shared/pages
2、内置Nginx目录：/var/opt/gitlab/nginx
```sh
# gitlab-ctl restart
ok: run: alertmanager: (pid 14835) 0s
ok: run: gitaly: (pid 14852) 1s
ok: run: gitlab-exporter: (pid 14876) 0s
ok: run: gitlab-workhorse: (pid 14895) 0s
ok: run: grafana: (pid 14907) 0s
ok: run: logrotate: (pid 14982) 0s
ok: run: nginx: (pid 15009) 1s
ok: run: node-exporter: (pid 15014) 0s
ok: run: postgres-exporter: (pid 15021) 1s
ok: run: postgresql: (pid 15031) 0s
ok: run: prometheus: (pid 15041) 0s
ok: run: redis: (pid 15058) 1s
ok: run: redis-exporter: (pid 15064) 0s
ok: run: sidekiq: (pid 15075) 0s
ok: run: unicorn: (pid 15087) 0s
# gitlab-ctl restart nginx
# http://IP:端口/gitlab账号/工程名/public/
```
