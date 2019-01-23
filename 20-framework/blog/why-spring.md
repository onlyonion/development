[为什么要有Spring?](https://blog.csdn.net/b644ROfP20z37485O35M/article/details/78967361)

#### Web发展简史
老一辈的软件开发人员一般经历了从Model1到Model2，然后到后来的三层模型，最后到现在的Spring Boot。
如果从Model1到Model2说起到我们现在使用的Spring Boot为整个时间轴的话，大致可以分为4个阶段：
1. 初级阶段：Model1(jsp + javaBean) -> Model2(jsp + servlet + javaBean)/三层模型；
2. 中级阶段：使用EJB进行分布式应用开发，忍受重量级框架带来的种种麻烦；
3. 高级阶段：使用Spring春天带给我们的美好，但是还要忍受很多繁琐的配置；
4. 骨灰级阶段：使用Spring Boot，畅享“预定大于配置”带给我们的种种乐趣！

##### Model1开发模式：
Model1的开发模式是：JSP+JavaBean的模式，它的核心是Jsp页面，在这个页面中，Jsp页面负责整合页面和JavaBean（业务逻辑），而且渲染页面
##### Model2开发模式：
Model1虽然在一定程度上解耦了，但JSP依旧即要负责页面控制，又要负责逻辑处理，职责不单一！此时Model2应运而生，使得各个部分各司其职，Model2是基于MVC模式的。
Model2的开发模式是：Jsp+Servlet+JavaBean的模式，它和Model1不同的是，增加了Servlet，将调用页面数据，调用业务逻辑等工作放到了Servlet中处理，从而减轻了Jsp的工作负担！

Model1、Model2、三层是在解耦的基础上一步步进化而来，通过解耦我们可以进行进一步的抽象，以应对现实需求的变动。

##### 三层
1. 表示层，JSP/Servlet； 
2. 业务逻辑层：业务规则； 
3. 持久化层：主要包装持久化的逻辑 ；