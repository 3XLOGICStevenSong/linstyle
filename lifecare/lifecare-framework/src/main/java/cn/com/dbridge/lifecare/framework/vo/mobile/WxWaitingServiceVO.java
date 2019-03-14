package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:  WxMyTaskQueryVO
 * @Description:微信待服务接口
 * @author: 陈健飞
 * @date:   2019年1月8日 上午11:07:18
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
@Data
public class WxWaitingServiceVO {
    /**
     * 服务类别编码
     */
    @ApiModelProperty(value = "任务类别名称")
    private String serviceCategoryName;
    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    private String taskId;
    /**
     * 服务类别颜色
     */
    @ApiModelProperty(value = "任务类别名称")
    private String serviceCategoryColor;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    /**
     * 订单日期
     */
    @ApiModelProperty(value = "订单日期")
    private String orderDate;
    /**
     * 订单开始时间
     */
    @ApiModelProperty(value = "订单开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date startTime;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;
    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID")
    private Integer customId;
    /**
     * 订单结束时间
     */
    @ApiModelProperty(value = "订单结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date endTime;
    /**
     * 查看标志  （0：未看   1：已看）
     */
    @ApiModelProperty(value = "查看标志  （0：未看   1：已看）")
    private String seeFlg;
}
