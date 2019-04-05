# dev environment
## 1. java
### 1.1 java环境变量
```
JAVA_HOME	D:\opt\java\jdk1.8.0_131
JRE_HOME    %JAVA_HOME%\jre
MAVEN_HOME	D:\opt\apache\apache-maven-3.3.9
ANT_HOME	D:\opt\apache\apache-ant-1.10.1
CLASSPATH	.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar
Path		;%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;%MAVEN_HOME%\bin;%ANT_HOME%\bin
NLS_LANG 	"AMERICAN_AMERICA.AL32UTF8"
```

### 1.2 java doc
-encoding UTF-8 -charset UTF-8

### 1.3 .bat
```
SETX JAVA_HOME "D:\opt\java\jdk1.8.0_131" /m
SETX JRE_HOME "%JAVA_HOME%\jre" /m
SETX M2_HOME "D:\opt\apahce\apache-maven-3.3.9" /m
SETX ANT_HOME "D:\opt\apahce\apache-ant-1.10.1" /m
SETX NLS_LANG "AMERICAN_AMERICA.AL32UTF8" /m
SETX Path "%JAVA_HOME%\bin;%M2_HOME%\bin;%ANT_HOME%\bin;%PATH%;"
```

## 2. nodejs
```
npm install cnpm -g --registry=https://registry.npm.taobao.org
cnpm install digo -g
```

## 3. php
## 4. python
## 5. object c, swift

# windows
## 查看端口占用
windows

netstat -aon|findstr "8080"
tasklist|findstr "2720"
taskkill /f /t /im tor.exe

linux 
netstat -tunlp | grep 8000
