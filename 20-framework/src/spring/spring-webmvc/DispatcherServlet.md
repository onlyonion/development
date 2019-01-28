org.springframework.web.servlet.HttpServletBean
org.springframework.web.servlet.FrameworkServlet
org.springframework.web.servlet.DispatcherServlet

## 1. 类图
DispatcherServlet通过继承FrameworkServlet和HttpServletBean而继承HttpServlet，通过使用Servlet API来对HTTP请求进行响应，
成为Spring MVC的前端处理器，同时成为MVC模块与Web容器集成的处理前端。

```yuml

// {type:class}

// servlet api
[Servlet{bg:thistle}]
[GenericServlet{bg:thistle}]
[HttpServlet{bg:thistle}]

// springmvc
[HttpServletBean{bg:wheat}]
[FrameworkServlet{bg:wheat}]
[DispatcherServlet{bg:tomato}]

// 请求处理
[HandlerMapping{bg:slategray}]
[HandlerAdapter{bg:slategray}]
[HandlerExceptionResolver{bg:slategray}]
[ViewResolver{bg:slategray}]

// 1. servlet规范
[Servlet]^-.-[GenericServlet]
[ServletConfig]^-.-[GenericServlet]
[GenericServlet]^-[HttpServlet]

// 2. HttpServletBean
[HttpServlet]^-[HttpServletBean]
// EnvironmentCapable 对get方法抽象
[EnvironmentCapable]^-.-[HttpServletBean]
// EnvironmentAware 对set方法抽象
[EnvironmentAware]^-.-[HttpServletBean]

// 3. FrameworkServlet
[HttpServletBean]^-[FrameworkServlet]
[ApplicationContextAware]^-.-[FrameworkServlet]

// 4. DispatcherServlet
[FrameworkServlet]^-[DispatcherServlet]

// 4.1 特性 multipart解析器
[DispatcherServlet]++-[MultipartResolver]
[DispatcherServlet]++-[LocaleResolver]
[DispatcherServlet]++-[ThemeResolver]

// 4.2 请求映射处理
[DispatcherServlet]++1-*[HandlerMapping]
[DispatcherServlet]++1-*[HandlerAdapter]
[DispatcherServlet]++1-*[HandlerExceptionResolver]

// 4.3 视图处理
[DispatcherServlet]++-[RequestToViewNameTranslator]
[DispatcherServlet]++-[FlashMapManager]
[DispatcherServlet]++1-*[ViewResolver]

```

## 2. 初始化 init()
第一次请求Servlet时，初始化

```mermaid
sequenceDiagram
    %% 1. 第一次请求servlet初始化
    StandardWrapper->>Servlet:init(ServletConfig)
    Servlet->>GenericServlet:init(ServletConfig)
    GenericServlet->>GenericServlet:init()
    
    %% 2. HttpServletBean初始化
    GenericServlet->>HttpServletBean:init()
    opt pvs不为空
        HttpServletBean->>HttpServletBean:initBeanWrapper()
    end
    HttpServletBean->>HttpServletBean:initServletBean()
    
    %% 3. FrameworkServlet
    HttpServletBean->>FrameworkServlet:initServletBean()
    HttpServletBean->>FrameworkServlet:initWebApplicationContext()

	%% 3.1 初始化web应用上下文
	opt 已经存在web应用上下文
		opt web应用上下文没有激活
			FrameworkServlet->>FrameworkServlet:configureAndRefreshWebApplicationContext()
			FrameworkServlet->>ConfigurableWebApplicationContext:refresh()
			ConfigurableWebApplicationContext->>AbstractApplicationContext:refresh()
		end
	end

	opt wac等于null
		FrameworkServlet->>FrameworkServlet:findWebApplicationContext()
    end

	opt 再次判断wac是否为null
		FrameworkServlet->>FrameworkServlet:createWebApplicationContext()
    end

	%% 3.2 模板方法，初始化策略
	FrameworkServlet->>DispatcherServlet:onRefresh(context)
	DispatcherServlet->>DispatcherServlet:initStrategies(context)

	%% 3.3 初始化其他
    FrameworkServlet->>FrameworkServlet:initFrameworkServlet()
```

## 3. 销毁 destroy()

```mermaid
sequenceDiagram
	Servlet->>FrameworkServlet:destroy()
	FrameworkServlet->>ConfigurableApplicationContext:close()
```

## 4. 处理请求 service()

### 4.1 请求经由Servlet最终到达DispatcherServlet
```mermaid
sequenceDiagram

	%% 经由tomcat链接器、适配器、应用过滤器等处理
	ApplicationFilterChain->>HttpServlet:service()
	HttpServlet->>FrameworkServlet:service()
	
	%% 分而治之
	alt 请求方法是PATCH或者null
	    %% FrameworkServlet自行处理
		FrameworkServlet->>FrameworkServlet:processRequest()
	else
		FrameworkServlet->>HttpServlet:super.service()
		
		%% FrameworkServlet处理请求
		HttpServlet->>FrameworkServlet:processRequest()
		
		%% DispatcherServlet处理请求
		FrameworkServlet->>DispatcherServlet:doService()
		
		%% DispatcherServlet分发请求，请求分发给处理器handler
		DispatcherServlet->>DispatcherServlet:doDispatch()
	end
```

### 4.2 DispatcherServlet.doDispatch()
* 请求映射
* 拦截器链
* 处理器适配器处理请求，之前、之后、完成
* 请求结果渲染视图

```mermaid
sequenceDiagram
	DispatcherServlet ->> DispatcherServlet: doDispatch()
	%% 1. 媒体类型检查
	DispatcherServlet ->> DispatcherServlet: 1. checkMultipart(request)

	%% 2. 获取请求处理器
	%% 一般每一个handlerMapping可以持有一系列从URL请求到Controller的映射，而Spring MVC提供了一系列的HandlerMapping实现。
	DispatcherServlet ->> DispatcherServlet: 2. getHandler(processedRequest)
	DispatcherServlet ->> HandlerMapping: 2.1 hm.getHandler(request)
	HandlerMapping -->> DispatcherServlet: 2.2 返回HandlerExecutionChain

	%% 3. 获取处理器适配器
	DispatcherServlet ->> DispatcherServlet: 3. getHandlerAdapter(mappedHandler.getHandler())

	%% 4.1 拦截器链，前置处理
	DispatcherServlet ->> HandlerExecutionChain:4.1 mappedHandler.applyPreHandle(processedRequest, response)
	loop Healthcheck
		HandlerExecutionChain ->> HandlerInterceptor: preHandle()
    end

	%% 4.2 处理器适配器处理请求
	DispatcherServlet ->> HandlerAdapter: 4.2 handle(processedRequest, response, mappedHandler.getHandler())

	%% 4.3 拦截器链，后置处理
	DispatcherServlet ->> HandlerExecutionChain: 4.3 mappedHandler.applyPostHandle(processedRequest, response, mv)
	loop Healthcheck
		HandlerExecutionChain ->> HandlerInterceptor: postHandle()
    end

	%% 5. 处理分发的结果，视图解析
	DispatcherServlet ->> DispatcherServlet: 5. processDispatchResult()
	DispatcherServlet ->> DispatcherServlet: 5.1 render()
	DispatcherServlet ->> AbstractView: 5.2 render()

	%% 4.4 拦截器链，完成处理
	DispatcherServlet ->> HandlerExecutionChain: 4.4 mappedHandler.triggerAfterCompletion(request, response, ex)
	
```
