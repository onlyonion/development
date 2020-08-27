org.apache.shiro.mgt.CachingSecurityManager

## Hierarchy
```

```

## Define
```plantuml
@startuml

interface Authenticator
interface Authorizer
interface SessionManager

interface WebSecurityManager

interface SecurityManager #orange
interface CacheManagerAware
interface EventBusAware
interface Destroyable
abstract class CachingSecurityManager 

CacheManagerAware ^.. CachingSecurityManager
EventBusAware ^.. CachingSecurityManager
Destroyable ^.. CachingSecurityManager

Authenticator ^-- SecurityManager
Authorizer ^-- SecurityManager
SessionManager ^-- SecurityManager

SecurityManager ^-- WebSecurityManager


class DefaultWebSecurityManager #orange
WebSecurityManager ^.. DefaultWebSecurityManager
DefaultSecurityManager ^-- DefaultWebSecurityManager

'''''''''''''''''''''''''实现'''''''''''''''''''''''''
abstract class CachingSecurityManager #orange
abstract class RealmSecurityManager
abstract class AuthenticatingSecurityManager
abstract class AuthorizingSecurityManager
class SessionsSecurityManager
class DefaultSecurityManager  #orange

SecurityManager ^.. CachingSecurityManager
CachingSecurityManager ^-- RealmSecurityManager
RealmSecurityManager ^-- AuthenticatingSecurityManager
AuthenticatingSecurityManager ^-- AuthorizingSecurityManager
AuthorizingSecurityManager ^-- SessionsSecurityManager
SessionsSecurityManager ^-- DefaultSecurityManager

@enduml
```

```java
public abstract class CachingSecurityManager implements SecurityManager, Destroyable, CacheManagerAware, EventBusAware {
    private CacheManager cacheManager;
    private EventBus eventBus;
}
```