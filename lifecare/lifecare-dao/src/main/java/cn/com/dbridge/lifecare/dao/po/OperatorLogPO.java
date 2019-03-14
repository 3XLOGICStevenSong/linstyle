package cn.com.dbridge.lifecare.dao.po;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * t_operator_log
 * @author 
 */
@Data
@ToString
public class OperatorLogPO implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 模块名称
     */
    private String module;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     * 异常信息
     */
    private String exceptionInfo;

    /**
     * 编码 0:请求成功 1：请求失败
     */
    private Byte code;

    /**
     * 员工ID-员工编号生成规则
     */
    private String userCode;

    /**
     * 操作人IP
     */
    private String ip;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 参数
     */
    private String args;

    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    private static final long serialVersionUID = 1L;

}