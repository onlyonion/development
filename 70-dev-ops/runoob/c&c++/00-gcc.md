
[runoob-c++](http://www.runoob.com/cplusplus/cpp-intro.html)


### concerpt
* GNU gnu's not linux
* GNU的核心精神是自由与分享
* GNU General Public License GNU通用公共许可协议
* MinGW Minimalist GNU for Windows


#### .hpp .cpp
hpp的优点不少，但是编写中有以下几点要注意： 
1、是Header Plus Plus的简写。（.h和.hpp就如同.c和.cpp似的） 
2、与.h类似，.hpp是C++程序头文件格式。 
3、是VCL专用的头文件,已预编译。 
4、是一般模板类的头文件。 
5、一般来说，.h里面只有声明，没有实现，而.hpp里声明实现都有，后者可以减少.cpp的数量。 
6、.h里面可以有using namespace std，而.hpp里则无。 
7、不可包含全局对象和全局函数。

所有源文件都是被编译器分别划分单元来分别编译，在编译的过程中，头文件被嵌入到实现文件里面一起作为一个编译单元被编译（实现文件filename.cpp里的#include "filename.h"这一行被替换成filename.h里面的所有内容（实际上会把预处理指令去掉，这才是预处理最本质的作用））。