com.alibaba.com.caucho.hessian.io.Hessian2Input

## methods

```java
    private Object readObjectInstance(Class cl, ObjectDefinition def)
            throws IOException {
        String type = def.getType();
        String[] fieldNames = def.getFieldNames();

        if (cl != null) {
            Deserializer reader;
            reader = findSerializerFactory().getObjectDeserializer(type, cl);

            return reader.readObject(this, fieldNames);
        } else {
            return findSerializerFactory().readObject(this, type, fieldNames);
        }
    }
```


## inner class
```java
    final static class ObjectDefinition {
        private final String _type;
        private final String[] _fields; // 子类、父类字段都在，父类字段在后

        ObjectDefinition(String type, String[] fields) {
            _type = type;
            _fields = fields;
        }

        String getType() {
            return _type;
        }

        String[] getFieldNames() {
            return _fields;
        }
    }
```