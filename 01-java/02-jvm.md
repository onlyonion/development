## Java虚拟机架构

JVM = 类加载器 classloader + 执行引擎 executionengine + 运行时数据区域 runtime data area

*	ClassLoader 把硬盘上的class文件加载到JVM中的运行时数据区域，但是它不负责这个类文件能否执行
*	执行引擎 执行字节码，或者执行本地方法
*	Runtime DataArea JVM在运行期间，在运行时数据区对JVM内存空间的划分和分配
	-	PC计数器
	-	JVM栈
	-	本地方法栈
	-	方法区
	-	运行时常量池
	-	Java堆
	

