#!/bin/bash
source /etc/profile
JAVA_APP_PATH=$1
JAVA_WAR=$2
if [ "$JAVA_APP_PATH" = "" ]; then 
    echo "JAVA_APP_PATH IS NULL";exit 1
fi

if [ "$JAVA_WAR" = "" ]; then   
    echo "JAVA_WAR IS NULL";exit 1
fi

tomcat_port=`echo "$JAVA_APP_PATH"|cut -c 7-10`
tomcat_pid=`/usr/sbin/lsof -n -P -t -i :4${tomcat_port}`



[ -n "$tomcat_pid" ] && kill -9 $tomcat_pid
rm -rf ${JAVA_APP_PATH}/webapps/ROOT
rm -rf ${JAVA_APP_PATH}/work/Catalina/localhost/*
cp /jenkins/data/${JAVA_WAR} ${JAVA_APP_PATH}/webapps/ROOT.war
echo "PID:$tomcat_pid will be killed" >>/tmp/start.log
cd ${JAVA_APP_PATH}/bin/
./startup.sh  2>&1 &
