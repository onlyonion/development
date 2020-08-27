org.apache.shiro.authc.AuthenticationInfo

## Hierarchy
```
AuthenticationInfo (org.apache.shiro.authc)
    Account (org.apache.shiro.authc)
        SimpleAccount (org.apache.shiro.authc)
    MergableAuthenticationInfo (org.apache.shiro.authc)
        SimpleAccount (org.apache.shiro.authc)
        SimpleAuthenticationInfo (org.apache.shiro.authc)
    SaltedAuthenticationInfo (org.apache.shiro.authc)
        SimpleAccount (org.apache.shiro.authc)
        SimpleAuthenticationInfo (org.apache.shiro.authc)
```

## Define
```plantuml
@startuml

interface AuthenticationInfo
interface Account
interface MergableAuthenticationInfo
interface SaltedAuthenticationInfo

AuthenticationInfo ^-- Account
AuthenticationInfo ^-- MergableAuthenticationInfo
AuthenticationInfo ^-- SaltedAuthenticationInfo

''''''''''''''''''''SimpleAccount''''''''''''''''''''
class SimpleAccount
Account ^.. SimpleAccount
MergableAuthenticationInfo ^.. SimpleAccount
SaltedAuthenticationInfo ^.. SimpleAccount

SimpleAccount *-- SimpleAuthenticationInfo
SimpleAccount *-- SimpleAuthorizationInfo

''''''''''''''''''''SimpleAuthenticationInfo''''''''''''''''''''
class SimpleAuthenticationInfo
MergableAuthenticationInfo ^.. SimpleAuthenticationInfo
SaltedAuthenticationInfo ^.. SimpleAuthenticationInfo

@enduml
```