org.springframework.core.AttributeAccessor

属性访问器、属性存取器

## hierachy

```
AttributeAccessor (org.springframework.core)
    TestContext (org.springframework.test.context)
    AttributeAccessorSupport (org.springframework.core)
        BeanMetadataAttributeAccessor (org.springframework.beans)
            PropertyValue (org.springframework.beans)
            AutowireCandidateQualifier (org.springframework.beans.factory.support)
            AbstractBeanDefinition (org.springframework.beans.factory.support)
                RootBeanDefinition (org.springframework.beans.factory.support)
                    ConfigurationClassBeanDefinition in ConfigurationClassBeanDefinitionReader (org.springframework.context.annotation)
                    ClassDerivedBeanDefinition in GenericApplicationContext (org.springframework.context.support)
                ChildBeanDefinition (org.springframework.beans.factory.support)
                GenericBeanDefinition (org.springframework.beans.factory.support)
                    ScannedGenericBeanDefinition (org.springframework.context.annotation)
                    AnnotatedGenericBeanDefinition (org.springframework.beans.factory.annotation)
    BeanDefinition (org.springframework.beans.factory.config)
        AnnotatedBeanDefinition (org.springframework.beans.factory.annotation)
            ScannedGenericBeanDefinition (org.springframework.context.annotation)
            ConfigurationClassBeanDefinition in ConfigurationClassBeanDefinitionReader (org.springframework.context.annotation)
            AnnotatedGenericBeanDefinition (org.springframework.beans.factory.annotation)
        AbstractBeanDefinition (org.springframework.beans.factory.support)
            RootBeanDefinition (org.springframework.beans.factory.support)
                ConfigurationClassBeanDefinition in ConfigurationClassBeanDefinitionReader (org.springframework.context.annotation)
                ClassDerivedBeanDefinition in GenericApplicationContext (org.springframework.context.support)
            ChildBeanDefinition (org.springframework.beans.factory.support)
            GenericBeanDefinition (org.springframework.beans.factory.support)
                ScannedGenericBeanDefinition (org.springframework.context.annotation)
                AnnotatedGenericBeanDefinition (org.springframework.beans.factory.annotation)
```

## class

```
@startuml

package org.springframework.core {
    interface AttributeAccessor {
        + void setAttribute(String name, Object value)
        + Object getAttribute(String name)
        + Object removeAttribute(String name)
        + boolean hasAttribute(String name)
        + String[] attributeNames()
    }
    
    abstract class AttributeAccessorSupport {
        - final Map<String, Object> attributes
    }
    
    AttributeAccessor <|.. AttributeAccessorSupport
}

interface BeanDefinition {
    + String getParentName()
    + String getBeanClassName()
    + String getScope()
    + boolean isLazyInit()
    + String[] getDependsOn()
    + boolean isAutowireCandidate()
    + boolean isPrimary()
    + String getFactoryBeanName()
    + String getFactoryMethodName()
    + ConstructorArgumentValues getConstructorArgumentValues()
    + String getInitMethodName()
    + String getDestroyMethodName()
    + int getRole()
    + String getDescription()
    + boolean isSingleton()
    + boolean isPrototype()
    + boolean isAbstract()
    + String getResourceDescription()
    + BeanDefinition getOriginatingBeanDefinition()
}
AttributeAccessor <|-- BeanDefinition

interface AnnotatedBeanDefinition {
}
BeanDefinition <|-- AnnotatedBeanDefinition


'''''''''''''''' AbstractBeanDefinition ''''''''''''''''''''
abstract class AbstractBeanDefinition {
    - volatile Object beanClass
    - String scope = SCOPE_DEFAULT
    - boolean abstractFlag = false
    - boolean lazyInit = false
    - int autowireMode = AUTOWIRE_NO
    - int dependencyCheck = DEPENDENCY_CHECK_NONE
    - String[] dependsOn
    - boolean autowireCandidate = true
    - boolean primary = false
    - final Map<String, AutowireCandidateQualifier> qualifiers
    - Supplier<?> instanceSupplier
    - boolean nonPublicAccessAllowed = true
    - boolean lenientConstructorResolution = true
    - String factoryBeanName
    - String factoryMethodName
    - ConstructorArgumentValues constructorArgumentValues
    - MutablePropertyValues propertyValues
    - MethodOverrides methodOverrides
    - String initMethodName
    - String destroyMethodName
    - boolean enforceInitMethod = true
    - boolean enforceDestroyMethod = true
    - boolean synthetic = false
    - int role = BeanDefinition.ROLE_APPLICATION
    - String description
    - Resource resource
}
AttributeAccessorSupport <|-- BeanMetadataAttributeAccessor
BeanMetadataAttributeAccessor <|-- AbstractBeanDefinition
BeanDefinition <|.. AbstractBeanDefinition

AbstractBeanDefinition <|-- RootBeanDefinition
AbstractBeanDefinition <|-- ChildBeanDefinition
AbstractBeanDefinition <|-- GenericBeanDefinition

GenericBeanDefinition <|-- ScannedGenericBeanDefinition
GenericBeanDefinition <|-- AnnotatedGenericBeanDefinition
@enduml
```