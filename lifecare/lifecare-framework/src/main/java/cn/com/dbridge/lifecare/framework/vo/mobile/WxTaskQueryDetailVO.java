package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:  WxTaskQueryDetail
 * @Description:微信查询任务详情
 * @author: 陈健飞
 * @date:   2019年1月8日 上午11:05:44
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class WxTaskQueryDetailVO {
    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID")
    private Integer customId;
    /**
     *  订单主键
     */
    @ApiModelProperty(value = "订单主键")
    private Integer taskId;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String realName;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String mobile;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String orderAddr;
    /**
     * 服务内容
     */
    @ApiModelProperty(value = "服务内容")
    private String content;
    /**
     * 合计服务时长
     */
    @ApiModelProperty(value = "合计服务时长")
    private String orderDuration;
    /**
     * 服务日期
     */
    @ApiModelProperty(value = "服务日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDate;
    /**
     * 时间Begin
     */
    @ApiModelProperty(value = "时间Begin")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTime;
    /**
     * 时间End
     */
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    @ApiModelProperty(value = "时间End")
    private Date orderEndTime;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;
    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    private String orderNo;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
     * 服务类别名称
     */
    @ApiModelProperty(value = "服务类别名称")
    private String serviceCategoryName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
