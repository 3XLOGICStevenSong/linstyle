package sendmsg1;

public final class Constants {
	private Constants() {
	}

	//URL指向要访问的数据库名
    public static final String url = "jdbc:mysql://210.76.163.54:3306/lifecare-test?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false";
	//数据库用户名
	public static final String user = "root";
	//数据库密码
	public static final String password = "Win2012@djbsql";

	//短信API产品名称
	public static final String product = "Dysmsapi";
	//短信API产品域名
	public static final String domain = "dysmsapi.aliyuncs.com";
	//accessKeyId
	public static final String accessKeyId = "LTAIvnKzL5PPlDeh";
	//accessKeySecret
	public static final String accessKeySecret = "Js4YjCUyJEK6YgFWPSQAZ78gXz7rwu";

	//短信签名
	public static final String signName = "至亲久久";
	//短信模板
	public static final String templateCode_Customer = "SMS_154505689";
	public static final String templateCode_Employee = "SMS_154586014";

	//短信消息检索用SQL
	public static final String sqlSlt = "SELECT `t_sms_msg`.ID, `t_sms_msg`.STEP, `t_user`.USER_TYPE, `t_user`.REAL_NAME, `t_user`.MOBILE FROM `t_sms_msg` INNER JOIN `t_user` ON `t_sms_msg`.USER_ID = `t_user`.USER_ID where SEND_TIME < date_add(now(),interval 1 minute) order by ID";
	//短信消息历史表表插入用SQL
	public static final String sqlIst = "INSERT INTO t_sms_msg_log ( TASK_ID, USER_ID, TYPE, PHONE, USER_NAME, SEND_TIME, STEP, STATUS, ERROR, CREATE_TIME, CREATE_BY, UPDATE_TIME, UPDATE_BY ) SELECT `t_sms_msg`.TASK_ID, `t_sms_msg`.USER_ID, `t_user`.USER_TYPE, `t_user`.MOBILE, `t_user`.REAL_NAME, `t_sms_msg`.SEND_TIME, `t_sms_msg`.STEP, ?, ?, `t_sms_msg`.CREATE_TIME, `t_sms_msg`.CREATE_BY, `t_sms_msg`.UPDATE_TIME, `t_sms_msg`.UPDATE_BY FROM `t_sms_msg` INNER JOIN `t_user` ON `t_sms_msg`.USER_ID = `t_user`.USER_ID WHERE ID = ?";
}
