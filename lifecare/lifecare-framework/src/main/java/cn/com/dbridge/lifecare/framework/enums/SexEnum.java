package cn.com.dbridge.lifecare.framework.enums;
/**
 * 
 * @ClassName:  SexEnum
 * @Description:性别枚举
 * @author: 陈健飞
 * @date:   2019年1月8日 下午7:25:40
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public enum SexEnum {
    MALE("0","男"),
    FE_MALE("1","女");
    public final String value;
    public final String name;
    private SexEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public static String getDesc(String value) {  
        SexEnum[] sexEnum = values();  
        for (SexEnum sex : sexEnum) {  
            if (sex.value.equals(value)) {  
                return sex.name;  
            }  
        }  
        return null;  
    }  
}
