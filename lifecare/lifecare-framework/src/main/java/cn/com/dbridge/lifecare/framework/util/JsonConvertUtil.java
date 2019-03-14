package cn.com.dbridge.lifecare.framework.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @ClassName:  JsonConvertUtil
 * @Description:Json和Object的互相转换，转List必须Json最外层加[]，转Object，Json最外层不要加[]
 * @author: 陈健飞
 * @date:   2018年12月5日 下午1:06:42
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注：このコンテンツはBJ Information Technology Co.、Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
public class JsonConvertUtil {
    /**
     * JSON 转 Object
     */
    public static <T> T jsonToObject(String pojo, Class<T> clazz) {
        return JSONObject.parseObject(pojo, clazz);
    }

    /**
     * Object 转 JSON
     */
    public static <T> String objectToJson(T t) {
        return JSONObject.toJSONString(t);
    }
}
