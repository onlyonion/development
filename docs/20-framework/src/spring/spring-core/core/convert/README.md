org.springframework.core.convert

## pacakge
```
converter
    ConditionalConverter
    ConditionalGenericConverter
    Converter
    ConverterFactory
    ConverterRegistry
    ConvertingComparator
    GenericConverter
support
    AbstractConditionalEnumConverter
    ArrayToArrayConverter
    ArrayToCollectionConverter
    ArrayToObjectConverter
    ArrayToStringConverter
    ByteBufferConverter
    CharacterToNumberFactory
    CollectionToArrayConverter
    CollectionToCollectionConverter
    CollectionToObjectConverter
    CollectionToStringConverter
    ConfigurableConversionService
    ConversionServiceFactory
    ConversionUtils
    ConvertingPropertyEditorAdapter
    DefaultConversionService
    EnumToIntegerConverter
    EnumToStringConverter
    FallbackObjectToStringConverter
    GenericConversionService
    IdToEntityConverter
    IntegerToEnumConverterFactory
    MapToMapConverter
    NumberToCharacterConverter
    NumberToNumberConverterFactory
    ObjectToArrayConverter
    ObjectToCollectionConverter
    ObjectToObjectConverter
    ObjectToOptionalConverter
    ObjectToStringConverter
    PropertiesToStringConverter
    StreamConverter
    StringToArrayConverter
    StringToBooleanConverter
    StringToCharacterConverter
    StringToCharsetConverter
    StringToCollectionConverter
    StringToCurrencyConverter
    StringToEnumConverterFactory
    StringToLocaleConverter
    StringToNumberConverterFactory
    StringToPropertiesConverter
    StringToTimeZoneConverter
    StringToUUIDConverter
    ZonedDateTimeToCalendarConverter
    ZoneIdToTimeZoneConverter
ConversionException
ConversionFailedException
ConversionService
ConverterNotFoundException
Property
TypeDescriptor
```

## PS
默认日期转换 java.util.Date#Date(java.lang.String) 构造方法 
```
19 Nov 2019 12:01:00 GMT  ==> Tue Nov 19 20:01:00 CST 2019
19 Nov 2019 12:01:00      ==> Tue Nov 19 12:01:00 CST 2019
19 Nov 2019               ==> Tue Nov 19 00:00:00 CST 2019

CST可以为如下4个不同的时区的缩写
Central Standard Time (USA) UT-6:00 美国标准时间
Central Standard Time (Australia) UT+9:30 澳大利亚标准时间
China Standard Time UT+8:00 中国标准时间
Cuba Standard Time UT-4:00 古巴标准时间
```