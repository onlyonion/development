时间复杂度

[时间复杂度和空间复杂度](http://www.cnblogs.com/xiaxianfei/p/5385081.html)

闭包

惰性计算

异步队列

## JavaScript核心
[JavaScript核心](http://blog.jobbole.com/52069/)

### 对象
一个对象就是一个属性集合，并拥有一个独立的prototype（原型）对象。这个prototype可以是一个对象或者null。

### 原型对象

原型对象的引用__proto__

### 原型链

原型链是一个用来实现继承和共享属性的有限对象链。

遍历整个原型链

如果没有明确为一个对象指定原型，那么它将会使用__proto__的默认值－Object.prototype。Object.prototype对象
自身也有一个__proto__属性，这是原型链的终点并且值为null。

### 构造函数
除了以指定模式创建对象之外，构造函数也做了另一个有用的事情－它自动地为新创建的对象设置一个原型对象。
这个原型对象存储在ConstructorFunction.prototype属性中
 
### 执行上下文堆栈
execution context

全局代码、函数代码和eval代码

一个触发其他上下文的上下文叫作caller。被触发的上下文叫作callee。callee在同一时间可能是一些其他callee的caller
（比如，一个在全局上下文中被调用的函数，之后调用了一些内部函数）。

当一个caller触发（调用）了一个callee，这个caller会暂缓自身的执行，然后把控制权传递给callee。这个callee被push
到栈中，并成为一个运行中（活动的）执行上下文。

### 执行上下文
一个执行上下文可以抽象的表示为一个简单的对象。每一个执行上下文拥有一些属性（可以叫作上下文状态）用来跟踪和它相关的代码的执行过程。

* Execution context
  * variable object {vars, function declarations, arguments...}
  * scope chain {Variable object + all parent scopes}
  * thisValue Context object

### 变量对象
变量对象是与执行上下文相关的数据作用域。它是一个与上下文相关的特殊对象，其中存储了在上下文中定义的变量和函数声明。

### 活动对象
当一个函数被caller所触发（被调用），一个特殊的对象，叫作活动对象（activation object）将会被创建。这个对象中包含形参和
那个特殊的arguments对象（是对形参的一个映射，但是值是通过索引来获取）。活动对象之后会做为函数上下文的变量对象来使用。

### 作用域链
作用域链是一个对象列表，上下文代码中出现的标识符在这个列表中进行查找。

这个规则还是与原型链同样简单以及相似：如果一个变量在函数自身的作用域（在自身的变量/活动对象）中没有找到，那么将会查找
它父函数（外层函数）的变量对象，以此类推。

### 闭包
在ECMAScript中，函数是第一级（first-class）对象。这个术语意味着函数可以做为参数传递给其他函数（在那种情况下，这些参数
叫作「函数类型参数」（funargs，是”functional arguments”的简称））。接收「函数类型参数」的函数叫作高阶函数或者，贴近
数学一些，叫作高阶操作符。同样函数也可以从其他函数中返回。返回其他函数的函数叫作以函数为值（function valued）的函数（
或者叫作拥有函数类值的函数（functions with functional value））。
* 函数类型参数（funargs）
* 函数类型值（functional　values）

> 闭包是一个代码块（在ECMAScript是一个函数）和以静态方式/词法方式进行存储的所有父作用域的一个集合体。所以，通过这些存储的
作用域，函数可以很容易的找到自由变量。

### This

> this是一个与执行上下文相关的特殊对象。因此，它可以叫作上下文对象（也就是用来指明执行上下文是在哪个上下文中被触发的对象）。

> this是执行上下文的一个属性，而不是变量对象的一个属性。

这个特性非常重要，因为与变量相反，this从不会参与到标识符解析过程。换句话说，在代码中当访问this的时候，它的值是直接从执行
上下文中获取的，并不需要任何作用域链查找。this的值只在进入上下文的时候进行一次确定。



