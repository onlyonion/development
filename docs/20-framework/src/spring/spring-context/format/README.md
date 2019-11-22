org.springframework.format

## package
```
annotation
    DateTimeFormat      日期格式化注解
    NumberFormat
datetime
    joda                 面向 Java 应用程序的日期/时间库的替代选择
    standard
    DateFormatter
    DateFormatterRegistrar
    DateTimeFormatAnnotationFormatterFactory
number
support

AnnotationFormatterFactory
Formatter
FormatterRegistrar
FormatterRegistry
Parser
Printer
```

```plantuml
@startuml

interface Parser<T>
interface Printer<T>
interface Formatter<T>
Parser ^-- Formatter
Printer ^-- Formatter

interface FormatterRegistry 
interface FormatterRegistrar
FormatterRegistrar ..> FormatterRegistry
FormatterRegistry *-- Formatter

@enduml
```