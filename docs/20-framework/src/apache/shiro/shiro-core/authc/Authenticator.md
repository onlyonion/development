org.apache.shiro.authc.Authenticator

## Hierarchy
```
Authenticator (org.apache.shiro.authc)
    SecurityManager (org.apache.shiro.mgt)
    AbstractAuthenticator (org.apache.shiro.authc)
        ModularRealmAuthenticator (org.apache.shiro.authc.pam)
```

## Define
```plantuml
@startuml

interface Authenticator {
    + AuthenticationInfo authenticate(AuthenticationToken authenticationToken)
                throws AuthenticationException;
}

interface SecurityManager
abstract class AbstractAuthenticator
class ModularRealmAuthenticator

Authenticator ^-- SecurityManager
Authenticator ^.. AbstractAuthenticator
AbstractAuthenticator ^-- ModularRealmAuthenticator

interface AuthenticationInfo
interface AuthenticationToken
Authenticator ..> AuthenticationInfo
Authenticator ..> AuthenticationToken


interface Account
AuthenticationInfo ^-- Account
AuthorizationInfo ^-- Account

@enduml
```