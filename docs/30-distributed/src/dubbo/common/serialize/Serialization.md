com.alibaba.dubbo.common.serialize.Serialization

## hierarchy
```
Serialization (com.alibaba.dubbo.common.serialize)
    NativeJavaSerialization (com.alibaba.dubbo.common.serialize.support.nativejava)
    JavaSerialization (com.alibaba.dubbo.common.serialize.support.java)
    JsonSerialization (com.alibaba.dubbo.common.serialize.support.json)
    DubboSerialization (com.alibaba.dubbo.common.serialize.support.dubbo)
    CompactedJavaSerialization (com.alibaba.dubbo.common.serialize.support.java)
    Hessian2Serialization (com.alibaba.dubbo.common.serialize.support.hessian)
    FastJsonSerialization (com.alibaba.dubbo.common.serialize.support.json)
```

## define
```java
@SPI("hessian2")
public interface Serialization {

    byte getContentTypeId();

    String getContentType();

    @Adaptive
    ObjectOutput serialize(URL url, OutputStream output) throws IOException;

    @Adaptive
    ObjectInput deserialize(URL url, InputStream input) throws IOException;

}
```
