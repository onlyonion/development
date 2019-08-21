com.alibaba.dubbo.common.compiler.Compiler
## hierachy
```
Compiler (com.alibaba.dubbo.common.compiler)
    AdaptiveCompiler (com.alibaba.dubbo.common.compiler.support)
    AbstractCompiler (com.alibaba.dubbo.common.compiler.support)
        JavassistCompiler (com.alibaba.dubbo.common.compiler.support)
        JdkCompiler (com.alibaba.dubbo.common.compiler.support)
```


## define
```java
@SPI("javassist")
public interface Compiler {
    Class<?> compile(String code, ClassLoader classLoader);
}
```

## invoke
* ExtensionLoader
* AdaptiveCompiler
* AbstractCompiler
* JavassistCompiler


## stack frames
```
doCompile:53, JavassistCompiler (com.alibaba.dubbo.common.compiler.support)
compile:59, AbstractCompiler (com.alibaba.dubbo.common.compiler.support)
compile:46, AdaptiveCompiler (com.alibaba.dubbo.common.compiler.support)
createAdaptiveExtensionClass:734, ExtensionLoader (com.alibaba.dubbo.common.extension)
getAdaptiveExtensionClass:727, ExtensionLoader (com.alibaba.dubbo.common.extension)
createAdaptiveExtension:716, ExtensionLoader (com.alibaba.dubbo.common.extension)
getAdaptiveExtension:451, ExtensionLoader (com.alibaba.dubbo.common.extension)
<clinit>:63, ReferenceConfig (com.alibaba.dubbo.config)
```