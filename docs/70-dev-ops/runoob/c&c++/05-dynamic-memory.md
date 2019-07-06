栈：在函数内部声明的所有变量都将占用栈内存。
堆：这是程序中未使用的内存，在程序运行时可用于动态分配内存。

### new 和 delete 运算符

```c++
new data-type;

double* pvalue  = NULL; // 初始化为 null 的指针
pvalue  = new double;   // 为变量请求内存

// new 与 malloc() 函数相比，其主要的优点是，new 不只是分配了内存，它还创建了对象

delete pvalue;        // 释放 pvalue 所指向的内存

// 数组
char* pvalue  = NULL;   // 初始化为 null 的指针
pvalue  = new char[20]; // 为变量请求内存
delete [] pvalue;        // 删除 pvalue 所指向的数组

```