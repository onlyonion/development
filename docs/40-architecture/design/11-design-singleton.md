# 单例

1. 饿汉式：实现简单，安全可靠
2. 懒汉式：安全，但是性能较低（同步方法）
3. 双重验证：更加安全,但使用时仍需判断----`懒汉式`（同步代码块）
4. 静态内部类：懒惰加载，使用时无需判断，但仍存在反射和反序列化的问题----`懒汉式`
5. 枚举：最理想的单例模式！----`饿汉式`

- 饿汉，静态变量实例化饿汉；枚举饿汉；
- 懒汉，同步方法懒汉；双重检测实例化；静态内部类Holder


## 饿汉式
```java
// 1、为什么使用final:防止子类覆盖父类方法破坏单例
// 2、如果实现了序列化，如何防止反序列化破坏单例：加入readResolve方法，在反序列化时就会采用readResolve返回的对象，而不是反序列化生成的对象
public final class SingleEhan extends Serializable{
	
	//3、构造方法为什么为私有：防止使用者使用构造器创建对象，破坏单例。
	//4、能否防止反射：不能，设置accessable为true通过构造方法反射依然会获得对象
	private SingleEhan() {
		
	}
	//5、这样初始化单例能否保证单例对象创建时的线程安全：可以，静态成员变量，在类初始化的时候创建。由jvm保证线程安全
	private static final SingleEhan singleEhan = new SingleEhan();
	
	// 6、为什么使用方法而不是直接将singleEhan设置为public：为了更好的封装，可以改写成懒惰初始化。也可以支持泛型。方法中可以写一些别的逻辑
	public static SingleEhan getSingleEhan() {
		return singleEhan;
	}

	public Object readResolve(){
		return singleEhan;
	}
}

```
## 懒汉式
```java
public final class SingleLanHan {
	private static SingleLanHan singleLanHan = null;
	
	private SingleLanHan() { }
	
	//此处能否保证线程安全：可以，因为创建对象或者获得对象会加锁。但是，锁的范围较大，就算对象创建好了，以后获得对象也要加锁，性能较低！
	public static synchronized SingleLanHan getSingleLanhan() {
		if(singleLanHan!=null) {
			return singleLanHan;
		}
		singleLanHan = new SingleLanHan();
		return singleLanHan;
	}
}
```

## 双重验证-懒汉式
```java
public final class SingleDcl {
	// 1、此处为何要用volatile：防止singleDcl 在实例化的时候指令重排，导致别的线程获得实例的时候虽不为空，但还未赋值。
	private static volatile SingleDcl singleDcl = null;
	
	private SingleDcl() {
		
	}
	// 2、对比单纯的懒汉式，这里有什么优点：初始化成功后，都不用上锁
	public static SingleDcl getSingleDcl() {
		if(singleDcl!=null) {
			return singleDcl;
		}
		synchronized (SingleDcl.class) {
			// 3、此处为什么还要为空判断：因为首次初始化时都会进入到同步块。
			if(singleDcl!=null) {
				return singleDcl;
			}
			singleDcl = new SingleDcl();
		}
		return singleDcl;
	}
	
}
```

## 静态内部类-懒汉式
```java
public final class SingleInnerClass {
	
	private SingleInnerClass() {
		
	}
	
	// 1、属于懒汉式还是饿汉式：懒汉式创建，类加载是懒惰的，在调用时静态变量才会初始化。
	private static class LazyHolder {
		static final SingleInnerClass singleInnerClass = new SingleInnerClass();
	}
	
	// 2、在创建单例时是否有并发问题：没有，初始化属于静态变量初始化，由jvm保证线程。
	public static SingleInnerClass getSingleInnerClass() {
		return LazyHolder.singleInnerClass;
	}
	
}
```

## 枚举单例-饿汉式
```java
// 1、枚举单例如何限制实例个数：创建时有几个就有几个
// 2、枚举单例属于懒汉式还是饿汉式：饿汉式，相当于静态成员变量
// 3、枚举单例能否防止反射：不能。
// 4、枚举单例能否防止反序列化：可以！枚举父类实现了序列化接口，但是它在反序列化的时作了处理。可以防止反序列化创建新对象
// 5、枚举单例在单例创建时希望加入初始化逻辑怎么做：可以在构造方法中写逻辑
// 6、枚举单例在创建时是否有并发问题：没有，在类加载时创建，由jvm保证。不会有并发问题
public enum SingleEnum {
	INSTANCE;
	
	public void doSomething() {
		
	}
}
```
