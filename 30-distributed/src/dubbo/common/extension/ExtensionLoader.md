
* getExtensionLoader()
* getAdaptiveExtension()

## 类图
使用了大量的ConcurrentMap容器，用作缓存
```yuml
// {type:class}

[ExtensionLoader]++-[ConcurrentMap<Class<?>, ExtensionLoader<?>>]
[ExtensionLoader]++-[ConcurrentMap<Class<?>, Object> ]

[ExtensionLoader]++-[ExtensionFactory]
[ExtensionLoader]++-[Holder<Map<String, Class<?>>>]

```

## getExtension()入口方法
```mermaid
sequenceDiagram
    %% 从缓存中获取与拓展类对应的 ExtensionLoader，若缓存未命中，则创建
    ExtensionLoader->>ExtensionLoader: 1. 静态方法getExtensionLoader(type)
    ExtensionLoader->>ConcurrentMap: get(type)
    opt loader为null
        ExtensionLoader->>ExtensionLoader:实例化一个ExtensionLoader
        ExtensionLoader->>ConcurrentMap:putIfAbsent
    end
        ConcurrentMap-->>ExtensionLoader:返回缓存的loader
    
    %% 是否获得默认的扩展
    ExtensionLoader->>ExtensionLoader: 2. 实例方法getExtension(name)
    opt name.equals("true")
        ExtensionLoader->>ExtensionLoader: 2.1 getDefaultExtension()
        ExtensionLoader->>ExtensionLoader: 2.2 getExtensionClasses()
        ExtensionLoader->>Holder:cachedClasses.get()
        ExtensionLoader->>ExtensionLoader:loadExtensionClasses()双重加锁检查
        ExtensionLoader->>ExtensionLoader:loadFile() 按照internal、dubbo、services目录加载
    end

    %% 检查缓存，缓存未命中则创建拓展对象
    opt holder.get == null 
        ExtensionLoader->>ExtensionLoader: 3. createExtension(name)双重加锁检查
        ExtensionLoader->>ExtensionLoader: getExtensionClasses().get(name)同2.1，、2.2
        
        opt clazz == null
            ExtensionLoader->>ExtensionLoader: findException(name)
        end

        ExtensionLoader->>ExtensionLoader: 4. injectExtension(instance)注入扩展

        %% 循环遍历方法
        loop instance.getClass().getMethods()
            ExtensionLoader->>ExtensionFactory: objectFactory.getExtension(pt, property)
            alt object != null
                 ExtensionLoader->>Method:method.invoke(instance, object);
            end
        end
    end
```


