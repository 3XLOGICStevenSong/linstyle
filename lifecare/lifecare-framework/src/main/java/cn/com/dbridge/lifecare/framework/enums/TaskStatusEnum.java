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
public enum TaskStatusEnum {
    TO_BE_ALLOCATED("1","待分配"),
    TO_BE_COMPLETED("2","待完成"),
    COMPLETED("3","已完成"),
    CANCEL("4","取消");
    public final String value;
    public final String name;
    private TaskStatusEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public static String getName(String value) {  
        TaskStatusEnum[] taskStatusEnum = values();  
        for (TaskStatusEnum taskStatus : taskStatusEnum) {  
            if (taskStatus.value.equals(value)) {  
                return taskStatus.name;  
            }  
        }  
        return null;  
    }  
}
