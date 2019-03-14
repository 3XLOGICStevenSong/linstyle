package sendmsg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @ClassName:  MainPro
 * @Description:检索数据库取得发送短信的对象,并在发送后更新结果
 * @author: 孙剑
 * @date:   2019年1月30日 下午2:51:40
 */
public class MainPro {
	//短信发送结果保存用list
	static List<Message> obList = new Vector<Message>();

	public static void main(String[] args)
			throws SQLException, InterruptedException {

		while (true) {
			//创建线程池:核心线程5,最大线程10,空闲线程存活时间200ms
			ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200,
					TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

			//创建Connection对象
			Connection conn = DriverManager.getConnection(Constants.url,
					Constants.user, Constants.password);

			//查询使用Statement对象的excuteQuery()方法 
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Constants.sqlSlt);

			//处理数据库的返回结果
			while (rs.next()) {
				//编号
				int id = rs.getInt("ID");
				//用户类型
				String type = rs.getString("USER_TYPE");
				//手机号码
				String phone = rs.getString("MOBILE");
				//姓名
				String name = rs.getString("REAL_NAME");
				//时间
				int step = rs.getInt("STEP") / 60;

				SendSms sendSms = new SendSms(id, type, phone, name, step);

				executor.execute(sendSms);
			}

			//关闭线程池
			executor.shutdown();
			executor.awaitTermination(10, TimeUnit.MINUTES);

			//关闭自动提交
			conn.setAutoCommit(false);
			//预编译SQL语句
			PreparedStatement pstmt = conn.prepareStatement(Constants.sqlIst);

			for (int i = 0; i < obList.size(); i++) {
				//添加预定义参数
				pstmt.setString(1, obList.get(i).status);
				pstmt.setString(2, obList.get(i).error);
				pstmt.setInt(3, obList.get(i).id);
				pstmt.addBatch();
				//添加短信消息表删除用SQL
				pstmt.addBatch("delete from t_sms_msg where ID = '"
						+ obList.get(i).id + "'");
			}

			//批量执行预定义SQL
			pstmt.executeBatch();
			//提交
			conn.commit();
			//打开自动提交
			conn.setAutoCommit(true);

			obList.clear();

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}

			//休眠一分钟
			Thread.sleep(60000);
		}
	}
}

/**
 * @ClassName:  SendSms
 * @Description:发送短信
 * @author: 孙剑
 * @date:   2019年1月30日 下午2:55:04
 */
class SendSms implements Runnable {
	private int id;
	private String type;
	private String phone;
	private String name;
	private int step;

	public SendSms(int id, String type, String phone, String name, int step) {
		this.id = id;
		this.type = type;
		this.phone = phone;
		this.name = name;
		this.step = step;
	}

	@Override
	public void run() {
		try {
			//初始化ascClient
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
					Constants.accessKeyId, Constants.accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou",
					Constants.product, Constants.domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			//组装请求对象
			SendSmsRequest request = new SendSmsRequest();
			//使用post提交
			request.setMethod(MethodType.POST);
			//待发送手机号
			request.setPhoneNumbers(phone);
			//短信签名
			request.setSignName(Constants.signName);
			//短信模板
			if (type.equals("1")) {
				request.setTemplateCode(Constants.templateCode_Customer);//客户
			} else {
				request.setTemplateCode(Constants.templateCode_Employee);//服务人员
			}
			//模板中的变量替换JSON串
			request.setTemplateParam(
					"{\"name\":\"" + name + "\", \"step\":\"" + step + "\"}");

			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

			if (sendSmsResponse.getCode() != null) {
				String status;
				if (sendSmsResponse.getCode().equals("OK")) {
					status = "0";
				} else {
					status = "1";
				}
				//短信发送结果添加到list
				MainPro.obList.add(
						new Message(status, sendSmsResponse.getMessage(), id));
			}
		} catch (ClientException e1) {
			e1.printStackTrace();
		}
	}
}

class Message {
	public String status;
	public String error;
	public int id;

	public Message(String status, String error, int id) {
		this.status = status;
		this.error = error;
		this.id = id;
	}
}