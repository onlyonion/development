
```sh
# linux
yum install -y traceroute
traceroute

# curl http code
curl -I -m 10 -o /dev/null -s -w %{http_code} www.baidu.com
curl -I -m 10 -o /dev/null -s -w %{http_code} m.dhvcmpd.cn

# windows
tracert
```