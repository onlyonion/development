org.apache.shiro.authz.Authorizer

## Hierarchy
```
Authorizer (org.apache.shiro.authz)
    SecurityManager (org.apache.shiro.mgt)
    ModularRealmAuthorizer (org.apache.shiro.authz)
    AuthorizingRealm (org.apache.shiro.realm)
        SimpleAccountRealm (org.apache.shiro.realm)
        AbstractLdapRealm (org.apache.shiro.realm.ldap)
        JdbcRealm (org.apache.shiro.realm.jdbc)
        DefaultLdapRealm (org.apache.shiro.realm.ldap)
```

## Define
```java
public interface Authorizer {

    // 判断有没有权限
    boolean isPermitted(PrincipalCollection principals, String permission);
    boolean isPermitted(PrincipalCollection subjectPrincipal, Permission permission);
    boolean[] isPermitted(PrincipalCollection subjectPrincipal, String... permissions);
    boolean[] isPermitted(PrincipalCollection subjectPrincipal, List<Permission> permissions);
    boolean isPermittedAll(PrincipalCollection subjectPrincipal, String... permissions);
    boolean isPermittedAll(PrincipalCollection subjectPrincipal, Collection<Permission> permissions);
    
    // 检查权限，抛异常
    void checkPermission(PrincipalCollection subjectPrincipal, String permission) throws AuthorizationException;
    void checkPermission(PrincipalCollection subjectPrincipal, Permission permission) throws AuthorizationException;
    void checkPermissions(PrincipalCollection subjectPrincipal, String... permissions) throws AuthorizationException;
    void checkPermissions(PrincipalCollection subjectPrincipal, Collection<Permission> permissions) throws AuthorizationException;

    // 判断有没有角色
    boolean hasRole(PrincipalCollection subjectPrincipal, String roleIdentifier);
    boolean[] hasRoles(PrincipalCollection subjectPrincipal, List<String> roleIdentifiers);
    boolean hasAllRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers);
    
    // 检查角色，抛异常
    void checkRole(PrincipalCollection subjectPrincipal, String roleIdentifier) throws AuthorizationException;
    void checkRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers) throws AuthorizationException;
    void checkRoles(PrincipalCollection subjectPrincipal, String... roleIdentifiers) throws AuthorizationException;
}
```