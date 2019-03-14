package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
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
     * 参数
     */
    private String args;

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
     * t_operator_log
     */
    private static final long serialVersionUID = 1L;
}