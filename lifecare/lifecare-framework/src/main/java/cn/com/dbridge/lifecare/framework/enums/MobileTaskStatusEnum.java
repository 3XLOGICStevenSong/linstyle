package cn.com.dbridge.lifecare.framework.enums;
/**
 * 
 * @ClassName:  OrderStatusEnum
 * @Description:任务状态枚举
 * @author: 陈健飞
 * @date:   2019年1月8日 下午7:25:28
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public enum MobileTaskStatusEnum {
    STARTED((byte)2,"开始任务"),
    FINISHED((byte)3,"完成任务");
    public final Byte value;
    public final String name;
    private MobileTaskStatusEnum(Byte value, String name) {
        this.value = value;
        this.name = name;
    }
    public static String getName(Byte value) {  
        MobileTaskStatusEnum[] taskStatusEnum = values();  
        for (MobileTaskStatusEnum taskStatus : taskStatusEnum) {  
            if (taskStatus.value.equals(value)) {  
                return taskStatus.name;  
            }  
        }  
        return null;  
    }  
}
