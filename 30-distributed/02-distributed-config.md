分布式配置


* 不同环境的不同配置
* 不用重新打包发布
* 自动更新配置文件信息


| 注册中心 				| 配置存储 		| 时效性 				|
| ------ 				| ------ 		| ------ 				|
| disconf 				| zookpeer 		| 实时推送 				| 
| zookpeer 				| Slytherin 	| 实时推送 				|
| diamond 				| mysql			| 每隔15s拉一次全量数据	|
| Spring Cloud Config	| git 			| 人工批量刷新 			|