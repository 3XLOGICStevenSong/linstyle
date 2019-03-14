package cn.com.dbridge.lifecare.framework.dto.mobile;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  MobileMyTaskQueryDTO
 * @Description:Mobile查询任务DTO
 * @author: 陈健飞
 * @date:   2019年1月8日 上午9:13:27
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class MobileTaskPoolQueryDTO {
    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID")
    private Integer customId;
    /**
     * 预约日期
     */
    @ApiModelProperty(value = "预约日期(yyyy-MM-dd)")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private String orderDate;
    /**
     * 订单状态，默认待完成状态
     */
    private String orderStatus;
    /**
     * 分页偏移量
     */
    @ApiModelProperty(value = "分页偏移量)")
    private Integer offset;
    /**
     * 每页显示记录数
     */
    @ApiModelProperty(value = "每页显示记录数")
    private Integer limit;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;
    /**
     * 类型(0：任务池 1:我的任务)
     */
    @ApiModelProperty(value = "类型(0：任务池 1:我的任务)")
    private String type;
}
