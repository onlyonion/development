org.apache.ibatis.builder.xml.XMLMapperBuilder
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

## package
```plantuml
@startuml

abstract class BaseBuilder {
    # final Configuration configuration
    # final TypeAliasRegistry typeAliasRegistry
    # final TypeHandlerRegistry typeHandlerRegistry
}

BaseBuilder ^-- XMLMapperBuilder

class XMLMapperBuilder {
    - final XPathParser parser
    - final MapperBuilderAssistant builderAssistant
    - final Map<String, XNode> sqlFragments
    - final String resource
    + void parse()
    - void parseConfiguration(XNode root)
}

@enduml
```