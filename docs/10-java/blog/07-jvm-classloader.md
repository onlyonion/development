# JVM类加载机制

## 类加载过程

加载，链接（验证，准备，解析），初始化

### 加载

* Class文件获取
* ZIP包中读取（比如从jar包和war包中读取）
* 运行时计算生成（动态代理）
* 由其它文件生成

### 验证

### 准备

为类变量分配内存并设置类变量的初始值阶段，即在方法区中分配这些变量所使用的内存空间

### 解析

虚拟机将常量池中的符号引用替换为直接引用的过程

### 初始化

开始真正执行类中定义的Java程序代码
执行类构造器<client>方法的过程

## 类加载器

### 类加载器分类

1. 启动类加载器(Bootstrap ClassLoader)：负责加载 JAVA_HOME\lib 目录中的，或通过-Xbootclasspath参数指定路径中的，且被虚拟机认可（按文件名识别，如rt.jar）的类。
用C++语言写的，主要负载加载JAVA_HOME/lib下的类库，启动类加载器无法被应用程序直接使用

2. 扩展类加载器(Extension ClassLoader)：负责加载 JAVA_HOME\lib\ext 目录中的，或通过java.ext.dirs系统变量指定路径中的类库。
用JAVA编写，且它的父类加载器是Bootstrap，是由sun.misc.Launcher$ExtClassLoader实现的，主要加载JAVA_HOME/lib/ext目录中的类库

3. 应用程序类加载器(Application ClassLoader)：负责加载用户路径（classpath）上的类库。
sun.misc.Launcher$AppClassLoader


### 双亲委派模型

描述一：
如果一个类加载器收到了一个类加载请求，它不会自己去尝试加载这个类，而是把这个请求转交给父类加载器去完成。每一个层次的类加载器都是如此。
因此所有的类加载请求都应该传递到最顶层的启动类加载器中，只有到父类加载器反馈自己无法完成这个加载请求（在它的搜索范围没有找到这个类）时，
子类加载器才会尝试自己去加载。委派的好处就是避免有些类被重复加载

先检查是否已经被加载过，若没有加载则调用父类加载器的loadClass方法，若父类加载器不存在，则使用启动类加载器。如果父类加载器加载失败，则抛出异常之后看，再调用自己的findClass方法进行加载。

描述二：
当一个类加载器收到类加载任务，会先交给其父类加载器去完成，因此最终加载任务都会传递到顶层的启动类加载器，只有当父类加载器无法完成加载任务时，才会尝试执行加载任务。
保证了使用不同的类加载器最终得到的都是同样一个Object对象。


## ClassNotFoundException
无法找到指定的类异常。
当应用程序试图使用以下方法通过字符串名加载类时，抛出该异常：
* Class 类中的 forName 方法。
* ClassLoader 类中的 findSystemClass 方法。
* ClassLoader 类中的 loadClass 方法。
但是没有找到具有指定名称的类的定义。

### 常见的原因
1. 所需要的支持类库放错了地方，并没有放在类路径(CLASSPATH环境变量)里面。
2. 使用了重复的类库，且版本不一致。导致低版本的被优先使用。
3. 类名错了，一般是使用Class.forName的时候，手工指定了类名的情况。
4. 没有导入纯JAVA驱动包。


## -Xbootclasspath
```sh
# 扩展bootStrap 级别class
-Xbootclasspath:<path>
-Xbootclasspath/p:<path>
-Xbootclasspath/a:<path>

# unix使用:号,windows使用;号
# java -Xbootclasspath/a:/usrhome/thirdlib.jar: -jar yourJarExe.jar

# extend class
{Java_home}\jre\lib\ext

# User class
```

