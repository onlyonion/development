Spring源码深度解析

核心实现

Spring整体架构和环境搭建

容器的基本实现

容器的基本用法

功能分析

工程搭建

spring的结构组成

bean包的层级结构

核心类介绍

容器的基础XmlBeanFactory

配置文件封装

加载Bean

获取XML的验证模式

DTD与XSD区别

验证模式的读取

获取Document

EntityResolver用法

解析及注册BeanDefinitions

profile属性的使用

解析并注册BeanDefinition

默认标签的解析

bean标签的解析及注册

解析BeanDefinition

AbstractBeanDefinition属性

解析默认标签中的自定义标签元素

注册解析的BeanDefinition

通知监听器解析及注册完成

alias标签的解析

import标签的解析

嵌入式beans标签的解析

自定义标签的解析

自定义标签的使用

自定义标签解析

获取标签的命名空间

提前自定义标签处理器

标签解析

bean的加载

FactoryBean的使用

缓存中获取单例bean

从bean的实例中获取对象

获取单例

准备创建bean

处理override属性

实例化的前置处理

循环依赖

什么是循环依赖

spring如何解决循环依赖

创建bean

创建bean单例

记录创建bean的ObjectFactory

属性注入

初始化bean

注册DisposableBean

容器的功能扩展

设置配置路径

扩展功能

环境准备

加载BeanFactory

定制BeanFactory

加载BeanDefinition

功能扩展

增加SPEL语言的支持

增加属性注册编辑器

增加ApplicationContextAwareProcessor处理器

设置忽略依赖

注册依赖

BeanFactory的后处理

激活注册的BeanFactoryPostProcessor

注册BeanPostProcessor

初始化消息资源

初始化ApplicationEventMulticaster

注册监听器

初始化非延迟加载单例

finishRefresh

AOP

动态AOP使用示例

动态AOP自定义标签

注册AnnotationAwareAspectJAutoProxyCreator

创建AOP代理

获取增强器

寻找匹配的增强器

创建代理

静态AOP使用示例

创建AOP静态代理

Instrumentation使用

自定义标签

织入

企业应用

数据库连接jdbc

spring连接数据库程序实现jdbc

save/update功能的实现

基础方法execute

update中的回调函数

query功能的实现

queryForObject

整合MyBatis

mybatis独立使用

Spring整合mybatis

源码分析

sqlSessionFactory创建

MapperFactoryBean的创建

MapperScannerConfigurer

事务

jdbc方式下的事务使用示例

事务自定义标签

注册InfrastructureAdvisorAutoProxyCreator

获取对应class/method的增强器

事务增强器

创建事务

回滚事务

事务提交

SpringMVC

SpringMVC快速体验

ContextLoaderListener

ServletContextLoaderListener的使用

Spring中的ContextLoaderListener

DispatcherServlet

servlet的使用

DispatcherServlet的初始化

WebApplicationContext的初始化

DispatcherServlet的逻辑处理

MultipartContent类型的request处理

根据request信息寻找对应的Handler

没找到对应Handler的错误处理

根据当前handler寻找对应的HandlerAdapter

缓存处理

HandlerIntereptor的处理

逻辑处理

异常视图处理

根据视图跳转页面

远程服务

RMI

使用示例

服务端实现

客户端实现

HttpInvoker

使用示例

服务端实现

客户端实现

Spring消息

JMS的独立使用

spring整合ActiveMQ

源码分析

JmsTemplate

监听器容器
