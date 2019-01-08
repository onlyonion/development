spring技术内幕

Spring的设计理念和架构

Spring的各个子项目

Spring的设计目标

Spring的整体架构

Spring的应用场景

spring核心实现篇

Spring Framework的核心：IoC容器的实现

Spring IoC容器概述

IoC容器和依赖反转模式

Spring IoC的应用场景

IoC容器系列的设计与实现：BeanFactory和ApplicationContext

Spring的IoC容器系列

Spring IoC容器的设计

IoC容器的初始化过程

BeanDefinition的Resource定位

BeanDefinition的载入和解析

BeanDefinition在IoC容器中的注册

IoC容器的依赖注入

容器其他相关特性的设计与实现

ApplicationContext和Bean的初始化及销毁

lazy-init属性和预实例化

FactoryBean的实现

BeanPostProcessor的实现

autowiring（自动依赖装配）的实现

Bean的依赖检查

Bean对IoC容器的感知

Spring AOP的实现

Spring AOP概述

AOP概念回顾

Advice通知

Pointcut切点

Advisor通知器

Spring AOP的设计与实现

JVM的动态代理特性

Spring AOP的设计分析

Spring AOP的应用场景

建立AopProxy代理对象

设计原理

配置ProxyFactoryBean

ProxyFactoryBean生成AopProxy代理对象

JDK生成AopProxy代理对象

CGLIB生成AopProxy代理对象

Spring AOP拦截器调用的实现

设计原理

JdkDynamicAopProxy的invoke拦截

Cglib2AopProxy的intercept拦截

目标对象方法的调用

AOP拦截器链的调用

配置通知器

Advice通知的实现

ProxyFactory实现AOP

Spring AOP的高级特性

Spring组件实现篇

Spring MVC与Web环境

SpringMVC概述

Web环境中的SpringMVC

上下文在Web容器中的启动

IoC容器启动的基本过程

Web容器中的上下文设计

ContextLoader的设计与实现

SpringMVC的设计与实现

SpringMVC的应用场景

SpringMVC的设计概览

DispatcherServlet的启动和初始化

MVC处理HTTP分发请求

SpringMVC视图的呈现

DispatcherServlet视图呈现的设计

JSP视图的实现

ExcelView的实现

PDF视图的实现

数据库操作组件的实现

Spring JDBC的设计与实现

Spring JDBC中的模板类的设计与实现

Spring JDBC中的RDBMS操作对象的实现

SqlQuery的实现

SqlUpdate的实现

SqlFunction

Spring ORM的设计与实现

Spring驱动Hibernate的设计与实现

设计原理

Hibernate的SessionFactory

HibernateTemplate的实现

Session的管理

Spring驱动iBatis的设计与实现

设计原理

创建SqlMapClient

SqlMapClientTempalte的实现

Spring事务处理的实现

Spring与事务管理

Spring事务处理的设计概览

Spring事务处理的应用场景

Spring声明式事务处理

Spring事务处理的设计与实现

Spring事务处理的编程式使用

事务的创建

事务的挂起

事务的提交

事务的回滚

Spring事务处理器的设计与实现

Spring事务处理的应用场景

DataSourceTransactionManager的实现

HibernateTransactionManager的实现

Spring远端调用的实现

Spring远端调用的应用场景

Spring远端调用的设计概览

Spring远端调用的实现

Spring HTTP调用器的实现

Spring Hession/Burlap的实现原理

Spring RMI的实现

Spring应用实现篇

安全框架ACEGI的设计与实现

Spring ACEGI安全框架概述

配置Spring ACEGI

ACEGI的Web过滤器实现

ACEGI验证器的实现

AuthenticationManager的authenticate

DaoAuthenticationProvider的实现

读取数据库用户信息

完成用户信息的对比验证

ACEGI授权器的实现

与Web环境的接口FilterSecurityInterceptor

授权器的实现

投票器的实现

Spring DM模块的设计与实现

Spring DM模块的应用场景

Spring DM的应用过程

Spring DM设计与实现

Spring Flex的设计与实现

Spring Flex模块的应用场景

Spring Flex的应用过程

Spring Flex的设计与实现
