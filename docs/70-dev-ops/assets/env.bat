:: Java
SETX JAVA_HOME D:\opt\java\jdk1.8.0_221 /m
SETX JRE_HOME %JAVA_HOME%\jre /m
SETX CLASSPATH .;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar /m
:: maven
SETX MAVEN_HOME	D:\opt\java\apache-maven-3.6.3 /m
SETX M2_HOME %MAVEN_HOME% /m
:: gradle
SETX JAVA_OPTS -Dfile.encoding=UTF-8 /m
SETX GRADLE_OPTS -Dfile.encoding=UTF-8 /m
SETX GRADLE_HOME D:\opt\java\gradle-6.6.1 /m
SETX GRADLE_USER_HOME D:\m2\repository /m
:: scala
SETX SCALA_HOME D:\opt\java\scala-2.11.5 /m
:: python
SETX CONDA_HOME D:\opt\tensorflow\anaconda3 /m
:: dot
SETX GRAPHVIZ_DOT D:\opt\tool\graphviz-2.38\bin\dot.exe /m
:: node
SETX NODE_HOME D:\opt\nodejs\node-v16.15.0-win-x64 /m
:: oracle
SETX NLS_LANG AMERICAN_AMERICA.AL32UTF8 /m

:: path
:: ";%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;%MAVEN_HOME%\bin;%GRADLE_HOME%\bin;%CONDA_HOME%;%CONDA_HOME%\Scripts;%SCALA_HOME%\bin;%NODE_HOME%"