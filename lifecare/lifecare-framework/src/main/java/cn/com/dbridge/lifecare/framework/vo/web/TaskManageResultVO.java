package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:TaskManageResultVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月14日 下午3:59:12
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class TaskManageResultVO {
    /**
     * 总条数
     */
	@ApiModelProperty(value = "总条数")
    private Long total;
    /**
     * 预约总时长
     */
	@ApiModelProperty(value = "预约总时长")
    private Integer totalOrderDuration;
    /**
     * 实际服务总时长
     */
	@ApiModelProperty(value = "实际服务总时长")
    private Integer totalServiceDuration;
    /**
     * 查询结果集
     */
	@ApiModelProperty(value = "查询结果集")
    private List<TaskManageVO> taskManageVOList;
}
