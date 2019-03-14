/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  PinginTest.java
 * @Package cn.com.dbridge.lifecare.pingyin
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2019年1月16日 上午11:20:03
 * @version V1.0
 * @Copyright: 2019 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.lifecare.pingyin;

import cn.com.dbridge.lifecare.pingyin.PinyinTool.Type;

/**
 * @ClassName:  PinginTest
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2019年1月16日 上午11:20:03
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class PinginTest {

    public static void main(String[] args) throws Exception {
        PinyinTool tool = new PinyinTool();
        System.out.println(
                "张天====" + tool.toPinYin("张天", "_", Type.LOWERCASE));
        System.out.println(
                "张天====" + tool.toPinYin("藏天", "", Type.UPPERCASE));
        System.out.println(
                "张天====" + tool.toPinYin("张天"));
        System.out.println("张天====" + tool.toPinYin("张天", "*"));
    }
}
