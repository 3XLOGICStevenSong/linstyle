package cn.com.dbridge.lifecare.framework.util;

import java.util.Random;
/**
 * 
 * @ClassName:  PwdUtil
 * @Description: 随机生成密码类
 * @author: 王林江
 * @date:   2019年01月08日 19:41:41
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class PwdUtil {

    /**
     * 
     * @Title: getCharArray
     * @author 王林江
     * @Description: 获取字母数字数组
     * @return
     */
    public static char[] getCharArray() {
        //整数
        String iLetters = "1234567890";
        //小写字母
        String lowLetters = "qwertyuiopasdfghjklzxcvbnm";
        //大写字母
        String upLetters = lowLetters.toUpperCase();
        //字母数字
        String word = lowLetters + upLetters + iLetters;
        //字母数字素组
        char[] c = word.toCharArray();
        return c;
    }

    /**
     * 
     * @Title: makePWD
     * @author 王林江
     * @Description: 生成随机密码
     * @param len 生成密码长度
     * @return
     */
    public static String makePWD(int len) {
        //获取包含26个字母大小写和数字的字符数组
        char[] c = getCharArray();
        //随机数生成器
        Random rd = new Random();
        //密码
        String pwd = "";
        for (int k = 0; k < len; k++) {
            //随机获取数组长度作为索引
            int index = rd.nextInt(c.length);
            //循环添加到字符串后面
            pwd += c[index];
        }
        return pwd;
    }

}
