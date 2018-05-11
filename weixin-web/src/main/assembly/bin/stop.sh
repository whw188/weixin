#!/bin/sh

[  -e `dirname $0`/env.sh ] \
    && . `dirname $0`/env.sh
[  -e `dirname $0`/prestart.sh ] \
    && . `dirname $0`/prestart.sh

if [ ! -d "$LOG_DIR" ] ;then
    mkdir "$LOG_DIR"
    if [ $? -ne 0 ] ;then
        echo "Cannot create $LOG_DIR" >&2
        exit 1
    fi
fi

#拼凑完整的classpath参数，包括指定lib目录下所有的jar
CLASSPATH=.
for i in "$APP_HOME"/lib/*.jar; do
   CLASSPATH="$CLASSPATH":"$i"
done
#echo $CLASSPATH
###################################
#(函数)判断程序是否已启动
#
#说明：
#使用awk，分割出pid ($2部分)，及Java程序名称($2部分)
###################################
#初始化psid变量（全局）
psid=0

checkpid() {
   javaps=`ps -U root -f | grep -v grep|grep java | grep $APP_HOME`

   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $2}'`
   else
      psid=0
   fi
}
###################################


###################################
start() {
   checkpid

   if [ $psid -ne 0 ]; then
      echo "================================"
      echo "warn: $APP_MAINCLASS already started! (pid=$psid)"
      echo "================================"
   else
      echo -n "Starting $APP_MAINCLASS ...\c"
      JAVA_CMD="java $JAVA_OPTS -classpath $CONFIGPATH:$CLASSPATH $APP_MAINCLASS"
      echo "$JAVA_CMD"
      nohup $JAVA_CMD >>/dev/null 2>&1 &
      checkpid
      if [ $psid -ne 0 ]; then
         echo "START (pid=$psid) [OK]"
      else
         echo "START [Failed]"
	 exit 1
      fi
   fi
}
###################################

###################################
stop() {
   checkpid

   if [ $psid -ne 0 ]; then
      echo -n "Stopping $APP_MAINCLASS ...(pid=$psid) "

      kill $psid 

      for i in {1..60};do
      echo -e ".\c"
      sleep 1
      PID_EXIST=`ps -f -p $psid | grep java`
          if [ -z "$PID_EXIST" ]; then
              echo "STOP ok , PID: $psid"
              break
          fi
          if [ $i -eq 60 ];then
	      echo "$psid未能正常关闭,开始强制kill" 
	      kill -9 $psid
	      sleep 3
	      PID_EXIST=`ps -f -p $psid | grep java`
	      if [ -z "$PID_EXIST" ];then 
		 echo "STOP ok , PID: $psid"
              else
	 	 echo "$psid未能正常关闭!!!"
		 exit 1
              fi
          fi
      done
   else
      echo "================================"
      echo "warn: $APP_MAINCLASS is not running"
      echo "================================"
   fi
}
###################################



###################################
status() {
   checkpid
 
   if [ $psid -ne 0 ];  then
      echo "$APP_MAINCLASS is running! (pid=$psid)"
   else
      echo "$APP_MAINCLASS is not running"
   fi
}
 
###################################



###################################
info() {
   echo "System Information:"
   echo "****************************"
   echo `head -n 1 /etc/issue`
   echo `uname -a`
   echo
   echo "JAVA_HOME=$JAVA_HOME"
   echo `java -version`
   echo
   echo "APP_HOME=$APP_HOME"
   echo "APP_MAINCLASS=$APP_MAINCLASS"
   echo "****************************"
}
###################################


###########################执行命令
stop
