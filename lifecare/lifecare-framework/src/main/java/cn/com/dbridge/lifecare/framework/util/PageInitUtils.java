package cn.com.dbridge.lifecare.framework.util;

/**
 * 
 * @ClassName:  PageInitUtils
 * @Description:分页参数设置
 * @author: 陈健飞
 * @date:   2019年1月8日 下午7:20:22
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class PageInitUtils {
    /**
     * 
     * @Title: setPageOffset
     * @Description: 如果开始记录数为空，则默认设置为0
     * @param offset
     * @return
     */
    public static Integer setPageOffset(Integer offset) {
        if(null == offset) {
            return 0;
        }
        return offset;
    }
    /**
     * 
     * @Title: setPageLimit
     * @Description: 如果每页显示记录数为空，默认为5
     * @param limit
     * @return
     */
    public static Integer setPageLimit(Integer limit) {
        if(null == limit) {
            return 5;
        }
        return limit;
    }
}
