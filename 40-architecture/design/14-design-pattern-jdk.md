* [JDK中设计模式](https://blog.csdn.net/gtuu0123/article/details/6114197)
* [细数JDK里的设计模式](http://blog.jobbole.com/62314/)

### 简单工厂

Integer.valueOf

Class.forName

### 工厂方法

Collection.iterator

### 抽象工厂

java.sql

### 单例

* java.lang.Runtime#getRuntime()
* java.text.NumberFormat
* java.awt.Toolkit#getDefaultToolkit()
* java.awt.GraphicsEnvironment#getLocalGraphicsEnvironment()
* java.awt.Desktop#getDesktop()

```java
public class Runtime {
    private static Runtime currentRuntime = new Runtime(); // 饿汉式
    private Runtime() {}
    public native void gc();
}
```

### 建造者

DocumentBuilder(org.w3c.dom)

### 原型

Object.clone；Cloneable


### 适配器
java.io.InputStreamReader(InputStream)
java.io.OutputStreamWriter(OutputStream)

### 装饰器

java.io包

java.util.Collections#synchronizedList(List)

### 代理
作用：
（1）透明调用被代理对象，无须知道复杂实现细节
（2）增加被代理类的功能
JDK中体现：动态代理；RMI

### 外观模式
作用：
（1）封装一组交互类，一致地对外提供接口
（2）封装子系统，简化子系统调用
JDK中体现：java.util.logging包

### 桥接
作用：将抽象部分与其实现部分分离，使它们都可以独立地变化

java.util.logging中的Handler和Formatter

### 组合
一致地对待组合对象和独立对象

org.w3c.dom

javax.swing.JComponent#add(Component)

### 享元
作用：共享对象，节省内存
JDK中体现：
（1）Integer.valueOf(int i)；Character.valueOf(char c)
（2）String常量池

### 策略
作用：提供不同的算法
JDK中的体现：ThreadPoolExecutor中的四种拒绝策略

### 模板
作用：定义算法的结构，子类只实现不同的部分
JDK中体现：ThreadPoolExecutor.Worker

### 观察者
作用：通知对象状态改变
JDK中体现：
（1）java.util.Observer,Observable
（2）Swing中的Listener

### 迭代器

### 责任链
作用：请求会被链上的对象处理，但是客户端不知道请求会被哪些对象处理
JDK中体现：
（1）java.util.logging.Logger会将log委托给parent logger
（2）ClassLoader的委托模型

### Command（命令）
作用：
（1）封装操作，使接口一致
（2）将调用者和接收者在空间和时间上解耦合
JDK中体现：Runnable；Callable；ThreadPoolExecutor

### 备忘录
作用：保持对象状态，需要时可恢复

### 状态模式
作用：将主对象和其状态分离，状态对象负责主对象的状态转换，使主对象类功能减轻

ThreadState

### 访问者
作用：异构的类间添加聚合操作；搜集聚合数据


### 中介者 Mediator（协调者）
作用：用于协调多个类的操作
JDK中体现：Swing的ButtonGroup

### 解释器
作用：用一组类代表某一规则
JDK中体现：java.util.regex.Pattern