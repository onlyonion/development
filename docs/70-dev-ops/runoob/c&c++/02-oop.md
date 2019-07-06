
## class
类是 C++ 的核心特性，通常被称为用户定义的类型。
类用于指定对象的形式，它包含了数据表示法和用于处理数据的方法。类中的数据和方法称为类的成员。

```c++
// C++ 类定义
class Box
{
   public:
      double length;   // 盒子的长度
      double breadth;  // 盒子的宽度
      double height;   // 盒子的高度
};

// 定义 C++ 对象
Box Box1;          // 声明 Box1，类型为 Box
Box Box2;          // 声明 Box2，类型为 Box

// 私有的成员和受保护的成员不能使用直接成员访问运算符 (.) 来直接访问
```

## C++ 类成员函数
```c++
// 成员函数可以定义在类定义内部，或者单独使用范围解析运算符 :: 来定义。在类定义中定义的成员函数把函数声明为内联的，即便没有使用 inline 标识符
class Box
{
   public:
      double length;      // 长度
      double breadth;     // 宽度
      double height;      // 高度
   
      double getVolume(void)
      {
         return length * breadth * height;
      }
};

// 也可以在类的外部使用范围解析运算符 :: 定义该函数
// 在 :: 运算符之前必须使用类名
double Box::getVolume(void)
{
    return length * breadth * height;
}
```

## C++ 类访问修饰符
关键字 public、private、protected 称为访问修饰符
成员和类的默认访问修饰符是 private


## 类的析构函数

类的析构函数是类的一种特殊的成员函数，它会在每次删除所创建的对象时执行。

析构函数的名称与类的名称是完全相同的，只是在前面加了个波浪号（~）作为前缀，它不会返回任何值，也不能带有任何参数。

析构函数有助于在跳出程序（比如关闭文件、释放内存等）前释放资源。
