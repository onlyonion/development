### C++ 基本的输入输出

C++ 的 I/O 发生在流中，流是字节序列。
如果字节流是从设备（如键盘、磁盘驱动器、网络连接等）流向内存，这叫做输入操作。
如果字节流是从内存流向设备（如显示屏、打印机、磁盘驱动器、网络连接等），这叫做输出操作。

流插入运算符 << 在一个语句中可以多次使用，endl 用于在行末添加一个换行符

### C++ 数据结构
C/C++ 数组允许定义可存储相同类型数据项的变量，但是结构是 C++ 中另一种用户自定义的可用的数据类型，它允许您存储不同类型的数据项。

```c++
// 结构定义
struct type_name {
    member_type1 member_name1;
    member_type2 member_name2;
    member_type3 member_name3;
} object_names;
// 访问结构成员 为了访问结构的成员，我们使用成员访问运算符（.）
```

```c++
// 指向结构的指针
struct Books *struct_pointer;
// 查找结构变量的地址，把 & 运算符放在结构名称的前面
struct_pointer = &Book1;
// 使用指向该结构的指针访问结构的成员，您必须使用 -> 运算符
struct_pointer->title;
```

```c++
// typedef
typedef struct Books
{
   char  title[50];
   char  author[50];
   char  subject[100];
   int   book_id;
} Books;
```