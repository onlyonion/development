## define
```
HandlerInterceptor (org.springframework.web.servlet)
    AsyncHandlerInterceptor (org.springframework.web.servlet)
        WebRequestHandlerInterceptorAdapter (org.springframework.web.servlet.handler)
        HandlerInterceptorAdapter (org.springframework.web.servlet.handler)
            LocaleChangeInterceptor (org.springframework.web.servlet.i18n)
            ThemeChangeInterceptor (org.springframework.web.servlet.theme)
            UserRoleAuthorizationInterceptor (org.springframework.web.servlet.handler)
            UriTemplateVariablesHandlerInterceptor in AbstractUrlHandlerMapping (org.springframework.web.servlet.handler)
            ConversionServiceExposingInterceptor (org.springframework.web.servlet.handler)
            PathExposingHandlerInterceptor in AbstractUrlHandlerMapping (org.springframework.web.servlet.handler)
    WebContentInterceptor (org.springframework.web.servlet.mvc)
    StatHandlerInterceptor (com.alibaba.druid.support.spring.mvc)
```


```java
public interface HandlerInterceptor {
	boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception;
	void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception;
	void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception;
}
```