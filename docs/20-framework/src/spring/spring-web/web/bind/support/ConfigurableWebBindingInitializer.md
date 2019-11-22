org.springframework.web.bind.support.ConfigurableWebBindingInitializer

## define
```java
public class ConfigurableWebBindingInitializer implements WebBindingInitializer {
	private boolean autoGrowNestedPaths = true;
	private boolean directFieldAccess = false;
	private MessageCodesResolver messageCodesResolver;
	private BindingErrorProcessor bindingErrorProcessor;
	private Validator validator;
	private ConversionService conversionService;
	private PropertyEditorRegistrar[] propertyEditorRegistrars;
}    
```