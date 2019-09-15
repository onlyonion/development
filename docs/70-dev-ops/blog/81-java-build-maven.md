
## Distributed Configuration Management Platform(分布式配置管理平台) 

专注于各种 分布式系统配置管理 的通用组件/通用平台, 提供统一的配置管理服务。 

包括 百度、滴滴打车、银联、网易、拉勾网 等知名互联网公司正在使用! 


## Maven项目动态打包

1 web项目目录结构介绍
一个web项目的目录结构：
webapp/src/main/resources
--conf
--test/conf
--prod/conf
说明：
conf 为默认的配置文件（也是作为开发测试环境的配置文件）。
uat/conf 为测试环境的配置文件。
prod/conf 为生产环境的配置文件。
如果需要跟多环境的，可以增加更多的目录。
注意：当修改配置文件时，需要同时维护相应环境下的文件。

2 项目中引入Maven插件
项目中pom文件添加打包插件的依赖：
```xml
<plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-war-plugin</artifactId>
   <configuration>
      <!-- 释放将项目的类文件打成jar放到lib目录中。打成jar的好处是：只修改class时，可以只更新jar。 -->
      <archiveClasses>false</archiveClasses>
      <archive>
         <addMavenDescriptor>false</addMavenDescriptor>
      </archive>
      <webResources>
         <resource>
            <!-- config作为source folder，不会被打到jar中。 -->
            <directory>src/main/resources/${package.environment}</directory>
            <targetPath>WEB-INF/classes</targetPath>
            <filtering>true</filtering>
         </resource>
      </webResources>
      <packagingExcludes><!-- 排除多余配置文件 -->
         WEB-INF/classes/test/**,
         WEB-INF/classes/dev/**
      </packagingExcludes>
   </configuration>
   <executions>
      <execution>
         <id>generate-manifest</id>
         <phase>prepare-package</phase>
         <goals>
            <goal>manifest</goal>
         </goals>
      </execution>
   </executions>
</plugin>
<!-- 根据${package.environment}变量，进行动态打包。 -->
<properties>
   <package.environment></package.environment>
</properties>
```
打包命令：
mvn  clean package  -Dmaven.test.skip=true -U -Pdev //打开发环境包
mvn  clean package  -Dmaven.test.skip=true -U -Ptest   //打测试环境包
mvn  clean package  -Dmaven.test.skip=true -U -Pprod  //打生产环境包
打包时maven将相应的目录配置文件替换conf目录中配置文件。

3 Maven全局配置参数
Maven全局配置文${maven.home}/conf/settings.xml中，增加profile 环境变量：
```xml
<profiles>
   <profile>
      <id>dev</id>
      <properties>
         <package.environment></package.environment>
      </properties>
   </profile>
   <profile>
      <id>test</id>
      <properties>
         <package.environment>test</package.environment>
      </properties>
   </profile>
  <profile>
      <id>prod</id>
      <properties>
         <package.environment>prod</package.environment>
      </properties>
   </profile>
</profiles>
```