package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:OperatorLogVO
 * @Description:操作日志VO
 * @author:陈健飞
 * @date:2018年12月27日 下午12:54:27
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class MobileOperatorLogVO implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 模块名称
     */
    @ApiModelProperty(value = "模块名称")
    private String module;

    /**
     * 方法名称
     */
    @ApiModelProperty(value = "方法名称")
    private String method;

    /**
     * 状态描述
     */
    @ApiModelProperty(value = "状态描述")
    private String statusDesc;

    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    private String args;

    /**
     * 员工ID-员工编号生成规则
     */
    @ApiModelProperty(value = "员工ID")
    private String userCode;

    /**
     * 操作人IP
     */
    @ApiModelProperty(value = "员工ID")
    private String ip;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * t_operator_log
     */
    private static final long serialVersionUID = 1L;
}