org.springframework.web.servlet.HandlerExecutionChain


## define
处理器执行链，持有拦截器的集合。

```plantuml
@startuml

class HandlerExecutionChain {
	- final Object handler
	- HandlerInterceptor[] interceptors
	- List<HandlerInterceptor> interceptorList
	- int interceptorIndex = -1
	boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response)
	void applyPostHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView mv)
	void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex)
	void applyAfterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response)
}

interface HandlerInterceptor
HandlerExecutionChain o-- HandlerInterceptor

@enduml
```

