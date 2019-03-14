package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:TaskManageVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月14日 下午3:58:49
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class TaskManageVO {
    /**
     * 订单主键
     */
	@ApiModelProperty(value = "订单主键")
    private Integer id;
    /**
     * 任务编号
     */
	@ApiModelProperty(value = "任务编号")
    private String orderNo;
    /**
     * 服务类别名称
     */
	@ApiModelProperty(value = "服务类别名称")
    private String serviceCategoryName;
    /**
     * 预约开始时间
     */
	@ApiModelProperty(value = "预约开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date orderDateTime;
    /**
     * 预约时长
     */
    @ApiModelProperty(value = "预约时长")
    private String orderDuration;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private Byte orderStatus;
    /**
     * 服务时长
     */
    @ApiModelProperty(value = "服务时长")
    private String serviceDuration;
    /**
     * 客户主键
     */
    @ApiModelProperty(value = "客户主键")
    private Integer customId;
    /**
     * 服务人员主键
     */
    @ApiModelProperty(value = "服务人员主键")
    private Integer servicePersonId;
    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名")
    private String customRealName;
    /**
     * 服务人员姓名
     */
    @ApiModelProperty(value = "服务人员姓名")
    private String servicePersonRealName;
    /**
     * 实际开始时间
     */
    @ApiModelProperty(value = "实际开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date serviceBeginTime;
    /**
     * 查看标志(0:未读 1:已读 2:查看)
     */
    @ApiModelProperty(value = "查看标志(0:未读 1:已读 2:查看)")
    private Byte seeFlg;
}
