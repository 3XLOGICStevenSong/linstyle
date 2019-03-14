package cn.com.dbridge.lifecare.framework.util;

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
 *  注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
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

    /**
     * 比较两个时间的时间间隔多少分钟
     * @param start 开始时间
     * @param end 结束时间
     * @return 结束时间-开始时间的分钟数
     */
    public static long between (Date start,Date end){
        long diff = end.getTime()-start.getTime();
        return  diff/(1000*60);
    }


    /**
     * @title: dateCompare
     * @description: 比较日期大小
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static int dateCompare(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateFirst = dateFormat.format(date1);
        String dateLast = dateFormat.format(date2);
        int dateFirstIntVal = Integer.parseInt(dateFirst);
        int dateLastIntVal = Integer.parseInt(dateLast);
        if (dateFirstIntVal > dateLastIntVal) {
            return 1;
        } else if (dateFirstIntVal < dateLastIntVal) {
            return -1;
        }
        return 0;
    }



//    public static void main(String ... args){
//        Date date1 = new Date(2019,10,11,10,00,00);
//        Date date2 = new Date(2019,10,11,10,10,00);
//        System.out.println(between(date1,date2));
//    }
    
}
