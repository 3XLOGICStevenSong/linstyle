package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:WebTaskPoolVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月8日 下午5:07:32
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class TaskPoolVO {
    /**
     *服务类颜色
     */
	@ApiModelProperty(value = "服务类颜色")
    private String serviceCategoryColor;
    /**
     * 预约地址
     */
	@ApiModelProperty(value = "预约地址")
    private String orderAddr;
    /**
     * 预约日期
     */
	@ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDate;
    /**
     * 预约开始时间
     */
    @ApiModelProperty(value = "预约开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTime;
    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderEndTime;
    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;
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
     * 服务类别编码
     */
    @ApiModelProperty(value = "服务类别编码")
    private String serviceCategoryCode;
    /**
     * 性别要求(0:不限 1:男 2:女)
     */
    @ApiModelProperty(value = "性别要求(0:不限 1:男 2:女)")
    private Byte sexRequire;
}
