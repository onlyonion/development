## 命名空间
### define
命名空间定义了一个范围
```c++
namespace namespace_name {
   // 代码声明
}

// 调用带有命名空间的函数或变量，需要在函数或变量前面加上命名空间的名称
name::code;  // code 可以是变量或函数
```

### using 
```c++
using namespace
```

### nested
```c++
namespace namespace_name1 {
   // 代码声明
   namespace namespace_name2 {
      // 代码声明
   }
}
```

* 实例
```c++
#include <iostream>
using namespace std;
 
// 第一个命名空间
namespace first_space{
   void func(){
      cout << "Inside first_space" << endl;
   }
   // 第二个命名空间
   namespace second_space{
      void func(){
         cout << "Inside second_space" << endl;
      }
   }
}
using namespace first_space::second_space;
int main ()
{
 
   // 调用第二个命名空间中的函数
   func();
   
   return 0;
}
```

## 模板
模板是泛型编程的基础，泛型编程即以一种独立于任何特定类型的方式编写代码。