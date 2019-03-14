package com.djb.ylt.wechat.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 * 微信的配置参数
 * @author linh
 */

public class  WeixinConfigUtils {

	//private static final Log log = LogFactory.getLog(WeixinConfigUtils.class);


	public static String appid="wxae68e29cb6f8bcfd";
	public static String mch_id="1415457602";
	public static String notify_url;




	public static void main(String[] args) throws IOException {
		InputStream is = WeixinConfigUtils.class.getResourceAsStream("/resources/sys.properties");

		Properties properties = new Properties();

		properties.load(is);

		is.close();
		String appid = properties.getProperty("appid");
		System.out.println(appid);
	}

}
