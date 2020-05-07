

## zipkin
https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/

java -jar zipkin-server-2.12.2-exec.jar --logging.level.zipkin2=INFO

http://127.0.0.1:9411

## pinpoint
[Pinpoint安装部署](https://www.cnblogs.com/yyhh/p/6106472.html)

jdk7 --- Java运行环境
hbase-1.0 --- 数据库，用来存储监控信息
tomcat8.0 --- Web服务器
pinpoint-collector.war --- pp的控制器
pinpoint-web.war --- pp展示页面
pp-collector.init --- 用来快速启动pp-col，不要也可以
pp-web.init --- 用来快速启动pp-web，不要也可以