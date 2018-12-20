
## elk
Elasticsearch , Logstash, Kibana 

[elk](https://www.cnblogs.com/aresxin/p/8035137.html "title") 

建立集中式日志收集系统，将所有节点上的日志统一收集，管理，访问。


* LogStash/Beats: 负责数据的收集与处理
* ElasticSearch: 一个开源的分布式搜索引擎，负责数据的存储、检索和分析
* Kibana: 提供了可视化的界面（ Web界面）。负责数据的可视化操作


## cat
传统的开源监控系统代表有：Cacti、Nagios、Zabbix等
先进的开源时间序监控系统代表有：OpenTSDB、Open-falcon、Prometheus等

## 分布式监控平台 CAT
[CAT](https://www.oschina.net/news/78563/cat-depth-analysis "title") 
通常Java客户端在业务上使用容易出问题的地方就是内存，另外一个是CPU。内存往往是内存泄露，占用内存较多导致业务方GC压力增大； CPU开销最终就是看代码的性能。

## prometheus 
分布式业务监控解决方案，采用时间序数据库 

一个开源的系统监控和告警的工具包，其采用pull方式采集时间序列，通过http协议传输

