

## git
```sh
sudo yum install -y git
```

## node
```sh
yum module list nodejs
sudo yum module install nodejs
```

## python
```sh
# 将Python2建立软连接Python
ln -s /usr/bin/python2 /usr/bin/python
```

## sz
```sh
yum install -y lrzsz
```

## java -jar
```sh
java -jar demo.jar
java -jar demo.jar &
nohup java -jar demo.jar &
nohup java -jar demo.jar >/dev/null & # 输出内容不打印屏幕，输出到文件中
netstat -nlp | grep :9181 # 查看某端口占用的线程的pid
```



