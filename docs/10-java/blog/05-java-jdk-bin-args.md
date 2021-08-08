
# JVM
- 区溢算收
- 结加栈调
- 编糖即优
- 模线安优

## GC实时分析
```sh
jstat -gc ${vmid} 1000 10   # 用于查看JVM中堆的垃圾收集情况的统计
jstat -gcutil # 用于查看新生代、老生代及持久代垃圾收集的情况
jstat -gccapacity ${vmid} 1000 10 # 用于查看新生代、老生代及持久代的存储容量情况
jstat -gccause ${vmid} 1000 10  # 用于查看垃圾收集的统计情况（这个和-gcutil选项一样），如果有发生垃圾收集，它还会显示最后一次及当前正在发生垃圾收集的原因。
jstat -gcnew ${vmid} 1000 10 # 用于查看新生代垃圾收集的情况
jstat -gcnewcapacity ${vmid} 1000 10 # 用于查看新生代的存储容量情况
jstat -gcold ${vmid} 1000 10 # 用于查看老生代及持久代发生GC的情况
jstat -gcoldcapacity ${vmid} 1000 10 # 用于查看老生代的容量
jstat -gcmetacapacity ${vmid} 1000 10 # 用于查看持久代的容量

################# JIT #################
jstat -printcompilation ${vmid} 1000 10 # HotSpot编译方法的统计
jstat -compiler ${vmid} # 用于查看HotSpot中即时编译器编译情况的统计

################# 类加载 #################
jstat -class ${vmid}    # 用于查看类加载情况的统计

```

查看JDK垃圾收集器
```sh
java -XX:+PrintCommandLineFlags -version # 查看JDK默认GC
java -XX:+PrintGCDetails -version # 查看GC详情
```

## 内存分析
jmap -histo 每个class的实例数目，内存占用，类全名信息。VM的内部类名字开头会加上前缀'*'


jmap -dump:format=b,file=heap3.dump 1

https://docs.oracle.com/javase/9/docs/api/java/lang/Class.html#getName--
```sh
[Z = boolean
[B = byte
[S = short
[I = int
[J = long
[F = float
[D = double
[C = char
[L = any non-primitives(Object)
```

## 线程分析
jstack
