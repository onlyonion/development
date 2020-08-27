org.apache.shiro

## Overview

### shiro-core
* aop
* authc 认证
  * [Authenticator](/docs/20-framework/src/apache/shiro/shiro-core/authc/Authenticator.md)
  * [AuthenticationInfo](/docs/20-framework/src/apache/shiro/shiro-core/authc/AuthenticationInfo.md)
* [authz](/docs/20-framework/src/apache/shiro/shiro-core/authz/README.md) 授权
  * [Authorizer](/docs/20-framework/src/apache/shiro/shiro-core/authz/Authorizer.md)
  * [Permission](/docs/20-framework/src/apache/shiro/shiro-core/authz/Permission.md)
  * [PermissionResolver](/docs/20-framework/src/apache/shiro/shiro-core/authz/PermissionResolver.md)
* [cache](/docs/20-framework/src/apache/shiro/shiro-core/cache/README.md) 缓存
* codec 
* concurrent 并发工具
* config
* crypto 加密
* dao
* env
* [event](/docs/20-framework/src/apache/shiro/shiro-core/event/README.md)
* io
* jndi
* ldap
* mgt
  * [SecurityManager](/docs/20-framework/src/apache/shiro/shiro-core/mgt/SecurityManager.md)
  * [CachingSecurityManager](/docs/20-framework/src/apache/shiro/shiro-core/mgt/CachingSecurityManager.md)
  * [DefaultSecurityManager](/docs/20-framework/src/apache/shiro/shiro-core/mgt/DefaultSecurityManager.md)
* realm 安全域
* [session](/docs/20-framework/src/apache/shiro/shiro-core/session/README.md) 会话管理
* subject 主题

### shiro-web
* mgt
  * [DefaultWebSecurityManager](/docs/20-framework/src/apache/shiro/shiro-web/mgt/DefaultWebSecurityManager.md)


## jars
```
org.apache.shiro:shiro-cache:1.4.0
org.apache.shiro:shiro-config-core:1.4.0
org.apache.shiro:shiro-config-ogdl:1.4.0
org.apache.shiro:shiro-core:1.4.0
org.apache.shiro:shiro-crypto-cipher:1.4.0
org.apache.shiro:shiro-crypto-core:1.4.0
org.apache.shiro:shiro-crypto-hash:1.4.0
org.apache.shiro:shiro-event:1.4.0
org.apache.shiro:shiro-lang:1.4.0
org.apache.shiro:shiro-spring:1.4.0
org.apache.shiro:shiro-web:1.4.0
```

## Overview
```plantuml
@startuml

'''''''''''''''''''''''''''''''AuthenticationToken'''''''''''''''''''''''''''''''
interface AuthenticationToken

AuthenticationToken o-- Principal
AuthenticationToken o-- Credentials

interface RememberMeAuthenticationToken
interface HostAuthenticationToken

AuthenticationToken ^-- RememberMeAuthenticationToken
AuthenticationToken ^-- HostAuthenticationToken

RememberMeAuthenticationToken ^.. UsernamePasswordToken
HostAuthenticationToken ^.. UsernamePasswordToken

'''''''''''''''''''''''''''''''Authenticator'''''''''''''''''''''''''''''''
interface Authenticator  #orangeRed
Authenticator ..> AuthenticationToken
Authenticator ..> AuthenticationInfo

Authenticator ^.. AbstractAuthenticator
abstract class AbstractAuthenticator

AbstractAuthenticator ^-- ModularRealmAuthenticator
ModularRealmAuthenticator o-- AuthenticationStrategy
ModularRealmAuthenticator "1" o-- "*" Realm

interface AuthenticationStrategy #orangeRed
AuthenticationStrategy ..> Realm
AuthenticationStrategy ..> AuthenticationToken
AuthenticationStrategy ..> AuthenticationInfo

interface AuthenticationInfo 
interface AuthorizationInfo 
AuthenticationInfo ^-- Account
AuthorizationInfo ^-- Account

'''''''''''''''''''''''''''''''Authorizer'''''''''''''''''''''''''''''''

interface Authorizer #magenta
interface SessionManager #magenta

'''''''''''''''''''''''''''''''Realm'''''''''''''''''''''''''''''''
interface Realm #lime

Realm ^.. CachingRealm
abstract class CachingRealm

CachingRealm ^-- AuthenticatingRealm
abstract class AuthenticatingRealm 

AuthenticatingRealm ^-- AuthorizingRealm
Authorizer ^.. AuthorizingRealm
abstract class AuthorizingRealm 

AuthorizingRealm ^-- JdbcRealm
AuthorizingRealm ^-- DefaultLdapRealm
AuthorizingRealm ^--SimpleAccountRealm

'''''''''''''''''''''''''''''''Subject'''''''''''''''''''''''''''''''
interface Subject 
Subject ^.. DelegatingSubject
class DelegatingSubject
DelegatingSubject o-- SecurityManager

interface WebSubject
Subject ^-- WebSubject
WebSubject ^.. WebDelegatingSubject
DelegatingSubject ^-- WebDelegatingSubject
class WebDelegatingSubject

abstract class SecurityUtils 
SecurityUtils o-- SecurityManager
SecurityUtils ..> Subject

'''''''''''''''''''''''''''''''SecurityManager'''''''''''''''''''''''''''''''

Authenticator ^-- SecurityManager
Authorizer ^-- SecurityManager
SessionManager ^-- SecurityManager

interface SecurityManager #orange
SecurityManager ..> Subject
SecurityManager ..> AuthenticationToken

SecurityManager ^.. CachingSecurityManager
CachingSecurityManager ^-- RealmSecurityManager
RealmSecurityManager ^-- AuthenticatingSecurityManager
AuthenticatingSecurityManager ^-- AuthorizingSecurityManager
AuthorizingSecurityManager ^-- SessionsSecurityManager

interface Destroyable
interface CacheManagerAware
interface EventBusAware

Destroyable ^.. CachingSecurityManager
CacheManagerAware ^.. CachingSecurityManager
EventBusAware ^.. CachingSecurityManager

abstract class CachingSecurityManager #orange
interface CacheManager #pink
CachingSecurityManager o-- CacheManager

abstract class RealmSecurityManager #orange
RealmSecurityManager "1" o-- "*" Realm

abstract class AuthenticatingSecurityManager #orange
AuthenticatingSecurityManager o-- Authenticator

abstract class AuthorizingSecurityManager #orange
AuthorizingSecurityManager o-- Authorizer

abstract class SessionsSecurityManager #orange
SessionsSecurityManager o-- SessionManager

'''''''''''''''''''''''''''''''DefaultSecurityManager'''''''''''''''''''''''''''''''
SessionsSecurityManager ^-- DefaultSecurityManager
class DefaultSecurityManager  #orange

DefaultSecurityManager o-- RememberMeManager
DefaultSecurityManager o-- SubjectDAO
DefaultSecurityManager o-- SubjectFactory

DefaultSecurityManager ^-- DefaultWebSecurityManager
class DefaultWebSecurityManager #wheat

interface RememberMeManager
interface SubjectDAO
interface SubjectFactory


SecurityManager ^-- WebSecurityManager
interface WebSecurityManager
WebSecurityManager ^.. DefaultWebSecurityManager


@enduml
```

<!--
```
aop
    AnnotationHandler
    MethodInterceptor                   方法拦截器
    MethodInvocation
    MethodInterceptorSupport
authc                                   认证
    credential
        CredentialsMatcher
        HashingPasswordService
        PasswordService
    pam
    Account
    Authenticator
    AuthenticationInfo
    AuthenticationListener
    AuthenticationToken
    HostAuthenticationToken
    LogoutAware
    MergableAuthenticationInfo
    RememberMeAuthenticationToken
    SaltedAuthenticationInfo
authz                                   授权
    annotation
        Logical
        RequiresAuthentication
        RequiresGuest
        RequiresPermissions
        RequiresRoles
        RequiresUser
    aop
    permission
    AuthorizationInfo
    Authorizer
    Permission
cache                                   缓存
    AbstractCacheManager
    Cache
    CacheManager
    CacheManagerAware
    MapCache
    MemoryConstrainedCacheManager
codec
    Base64
    CodecSupport
    H64
    Hex
concurrent
    SubjectAwareExecutor
    SubjectAwareExecutorService
    SubjectAwareScheduledExecutorService
config
    Ini
    Interpolator
    ResourceConfigurable
crypto
    hash
        Hash
        Md5Hash
        Sha256Hash
        HashService
    BlowfishCipherService
    RandomNumberGenerator
    CipherService
dao
env
    Environment
    NamedObjectEnvironment
event
    EventBus
    EventBusAware
    Subscribe
    Event
io
    ResourceUtils
    Serializer
    DefaultSerializer
jndi
    JndiCallback
    JndiTemplate
ldap
mgt
    AbstractRememberMeManager
    AuthenticatingSecurityManager
    AuthorizingSecurityManager
    CachingSecurityManager
    DefaultSecurityManager
    DefaultSessionStorageEvaluator
    DefaultSubjectDAO
    DefaultSubjectFactory
    RealmSecurityManager
    RememberMeManager
    SecurityManager
    SessionsSecurityManager
    SessionStorageEvaluator
    SubjectDAO
    SubjectFactory
realm
    AuthenticatingRealm
    AuthorizingRealm
    CachingRealm
    Realm
    RealmFactory
    SimpleAccountRealm
session
    mgt
        SessionManager
    Session
    SessionListener
subject
    Subject
    SubjectContext
    PrincipalCollection
    PrincipalMap
util
    Destroyable
    Factory
    Initializable
    Nameable
    PatternMatcher
    ThreadState
    ThreadContext
SecurityUtils
ShiroException
UnavailableSecurityManagerException
```
-->