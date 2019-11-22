org.springframework.core.convert.ConversionService

## hierarchy
```
ConversionService (org.springframework.core.convert)
    ConfigurableConversionService (org.springframework.core.convert.support)
        GenericConversionService (org.springframework.core.convert.support)
            FormattingConversionService (org.springframework.format.support)
            DefaultConversionService (org.springframework.core.convert.support)
    RelaxedConversionService (org.springframework.boot.bind)
```

## define
```java

public interface ConversionService {
	boolean canConvert(Class<?> sourceType, Class<?> targetType);
	boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType);
	<T> T convert(Object source, Class<T> targetType);
	Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType);
}
```