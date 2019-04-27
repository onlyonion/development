org.apache.shiro

## jars
```
Maven: org.apache.shiro:shiro-cache:1.4.0
Maven: org.apache.shiro:shiro-config-core:1.4.0
Maven: org.apache.shiro:shiro-config-ogdl:1.4.0
Maven: org.apache.shiro:shiro-core:1.4.0
Maven: org.apache.shiro:shiro-crypto-cipher:1.4.0
Maven: org.apache.shiro:shiro-crypto-core:1.4.0
Maven: org.apache.shiro:shiro-crypto-hash:1.4.0
Maven: org.apache.shiro:shiro-event:1.4.0
Maven: org.apache.shiro:shiro-lang:1.4.0
Maven: org.apache.shiro:shiro-spring:1.4.0
Maven: org.apache.shiro:shiro-web:1.4.0
```

## package
```
aop
    AnnotationHandler
    MethodInterceptor
    MethodInvocation
    MethodInterceptorSupport
authc
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
authz
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
cache
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
jndi
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

## overview
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

interface AuthenticationStrategy #pink
AuthenticationStrategy ..> Realm
AuthenticationStrategy ..> AuthenticationToken
AuthenticationStrategy ..> AuthenticationInfo

interface AuthenticationInfo 
interface AuthorizationInfo 
AuthenticationInfo ^-- Account
AuthorizationInfo ^-- Account

'''''''''''''''''''''''''''''''Authorizer'''''''''''''''''''''''''''''''

interface Authorizer #magenta
interface SessionManager #lightBlue

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

abstract class CachingSecurityManager #wheat
interface CacheManager #pink
CachingSecurityManager o-- CacheManager

abstract class RealmSecurityManager #wheat
RealmSecurityManager "1" o-- "*" Realm

abstract class AuthenticatingSecurityManager #wheat
AuthenticatingSecurityManager o-- Authenticator

abstract class AuthorizingSecurityManager #wheat
AuthorizingSecurityManager o-- Authorizer

abstract class SessionsSecurityManager #wheat
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

