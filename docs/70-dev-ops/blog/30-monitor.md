# monitor

监控，日志分析，故障恢复
zabbix, nagios, openfalcon

## metrics
- CPU
- 内存
- IOPS （Input/Output Operations Per Second）每秒进行读写操作的次数
- 网络 流入流量、流出流量

## zabbix
一个基于WEB界面的提供分布式系统监视以及网络监视功能的企业级的开源解决方案
- CPU负荷
- 内存使用
- 磁盘使用
- 网络状况
- 端口监视
- 日志监视

[运维监控工具](https://baijiahao.baidu.com/s?id=1593885632264192128&wfr=spider&for=pc&isFailFlag=1)


## trace
[Zipkin，Pinpoint，SkyWalking三种服务链路监控组件分析](https://blog.csdn.net/geeky/article/details/102095706)

- zipkin
- pinpint Java字节码增强 agent, collector, hbase, webui
- skywalking Java字节码增强 


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