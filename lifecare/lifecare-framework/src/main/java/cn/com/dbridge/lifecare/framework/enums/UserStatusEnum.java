package cn.com.dbridge.lifecare.framework.enums;
/**
 * 
 * @ClassName:  UserStatusEnum
 * @Description:用户状态枚举
 * @author: 王林江
 * @date:   2019年1月9日 下午16:25:40
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public enum UserStatusEnum {
    RUN_STATUS((byte)0, "启用"), STOP_STATUS((byte)1, "停用");
    public final Byte value;
    public final String name;
    private UserStatusEnum(Byte value, String name) {
        this.value = value;
        this.name = name;
    }
    public static String getDesc(Byte value) {
        UserStatusEnum[] userStatusEnum = values();
        for (UserStatusEnum userStatus : userStatusEnum) {
            if (userStatus.value == value) {
                return userStatus.name;
            }  
        }  
        return null;  
    }  
}
