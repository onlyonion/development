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
backup_date=/jenkins/data/backup/${tomcat_port}
if [ ! -d "${backup_date}" ]; then
   mkdir -p ${backup_date}
fi

cp ${JAVA_APP_PATH}/webapps/ROOT.war ${backup_date}/${JAVA_WAR}-`date +"%Y-%m-%d"`
tomcat_pid=`/usr/sbin/lsof -n -P -t -i :4${tomcat_port}`

#
if [  -d "${backup_date}" ]; then
   find ${backup_date} -type f -mtime +15|xargs rm -rf
fi

#LOG
DateTime=`date +%Y-%m-%d_%H:%M:%S`

[ -n "$tomcat_pid" ] && kill -9 $tomcat_pid
rm -rf ${JAVA_APP_PATH}/webapps/ROOT
rm -rf ${JAVA_APP_PATH}/work/Catalina/localhost/*
cp /jenkins/data/${JAVA_WAR} ${JAVA_APP_PATH}/webapps/ROOT.war
cd ${JAVA_APP_PATH}/bin/
./startup.sh  2>&1 &
