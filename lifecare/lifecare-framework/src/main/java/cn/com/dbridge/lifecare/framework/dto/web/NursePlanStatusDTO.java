package cn.com.dbridge.lifecare.framework.dto.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NursePlanStatusDTO {
	/**
     * 服务方案编号
     */
    @ApiModelProperty(value = "服务方案编号")
    private Integer nursePlanId;
	
	/**
	 * 方案状态(0:草稿 1:发布)
	 */
    @ApiModelProperty(value = "方案状态(0:草稿 1:发布)")
	private Byte nursePlanStatus;
}
