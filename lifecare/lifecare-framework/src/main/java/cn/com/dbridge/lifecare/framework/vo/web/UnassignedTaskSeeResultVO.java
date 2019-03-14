package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:WebUnassignedTaskSeeResultVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月8日 上午10:47:55
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class UnassignedTaskSeeResultVO {
	/**
	 * 总记录数
	 */
	@ApiModelProperty(value = "总记录数")
    private Integer total;
    /**
     * 服务总时长
     */
	@ApiModelProperty(value = "服务总时长")
    private Integer totalOrderDuration;
    /**
     * 任务编号
     */
	@ApiModelProperty(value = "任务编号")
    private String orderNo;
    /**
     * 服务类别
     */
	@ApiModelProperty(value = "服务类别")
    private String serviceCategoryName;
    /**
     * 预约开始时间
     */
	@ApiModelProperty(value = "预约开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTime;
    /**
     * 服务时长
     */
	@ApiModelProperty(value = "服务时长")
    private Integer orderDuration;
    /**
     * 客户姓名
     */
	@ApiModelProperty(value = "客户姓名")
    private String realName;
    /**
     * 客户手机
     */
	@ApiModelProperty(value = "客户手机")
    private String mobile;
    /**
     * 显示结果集
     */
	@ApiModelProperty(value = "显示结果集")
    private List<UnassignedTaskSeeVO> webUnassignedTaskSeeVOList;
}
