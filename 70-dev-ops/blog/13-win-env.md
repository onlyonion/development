# windows环境变量

## java环境变量
JAVA_HOME	D:\opt\java\jdk1.8.0_131
CLASSPATH	.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar
Path		;%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin

## maven
MAVEN_HOME	D:\opt\apache\apache-maven-3.3.9
Path		;%MAVEN_HOME%\bin

## ant
ANT_HOME	D:\opt\apache\apache-ant-1.10.1
Path		;%ANT_HOME%\bin

NLS_LANG 	"AMERICAN_AMERICA.AL32UTF8"

# java doc
-encoding UTF-8 -charset UTF-8


SETX JAVA_HOME "D:\opt\java\jdk1.8.0_131" /m
SETX JRE_HOME "%JAVA_HOME%\jre" /m
SETX M2_HOME "D:\opt\apahce\apache-maven-3.3.9" /m
SETX ANT_HOME "D:\opt\apahce\apache-ant-1.10.1" /m
SETX NLS_LANG "AMERICAN_AMERICA.AL32UTF8" /m
SETX Path "%JAVA_HOME%\bin;%M2_HOME%\bin;%ANT_HOME%\bin;%PATH%;"

## 查看端口占用
windows

netstat -aon|findstr "8080"
tasklist|findstr "2720"
taskkill /f /t /im tor.exe

linux 
netstat -tunlp | grep 8000
