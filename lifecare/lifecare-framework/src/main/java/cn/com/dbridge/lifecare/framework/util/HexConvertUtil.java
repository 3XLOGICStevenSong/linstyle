package cn.com.dbridge.lifecare.framework.util;

import cn.com.dbridge.lifecare.framework.exception.CustomAuthException;

/**
 * 
 * @ClassName: HexConvertUtil
 * @Description:进制转换
 * @author: 陈健飞
 * @date: 2018年12月11日 下午1:40:16
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注：このコンテンツはBJ
 *             Information Technology
 *             Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
public class HexConvertUtil {

	/**
	 * 1
	 */
	private static final Integer INTEGER_1 = 1;

	/**
	 * 2
	 */
	private static final Integer INTEGER_2 = 2;

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buff
	 * @return java.lang.String
	 * @author 陈健飞
	 * @date 2018/8/31 17:20
	 */
	public static String parseByte2HexStr(byte[] buff) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0, len = buff.length; i < len; i++) {
			String hex = Integer.toHexString(buff[i] & 0xFF);
			if (hex.length() == INTEGER_1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return byte[]
	 * @author 陈健飞
	 * @date 2018/8/31 17:21
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < INTEGER_1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / INTEGER_2];
		try {
			for (int i = 0, len = hexStr.length() / INTEGER_2; i < len; i++) {
				int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
				int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
				result[i] = (byte) (high * 16 + low);
			}
			return result;
		} catch (Exception e) {
			throw new CustomAuthException("用户名或密码错误");
		}
	}
}
