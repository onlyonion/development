java.lang.LinkageError

## hierarchy
```
Throwable (java.lang)
    Error (java.lang)
        LinkageError (java.lang)
            BootstrapMethodError (java.lang)
            ClassCircularityError (java.lang)
            ClassFormatError (java.lang)
            ExceptionInInitializerError (java.lang)
            IncompatibleClassChangeError (java.lang)    
                AbstractMethodError (java.lang)
                IllegalAccessError (java.lang)                权限验证失败
                InstantiationError (java.lang)
                NoSuchFieldError (java.lang)
                NoSuchMethodError (java.lang)
            NoClassDefFoundError (java.lang)
            UnsatisfiedLinkError (java.lang)
            VerifyError (java.lang)                     验证失败
```

类加载过程：加载、连接、初始化
- 验证
  - 文件格式验证
  - 元数据验证
  - 字节码验证
  - 符号引用验证 IncompatibleClassChangeError
- 准备
- 解析
  - 类或接口的解析 IllegalAccessError
  - 字段解析 NoSuchFieldError，IllegalAccessError
  - 类方法解析
  - 接口方法解析 NoSuchMethodError