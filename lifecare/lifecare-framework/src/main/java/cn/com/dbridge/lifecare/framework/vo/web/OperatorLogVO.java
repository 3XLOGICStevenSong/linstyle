package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;
import java.util.Date;

import cn.com.dbridge.lifecare.dao.po.OperatorLogPO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  OperatorLogVO
 * @Description:操作日志VO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:54:27
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class OperatorLogVO implements Serializable {
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
     * 异常信息
     */
	@ApiModelProperty(value = "异常信息")
    private String exceptionInfo;

    /**
     * 编码 0:请求成功 1：请求失败
     */
	@ApiModelProperty(value = "编码 0:请求成功 1：请求失败")
    private Byte code;

    /**
     * 员工ID-员工编号生成规则
     */
    @ApiModelProperty(value = "员工ID-员工编号生成规则")
    private String userCode;

    /**
     * 操作人IP
     */
    @ApiModelProperty(value = "操作人IP")
    private String ip;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    private String args;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OperatorLogPO other = (OperatorLogPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getModule() == null ? other.getModule() == null : this.getModule().equals(other.getModule()))
                && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
                && (this.getStatusDesc() == null ? other.getStatusDesc() == null : this.getStatusDesc().equals(other.getStatusDesc()))
                && (this.getExceptionInfo() == null ? other.getExceptionInfo() == null : this.getExceptionInfo().equals(other.getExceptionInfo()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
                && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getArgs() == null ? other.getArgs() == null : this.getArgs().equals(other.getArgs()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getModule() == null) ? 0 : getModule().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getStatusDesc() == null) ? 0 : getStatusDesc().hashCode());
        result = prime * result + ((getExceptionInfo() == null) ? 0 : getExceptionInfo().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getArgs() == null) ? 0 : getArgs().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", module=").append(module);
        sb.append(", method=").append(method);
        sb.append(", statusDesc=").append(statusDesc);
        sb.append(", exceptionInfo=").append(exceptionInfo);
        sb.append(", code=").append(code);
        sb.append(", userCode=").append(userCode);
        sb.append(", ip=").append(ip);
        sb.append(", createTime=").append(createTime);
        sb.append(", args=").append(args);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}