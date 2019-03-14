package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StudentEvaluateUpdateDTO implements Serializable {
    private static final long serialVersionUID = -7617665815403295274L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 学员编号（关联用户信息表中的user_id）
     */
    private Integer studentId;

    /**
     * 学员评价
     */
    private String evaluate;

    /**
     * 是否禁用(0:正常 1：禁用)
     */
    private Byte useStatus;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    /**
     * 创建人
     */
    private Integer insertPerson;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    private Integer updatePerson;

}