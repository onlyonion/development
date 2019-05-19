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


#### 什么是指针？
指针是一个变量，其值为另一个变量的地址，即，内存位置的直接地址。

```c
type *varName;
// * &
int  var = 20;   /* 实际变量的声明 */
int  *ip;        /* 指针变量的声明 */
ip = &var;       /* 在指针变量中存储 var 的地址 */
```

#### 函数指针
函数指针是指向函数的指针变量。
通常我们说的指针变量是指向一个整型、字符型或数组等变量，而函数指针是指向函数。
函数指针可以像一般函数一样，用于调用函数、传递参数。
函数指针变量的声明：
```c
typedef int (*fun_ptr)(int,int); // 声明一个指向同样参数、返回值的函数指针类型

int max(int x, int y) { return x > y ? x : y; }
int (* p)(int, int) = & max; // &可以省略
```

#### 回调函数

函数指针作为某个函数的参数
函数指针变量可以作为某个函数的参数来使用的，回调函数就是一个通过函数指针调用的函数。

#### 结构体
 
C 数组允许定义可存储**相同类型**数据项的变量，结构是 C 编程中另一种用户自定义的可用的数据类型，它允许您存储不同类型的数据项。

```c
struct tag { 
    member-list
    member-list 
    member-list  
    ...
} variable-list ;


/* 函数声明 */
// 结构作为函数参数
void printBook( struct Books book );

// 指向结构的指针
struct Books *struct_pointer;

// 指向该结构的指针访问结构的成员，您必须使用 -> 运算符，如下所示：
struct_pointer->title;
```

#### 位域
有些信息在存储时，并不需要占用一个完整的字节，而只需占几个或一个二进制位

```c
类型说明符 位域名: 位域长度 
struct bs{
    int a:8;
    int b:2;
    int c:6;
}data;
```

#### C 共用体
共用体是一种特殊的数据类型，允许您在相同的内存位置存储不同的数据类型。