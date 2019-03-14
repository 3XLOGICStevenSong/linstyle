package cn.com.dbridge.lifecare.framework.enums;
/**
 * 
 * @ClassName:  TaskShowEnum
 * @Description:任务查看枚举
 * @author: 陈健飞
 * @date:   2019年1月25日 下午2:21:21
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public enum TaskShowEnum {
    NOT_SEEN("0","未看"),
    ALREADY_SEEN("1","已看");
    public final String value;
    public final String name;
    private TaskShowEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public static String getDesc(String value) {  
        TaskShowEnum[] taskShowEnum = values();  
        for (TaskShowEnum sex : taskShowEnum) {  
            if (sex.value.equals(value)) {  
                return sex.name;  
            }  
        }  
        return null;  
    }  
}
