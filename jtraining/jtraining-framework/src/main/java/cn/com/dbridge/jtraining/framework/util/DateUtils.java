package cn.com.dbridge.jtraining.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @ClassName:  DateUtils
 * @Description: 时间转化工具类
 * @author: 陈健飞
 * @date:   2018年12月24日 下午7:22:48
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class DateUtils {
    /**
     * 
     * @Title: TimeStamp2Date
     * @Description: 时间戳转换为Date字符串
     * @param timestampString
     * @param formats
     * @return
     */
    public static String timeStamp2DateStr(String timestampString,
            String formats) {
        if (StringUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA)
                .format(new Date(timestamp));
        return date;
    }
    
    public static Date timestamp2Date(String timestampString,
            String formats) throws ParseException {
        String timeStamp2DateStr = timeStamp2DateStr(timestampString,formats);
        SimpleDateFormat format = new SimpleDateFormat();
        return  format.parse(timeStamp2DateStr);
    }
}
