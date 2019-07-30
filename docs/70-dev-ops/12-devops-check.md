troubleshoot 故障排查

* cpu消耗
* 内存消耗
* 文件IO消耗
* 网络IO消耗
* 应用慢

## cpu占用过高
结合linux和jdk命令一起分析
1. top命令找出cpu占比最高的
2. ps -ef 或者 jps进一步定位，得志是一个怎么样的后台程序
3. 定位到具体的**线程或代码**; `ps -mp 进程 -o THREAD,tid,time` 或 `ps -mp pid -o THREAD,tid,time | sort -rn `
4. 将需要的线程ID转换为16进制格式 `printf "%x\n" tid` 十进制转换成十六进制
5. jstack 进程id | grep tid（16进制线程ID小写英文） -A60 （打印前60行）

[Java线上应用故障排查之一：高CPU占用【转】](http://www.linuxhot.com/java-cpu-used-high.html)

## book
* [《分布式Java应用 基础与实践》林昊 电子工业出版社](/docs/99-book/notes/30-distributed/分布式Java应用.md)
* [《逆流而上 阿里巴巴技术成长之路》阿里巴巴集团成长集编委会](/docs/99-book/notes/40-architecture/逆流而上.md)