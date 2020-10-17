org.springframework.web.method.support.HandlerMethodArgumentResolver

## Hierarchy
```
HandlerMethodArgumentResolver (org.springframework.web.method.support)
    PathVariableMapMethodArgumentResolver (org.springframework.web.servlet.mvc.method.annotation)
    AbstractNamedValueMethodArgumentResolver (org.springframework.web.method.annotation)
        RequestAttributeMethodArgumentResolver (org.springframework.web.servlet.mvc.method.annotation)
        RequestHeaderMethodArgumentResolver (org.springframework.web.method.annotation)
        RequestParamMethodArgumentResolver (org.springframework.web.method.annotation)
    RequestHeaderMapMethodArgumentResolver (org.springframework.web.method.annotation)
    ModelAttributeMethodProcessor (org.springframework.web.method.annotation)
```

## Define
```plantuml
@startuml

@enduml
```

```java
public interface HandlerMethodArgumentResolver {

	boolean supportsParameter(MethodParameter parameter);

	Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception;

}
```