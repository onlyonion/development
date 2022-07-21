
Java构建工具
- ant 2000年
- maven 2004年
- gradle 2012年

gant = ant + groovy

## maven
```sh
mvn  clean package  -Dmaven.test.skip=true
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dpackaging=jar  -Dversion=11.2.0.4 -Dfile=ojdbc6.jar
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dpackaging=jar  -Dversion=11.2.0.4 -Dfile=ojdbc6-11.2.0.4.jar
```
## gradle
```sh
# ant + ivy(依赖管理) + maven
# project、task
gradle dependencies
```
```groovy
  // helloworld
  task hellowrold {
      doLast {
          println 'hello world
      }
  }
  task helloworld << {
      println 'hello world
  }
  // resolutionStrategy
  resolutionStrategy {
      cacheChangingModulesFor 0, 'seconds'
      cacheDynamicVersionsFor 0, 'seconds'
  }
```