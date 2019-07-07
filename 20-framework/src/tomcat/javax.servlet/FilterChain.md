javax.servlet.FilterChain
##  define

```
FilterChain (javax.servlet)
    ApplicationFilterChain (org.apache.catalina.core)
    CachedChain in ServletHandler (org.mortbay.jetty.servlet)
    Chain in ServletHandler (org.mortbay.jetty.servlet)
    MockFilterChain (org.springframework.mock.web)
    PassThroughFilterChain (org.springframework.mock.web)
```

## 请求在过滤器中传递

```mermaid
sequenceDiagram
    %% 1. CharacterEncodingFilter
    StandardWrapperValve ->> ApplicationFilterChain:doFilter()
    ApplicationFilterChain ->> OncePerRequestFilter:抽象基类doFilter()
    OncePerRequestFilter ->> CharacterEncodingFilter:doFilter()
    
    %% 2. HiddenHttpMethodFilter
    CharacterEncodingFilter ->> ApplicationFilterChain:doFilter()
    ApplicationFilterChain ->> OncePerRequestFilter:抽象基类doFilter()
    OncePerRequestFilter ->> HiddenHttpMethodFilter:doFilter()
    
    %% 3. HttpPutFormContentFilter
    HiddenHttpMethodFilter ->> ApplicationFilterChain:doFilter()
    ApplicationFilterChain ->> OncePerRequestFilter:抽象基类doFilter()
    OncePerRequestFilter ->> HttpPutFormContentFilter:doFilter()
    
    %% 4. RequestContextFilter
    HttpPutFormContentFilter ->> ApplicationFilterChain:doFilter()
    ApplicationFilterChain ->> OncePerRequestFilter:抽象基类doFilter()
    OncePerRequestFilter ->> RequestContextFilter:doFilter()
    
    %% 5. WsFilter
    RequestContextFilter ->> ApplicationFilterChain:doFilter()
    ApplicationFilterChain ->> WsFilter:doFilter()
    
``` 
