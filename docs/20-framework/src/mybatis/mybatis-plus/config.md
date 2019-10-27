
## spring boot 
```yaml
mybatis-plus:
  ......
  configuration:
    ......
  global-config:
    ......
    db-config:
      ......  
```

## spring mvc
```xml
<beans>
    <bean id="sqlSessionFactory" class="com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="mapperLocations" value="classpath:mappers/*.xml"/>
        <property name="configuration" ref="configuration"/> <!--  非必须  -->
        <property name="globalConfig" ref="globalConfig"/> <!--  非必须  -->
        <property name="plugins">
            <array>
                <bean class="com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor"></bean>
            </array>
        </property>
    </bean>
    
    <bean id="configuration" class="com.baomidou.mybatisplus.core.MybatisConfiguration">
    </bean>
    
    <bean id="globalConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig">
        <property name="dbConfig" ref="dbConfig"/> <!--  非必须  -->
    </bean>
    
    <bean id="dbConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig">
        <property name="keyGenerator" ref="keyGenerator"/>
    </bean>
    
    <bean id="keyGenerator" class="com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator"/>

    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.baomidou.mybatisplus.samples.quickstart.springmvc.mapper"/>
    </bean>

</beans>
```