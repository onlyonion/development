org.springframework.web.filter.OncePerRequestFilter
## hierarchy
```
GenericFilterBean (org.springframework.web.filter)
    OncePerRequestFilter (org.springframework.web.filter)
        1 in ErrorPageFilter (org.springframework.boot.web.support)
        CharacterEncodingFilter (org.springframework.web.filter)
        HiddenHttpMethodFilter (org.springframework.web.filter)
        HttpPutFormContentFilter (org.springframework.web.filter)
        RelativeRedirectFilter (org.springframework.web.filter)
        CorsFilter (org.springframework.web.filter)
        RequestContextFilter (org.springframework.web.filter)
        ApplicationContextHeaderFilter (org.springframework.boot.web.filter)
        ForwardedHeaderFilter (org.springframework.web.filter)
        MultipartFilter (org.springframework.web.multipart.support)
        ShallowEtagHeaderFilter (org.springframework.web.filter)
        AbstractRequestLoggingFilter (org.springframework.web.filter)
```

## define
```
@startuml

abstract class GenericFilterBean {
    - FilterConfig filterConfig
    # void initFilterBean()
}

abstract class OncePerRequestFilter {
    + final void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
    # abstract void doFilterInternal(
    			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
}

GenericFilterBean <|-- OncePerRequestFilter
@enduml
```