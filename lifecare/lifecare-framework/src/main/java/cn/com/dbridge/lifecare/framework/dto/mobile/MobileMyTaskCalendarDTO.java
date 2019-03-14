package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileMyTaskCalendarDTO
 * @Description:预约日期DTO
 * @author:郭健
 * @date:2019年1月3日 下午2:47:54
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileMyTaskCalendarDTO {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    /**
     * 预约日期
     */
    @ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMM")
    private Date orderDate;

    /**
          *  查看类型(0：任务池 1:我的任务)
     */
    @ApiModelProperty(value = "查看类型(0：任务池 1:我的任务)")
    private Byte type;
}
