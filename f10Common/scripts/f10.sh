#!/bin/sh
#chkconfig: 35 85 15
#author:liujianli@gtadata.com
#布署的时候改成对应的项目根目录就OK
. /etc/rc.d/init.d/functions
f10_PROJ_NAME=f10
f10Schedule_PROJ_NAME=f10Scheduling
BASE_DIRECTORY=/var/myapp/f10/Trunk/
#PID_FILE=$DIRECTORY/server.pid
#LOG_FILE=$DIRECTORY/logs/system.out
#play脚本位置
DEAMON=/usr/local/play1.2.x_cust/play
#java安装目录
export JAVA_HOME=/usr/local/jdk1.6.0_33/
export LANG="en_US.UTF-8"
RET_VAL=0;
function start()
{
  if [ -f "$BASE_DIRECTORY$1/server.pid" ]
  then
         failure
         echo "$1服务运行中，请先关闭"
  else
        # echo "开始启动。。。"
         cd $BASE_DIRECTORY$1 && $DEAMON start>/dev/null
         success
         echo "$1服务启动成功"

  fi
}
function stop()
{
  if [ -f "$BASE_DIRECTORY$1/server.pid" ]
  then
        cd $BASE_DIRECTORY$1 && $DEAMON stop>/dev/null;
        sleep 3
        if [ -f "$BASE_DIRECTORY$1/server.pid" ]
           then
           #play 意外中止不会删除pid文件
           rm -f $BASE_DIRECTORY$1/server.pid
        fi
        success
        echo "$1停止成功"
  else
        failure
        echo "$1没启动"
  fi

}

function status()
{
  if [ -f "$BASE_DIRECTORY$1/server.pid" ]
  then
       pid=$(cat $BASE_DIRECTORY$1/server.pid)
       $(getMode $1)
       mode=$?
       if [ $mode -eq 1 ];
       then
           success
           echo "$1运行中. PID为:$pid,运行模式:生产模式"
       else
           success
           echo "$1运行中. PID为:$pid,运行模式:开发模式"
       fi
       #cat $BASE_DIRECTORY$1/server.pid
      # echo ""
  else
       failure
       echo "$1未运行"
  fi

}
# 0:开发模式 1 生产模式
function getMode()
{


       mode=$(sed -n "/^application.mode=dev/p" $BASE_DIRECTORY$1/conf/application.conf)

       if [ ${#mode} -gt 0 ]
       then
           return 0
       else
           #echo "test"
           return 1
       fi
       #mode_in_chinese=$(( "$mode" = "application.mode=dev"?"开发模式":"生产模式" ))

      #echo $mode_in_chinese

       #$($mode = "application.mode=dev") && echo "开发模式2" || echo "生产模式2"
      # return "未知模式"

}

function update()
{
  if [ -d "$BASE_DIRECTORY" ]
  then
       success
       cd $BASE_DIRECTORY && svn update || echo "svn 更新失败" && exit 1
       RET_VAL=0
  else
       failure
       echo "$BASE_DIRECTORY不是目录"
       RET_VAL=1
  fi
  return $RETVAL

}
case $1 in
'start')
        #start的时候也调用restart 因为当play异常关闭后server.pid还在 此时若调用start命令并不能重启
        $0 restart
;;
'stop')
        stop $f10_PROJ_NAME
        stop $f10Schedule_PROJ_NAME
;;
'restart')
        stop $f10_PROJ_NAME
        sleep 1
        start $f10_PROJ_NAME
        sleep 1
        stop $f10Schedule_PROJ_NAME
        sleep 1
        start $f10Schedule_PROJ_NAME
;;
'status')
        status $f10_PROJ_NAME
        status $f10Schedule_PROJ_NAME
;;
'update')
        update
;;
*)
        echo "usage: service f10 <start|stop|status|restart|update>"
;;
esac
exit 0

