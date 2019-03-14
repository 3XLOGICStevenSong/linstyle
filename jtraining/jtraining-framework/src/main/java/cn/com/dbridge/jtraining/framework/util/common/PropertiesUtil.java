package cn.com.dbridge.jtraining.framework.util.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import cn.com.dbridge.jtraining.framework.exception.CustomException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName:  PropertiesUtil
 * @Description:Properties工具
 * @author: 陈健飞
 * @date:   2018年12月5日 下午1:06:57
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注：このコンテンツはBJ Information Technology Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
@Slf4j
public class PropertiesUtil {


    /**
     * PROP
     */
    private static final Properties PROP = new Properties();

    /**
     * 读取配置文件
     * @param fileName
     * @return void
     * @author Andy
     * @date 2018/8/31 17:29
     */
    public static void readProperties(String fileName){
        InputStream in = null;
        try {
            in = PropertiesUtil.class.getResourceAsStream("/" + fileName);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            PROP.load(bf);
        } catch (IOException e){
            log.error("PropertiesUtil工具类读取配置文件出现IOException异常:" + e.getMessage());
            throw new CustomException("PropertiesUtil工具类读取配置文件出现IOException异常:" + e.getMessage());
        } finally {
            try{
                if(in != null){
                    in.close();
                }
            }catch (IOException e){
                log.error("PropertiesUtil工具类读取配置文件出现IOException异常:" + e.getMessage());
                throw new CustomException("PropertiesUtil工具类读取配置文件出现IOException异常:" + e.getMessage());
            }
        }
    }

    /**
     * 根据key读取对应的value
     * @param key
     * @return java.lang.String
     * @author Andy
     * @date 2018/8/31 17:29
     */
    public static String getProperty(String key){
        return PROP.getProperty(key);
    }
}
