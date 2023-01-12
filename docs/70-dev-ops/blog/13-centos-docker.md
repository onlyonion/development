
## docker

### remove
systemctl stop docker
rm -rf /etc/docker
rm -rf /run/docker
rm -rf /var/lib/dockershim
rm -rf /var/lib/docker
yum remove -y docker*
yum clear all

yum list installed | grep docker
ps -ef | grep docker
kill -9 pid

### install
cat <<EOF > daemon.json
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "registry-mirrors": [
    "https://registry.docker-cn.com", 
    "http://f1361db2.m.daocloud.io", 
    "https://docker.mirrors.ustc.edu.cn",
    "https://hub-mirror.c.163.com"
  ]
}
EOF
mv daemon.json /etc/docker/

```sh
yum install -y lrzsz 
# 所有节点关闭 SELinux
setenforce 0
sed -i --follow-symlinks 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/sysconfig/selinux
# 关闭swap
swapoff -a
vim /etc/fstab #注释掉swap分区
#/dev/mapper/cl-swap swap swap defaults 0 0

# subscription-manag
vim /etc/yum/pluginconf.d/subscription-manager.conf
[main]
#enabled=1
```


```sh
yum remove -y kubelet-1.22.4 kubectl-1.22.4 kubeadm-1.22.4 docker-ce
yum install -y kubelet-1.22.4 kubectl-1.22.4 kubeadm-1.22.4 docker-ce

systemctl enable kubelet
systemctl start kubelet
systemctl enable docker
systemctl start docker

# kubernetes 官方推荐 docker 等使用 systemd 作为 cgroupdriver，否则 kubelet 启动不了
cat <<EOF > daemon.json
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "registry-mirrors": ["https://ud6340vz.mirror.aliyuncs.com"]
}
EOF
mv daemon.json /etc/docker/

# 重启生效
systemctl daemon-reload
systemctl restart docker
```

## docker-compose


## harbor

```sh
tar xzf harbor-offline-installer-v1.9.3.tgz
vim ./harbor/harbor.yml
# hostname: 192.168.0.115
cd harbor
nohup ./install.sh &

docker pull nginx
docker images
docker tag {imageId} 192.168.0.115/demo/nginx-x1
docker images
docker login -u admin -p Harbor12345 http://192.168.0.115
# 登录过程如果出现https加密报错，在/usr/lib/systemd/system/docker.service文件中的
# ExecStart=/usr/bin/dockerd那里fd://后面添加--insecure-registry 192.168.118.44即可

docker push 192.168.0.115/demo/nginx-01
```
admin、Harbor12345