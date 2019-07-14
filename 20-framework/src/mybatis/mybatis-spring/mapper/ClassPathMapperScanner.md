org.mybatis.spring.mapper.ClassPathMapperScanner

## package
```
ClassPathScanningCandidateComponentProvider (org.springframework.context.annotation)
    ClassPathBeanDefinitionScanner (org.springframework.context.annotation)
        ClassPathMapperScanner (org.mybatis.spring.mapper)
```
## define
```plantuml
@startuml

interface EnvironmentCapable
interface ResourceLoaderAware
EnvironmentCapable ^.. ClassPathScanningCandidateComponentProvider
ResourceLoaderAware ^.. ClassPathScanningCandidateComponentProvider

class ClassPathScanningCandidateComponentProvider 
ClassPathScanningCandidateComponentProvider ^-- ClassPathBeanDefinitionScanner

class ClassPathBeanDefinitionScanner {
    - final BeanDefinitionRegistry registry
	- BeanDefinitionDefaults beanDefinitionDefaults = new BeanDefinitionDefaults()
	- String[] autowireCandidatePatterns
	.. bean名称生成器 ..
	- BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator()
	- ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver()
	- boolean includeAnnotationConfig = true
}
ClassPathBeanDefinitionScanner ^-- ClassPathMapperScanner

class ClassPathMapperScanner {
    - boolean addToConfig = true
    - SqlSessionFactory sqlSessionFactory
    - SqlSessionTemplate sqlSessionTemplate
    - String sqlSessionTemplateBeanName
    - String sqlSessionFactoryBeanName
    - Class<? extends Annotation> annotationClass
    - Class<?> markerInterface
    .. 映射工厂bean ..
    - MapperFactoryBean<?> mapperFactoryBean = new MapperFactoryBean<Object>()
}

@enduml
```

## methods

### doScan
```
/**
* Calls the parent search that will search and register all the candidates.
* Then the registered objects are post processed to set them as
* MapperFactoryBeans
*/
@Override
public Set<BeanDefinitionHolder> doScan(String... basePackages) {
    Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
    
    if (beanDefinitions.isEmpty()) {
      logger.warn("No MyBatis mapper was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
    } else {
      processBeanDefinitions(beanDefinitions);
    }
    
    return beanDefinitions;
}
```