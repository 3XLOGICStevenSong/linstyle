package cn.com.dbridge.lifecare.framework.util.common;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 
 * @ClassName:  Base64ConvertUtil
 * @Description:Base64工具
 * @author: 陈健飞
 * @date:   2018年12月5日 下午12:55:46
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注：このコンテンツはBJ Information Technology Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
public class Base64ConvertUtil {

    /**
     * 
     * @Title: encode
     * @Description: 加密JDK1.8
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String str) throws UnsupportedEncodingException {
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
        return new String(encodeBytes);
    }

    /**
     * 
     * @Title: decode
     * @Description: 解密JDK1.8
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes("utf-8"));
        return new String(decodeBytes);
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(encode("DJBDAXIONGMAO"));
    }
}
