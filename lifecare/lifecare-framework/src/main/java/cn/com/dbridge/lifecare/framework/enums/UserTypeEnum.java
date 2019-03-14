package cn.com.dbridge.lifecare.framework.enums;
/**
 * 
 * @ClassName:  OrderStatusEnum
 * @Description:用户类型枚举
 * @author: 陈健飞
 * @date:   2019年1月8日 下午7:25:28
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public enum UserTypeEnum {
    CUSTOMER("1","客户"),
    SERVICE_PERSONAL("2","服务人员"),
    BACKGROUND_USER("3","后台用户");
    public final String value;
    public final String name;
    private UserTypeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public static String getName(String value) {  
        UserTypeEnum[] userTypeEnum = values();  
        for (UserTypeEnum userType : userTypeEnum) {  
            if (userType.value.equals(value)) {  
                return userType.name;  
            }  
        }  
        return null;  
    }  
}
