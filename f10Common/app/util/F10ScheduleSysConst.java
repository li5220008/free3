package util;

/**
 * 存放系统的常量
 * User: wenzhihong
 * Date: 12-9-28
 * Time: 上午10:53
 */
public abstract class F10ScheduleSysConst {

    //任务状态
    public enum HandleResult {
        NO_HANDLE,
        ERROR,
        OK,
        NO_RECORD //代表可能是关联的表的数据还没有过来
    }

    public static final String suffix = "";

    //<editor-fold desc="与消息源头队列有关的redis key">
    //队列名称
    public static final String QUEUE_KEY_NAME = "change_msg_queue" + suffix;
    //存放update的记录数
    public static final String TOTAL_INSERT_NUM_KEY_NAME = "q_insert" + suffix;
    //存放insert的记录数
    public static final String TOTAL_UPDATE_NUM_KEY_NAME = "q_update" + suffix;
    //存放del的记录数
    public static final String TOTAL_DEL_NUM_KEY_NAME = "q_del" + suffix;
    //---------------以上 key 由 mysql - tungsten-queue 程序 产生. 记录消息的记录数

    public static final String INSERT_ACTION = "insert";

    public static final String UPDATE_ACTION = "update";

    public static final String DEL_ACTION = "delete";

    public static final String ID_KEY = "UTSID"; //id在数据库列中的名字

    public static final String NO_RELEVANCE_QUEUE_NAME = "job_no_relevance_queue" + suffix ; //相关表还没有数据的队列

    public static final String ERROR_QUEUE_NAME = "job_error_queue" + suffix ; //任务处理错误的队列

    public static final String EXEC_EXCEPTION_QUEUE_NAME = "job_exec_exception_queue" + suffix ; //任务执行时发生异常的队列

    public static final String REST_COUNT = "job_rest_count" + suffix ; //休息次数

    public static final String SUCESS_COUNT = "job_sucess_count" + suffix ; //成功次数

    public static final String PROCESS_MSG_COUNT = "process_msg_count" + suffix ; //消息处理条数, 包含成功,失败的, 还有就是没有处理器的

    //</editor-fold>

    //<editor-fold desc="以下为在目标redis上的key">
    public static final int NEWS_LIST_SIZE = 199; //redis保留新闻的list的长度, 也就是 200
    public static final int BULLETIN_LIST_SIZE = 199; //redis保留公告的list的长度, 也就是 200
    public static final int REPORT_LIST_SIZE = 199; //redis保留研报的list的长度, 也就是 200
    public static final int COMPANYSCALE_LIST_SIZE = 199; //redis保留公司规模的list的长度, 也就是 200

    public static final int RECENTLY_NEWS_SIZE = 799; //最近新闻的list长度. 也就是 800

    //</editor-fold>
}
