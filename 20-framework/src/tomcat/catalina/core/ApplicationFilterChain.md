

```
@startuml

interface FilterChain {
    + void doFilter(ServletRequest request, ServletResponse response)
}

class ApplicationFilterChain {
    + void doFilter(ServletRequest request, ServletResponse response)
    - void internalDoFilter(ServletRequest request, ServletResponse response)
}

FilterChain <|.. ApplicationFilterChain

@enduml
```