/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  PinyinTool.java
 * @Package cn.com.dbridge.lifecare.pingyin
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2019年1月16日 上午10:27:22
 * @version V1.0
 * @Copyright: 2019 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.lifecare.pingyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @ClassName:  PinyinTool
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2019年1月16日 上午10:27:22
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class PinyinTool {

    HanyuPinyinOutputFormat format = null;
    public static enum Type {
        LOWERCASE, //全部小写
        UPPERCASE,              //全部大写
        FIRSTUPPER              //首字母大写
    }

    public PinyinTool() {
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }
    //
    public String toPinYin(String str)
            throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, "", Type.UPPERCASE);
    }

    public String toPinYin(String str, String spera)
            throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, spera, Type.UPPERCASE);
    }

    /**
     * 
     * @Title: toPinYin
     * @Description: 将str转换成拼音，如果不是汉字或者没有对应的拼音
     * @param str 要转化的汉字
     * @param spera 转化结果的分割符
     * @param type
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public String toPinYin(String str, String spera, Type type)
            throws BadHanyuPinyinOutputFormatCombination {
        if (str == null || str.trim().length() == 0)
            return "";
        if (type == Type.UPPERCASE) {
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        } else {
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }
        String py = "";
        String temp = "";
        String[] t;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c <= 128) {
                py += c;
            } else {
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (t == null) {
                    py += c;
                } else {
                    temp = t[0];
                    if (type == Type.FIRSTUPPER) {
                        temp = t[0].toUpperCase().charAt(0) + temp.substring(1);
                    }
                    if (temp.length() > 1) {
                        String str1 = temp.substring(1, 2);
                        if (str1.equalsIgnoreCase("h")) {
                            temp = temp.substring(0, 2);
                        } else {
                            temp = temp.substring(0, 1) + "0";
                        }
                    }
                    py += temp + (i == str.length() - 1 ? "" : spera);
                }
            }
        }
        return py.trim();
    }

    public static void main(String ... args) throws Exception{
        System.out.println(new PinyinTool().toPinYin("阿毛","",
                Type.UPPERCASE));
        System.out.println(new PinyinTool().toPinYin("陈健飞","",
                Type.UPPERCASE));
        System.out.println(new PinyinTool().toPinYin("中国","",
                Type.UPPERCASE));
    }
}
