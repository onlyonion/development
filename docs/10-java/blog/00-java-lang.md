Java创建对象的方式
- new
- 反射 Class.newInstance Constructor.newInstance
- clone
- 序列化

a=a+b与a+=b的区别
- 性能 a=a+b两次寻址，a+=b一次寻址
- 可读性 a=a+b更好
- 数据类型 a+=b自动转型