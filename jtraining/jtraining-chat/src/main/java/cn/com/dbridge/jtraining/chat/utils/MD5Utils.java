package cn.com.dbridge.jtraining.chat.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
/**
 * @ClassName: MD5Utils
 * @Description: MD5 工具类
 * @author: 陈健飞
 * @date: 2018-12-13 20:19
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 * 注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class MD5Utils {

	/**
	 * @Description: 对字符串进行md5加密 
	 */
	public static String getMD5Str(String strValue) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
		return newstr;
	}

	public static void main(String[] args) {
		try {
			String md5 = getMD5Str("Andy");
			System.out.println(md5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
