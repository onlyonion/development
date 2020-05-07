org.apache.shiro.authc

## Overview
```plantuml
@startuml

interface Authenticator

Authenticator ..> AuthenticationToken
Authenticator ..> AuthenticationInfo

interface AuthenticationInfo
interface AuthorizationInfo
interface Account

AuthenticationInfo ^-- Account
AuthorizationInfo ^-- Account

interface AuthenticationToken

@enduml
```