org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader

## define
* parseDefaultElement 解析默认元素
  * importBeanDefinitionResource 解析import
  * processAliasRegistration 处理别名
  * processBeanDefinition 处理bean定义
  * doRegisterBeanDefinitions 递归解析
  
```plantuml
@startuml

interface BeanDefinitionDocumentReader {
	void registerBeanDefinitions(Document doc, XmlReaderContext readerContext)
}

class DefaultBeanDefinitionDocumentReader {
    - XmlReaderContext readerContext
    - BeanDefinitionParserDelegate delegate
    # void preProcessXml(Element root)
    # void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate)
    # void postProcessXml(Element root)
    .. 解析默认元素 ..
    - void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate)
}
BeanDefinitionDocumentReader ^.. DefaultBeanDefinitionDocumentReader

@enduml
```