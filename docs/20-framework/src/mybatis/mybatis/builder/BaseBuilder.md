org.apache.ibatis.builder.BaseBuilder

## hierarchy
```
BaseBuilder (org.apache.ibatis.builder)
    XMLMapperBuilder (org.apache.ibatis.builder.xml)
    ParameterMappingTokenHandler in SqlSourceBuilder (org.apache.ibatis.builder)
    MybatisXMLConfigBuilder (com.baomidou.mybatisplus.core)
    MapperBuilderAssistant (org.apache.ibatis.builder)
    XMLScriptBuilder (org.apache.ibatis.scripting.xmltags)
    XMLConfigBuilder (org.apache.ibatis.builder.xml)
    SqlSourceBuilder (org.apache.ibatis.builder)
    XMLStatementBuilder (org.apache.ibatis.builder.xml)
```

## define

```plantuml
@startuml

abstract class BaseBuilder {
    protected final Configuration configuration
    protected final TypeAliasRegistry typeAliasRegistry
    protected final TypeHandlerRegistry typeHandlerRegistry
}

BaseBuilder ^-- BaseBuilder
BaseBuilder ^-- XMLMapperBuilder
BaseBuilder ^-- XMLStatementBuilder
BaseBuilder ^-- SqlSourceBuilder

class XMLConfigBuilder extends BaseBuilder {
    - boolean parsed
    - final XPathParser parser
    - String environment
    - final ReflectorFactory localReflectorFactory
    + Configuration parse()
    - void parseConfiguration(XNode root)
}

class XMLMapperBuilder {
    - final XPathParser parser
    - final MapperBuilderAssistant builderAssistant
    - final Map<String, XNode> sqlFragments
    - final String resource
}

class XMLStatementBuilder {
    - final MapperBuilderAssistant builderAssistant
    - final XNode context
    - final String requiredDatabaseId
}

class SqlSourceBuilder {

}

@enduml
```
