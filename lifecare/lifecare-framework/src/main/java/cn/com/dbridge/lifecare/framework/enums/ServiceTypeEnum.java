package cn.com.dbridge.lifecare.framework.enums;
/**
 * 
 * @ClassName:  SexEnum
 * @Description:服务类别枚举
 * @author: 陈健飞
 * @date:   2019年1月8日 下午7:25:40
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public enum ServiceTypeEnum {
    LIFE_ASSISTANT("A","生活助理"),
    ELDERLY_CARE("B","老龄照护"),
    CLINICAL_NURSING("C","临床护理");
    public final String value;
    public final String name;
    private ServiceTypeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public static String getDesc(String value) {  
        ServiceTypeEnum[] serviceTypeEnum = values();  
        for (ServiceTypeEnum serviceType : serviceTypeEnum) {  
            if (serviceType.value.equals(value)) {  
                return serviceType.name;  
            }  
        }  
        return null;  
    }  
}
