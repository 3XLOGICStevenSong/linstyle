package cn.com.dbridge.lifecare.framework.dto.mobile;

import lombok.Data;

@Data
public class TaskDetailQueryDTO {
    /**
     * 任务ID
     */
    private Integer taskId;
    /**
     * 服务人员ID
     */
    private Integer servicePersonId;
    /**
     * 查看类型(0：任务池 1:我的任务)
     */
    private String type;
}
