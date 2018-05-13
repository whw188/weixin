APP="weixin-web"
#需要启动的Java主程序（main方法类）
APP_MAINCLASS=com.xzh.weixin.web.main.StartupWeixin

LOG_BASE="/export/log"
LOG_DIR="$LOG_BASE/$APP"
APP_HOME="/export/server/$APP"
CONFIGPATH=$APP_HOME/conf
#STDOUT_FILE=$LOG_DIR/stdout.log
GC_FILE=$LOG_DIR/gc.log

export LANG="zh_CN.UTF-8"
export LC_ALL="zh_CN.UTF-8"
export JAVA_HOME=/export/local/jdk1.7
export JAVA_BIN=/export/local/jdk1.7/bin
export PATH=$PATH:$JAVA_BIN
JAVA_OPTS="-server -Xms1024m -Xmx1024m -XX:PermSize=128m -XX:MaxPermSize=128m -XX:+UseConcMarkSweepGC -verbose:gc -Xloggc:"$GC_FILE" -Dfile.encoding=UTF-8"
