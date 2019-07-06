org.springframework.asm

用来操作字节码，动态生成类或者增强既有类的功能
## pacakge
```
AnnotationVisitor 抽象类，定义在解析注解时会触发的事件，如解析到一个基本值类型的注解、enum值类型的注解、Array值类型的注解、注解值类型的注解等。
AnnotationWriter 继承了 AnnotationVisitor 类，用于拼接注解相关字节码。
Attribute 字节码中属性的类抽象
ByteVector 字节码二进制存储的容器
ClassReader 字节码的读取与分析引擎。它采用类似SAX的事件读取机制，每当有事件发生时，调用注册的ClassVisitor、AnnotationVisitor、FieldVisitor、MethodVisitor做相应的处理。
ClassVisitor 定义在读取Class字节码时会触发的事件，如类头解析完成、注解解析、字段解析、方法解析等。
ClassWriter 它实现了ClassVisitor接口，用于拼接字节码。
Context 定义了一些字节码及其属性。
CurrentFrame 继承自 Frame 类
Edge
FieldVisitor 它实现了FieldVisitor接口，用于拼接字段相关字节码。
FieldWriter 
Frame
Handle
Handler
Item
Label
MethodVisitor
MethodWriter 它实现了MethodVisitor接口，用于拼接方法相关字节码。
Opcodes
SpringAsmInfo
Type
TypePath
TypeReference
```