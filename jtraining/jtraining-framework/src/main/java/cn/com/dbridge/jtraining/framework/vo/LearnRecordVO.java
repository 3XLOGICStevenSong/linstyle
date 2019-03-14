package cn.com.dbridge.jtraining.framework.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class LearnRecordVO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 学员编号（关联用户信息表中的user_id）
     */
    private Integer studentId;

    /**
     * 培训类别编号
     */
    private Integer categoryId;

    /**
     * 培训编号
     */
    private Integer trainId;

    /**
     * 培训项目编号
     */
    private Integer trainItemId;

    /**
     * 学习百分比（单个项目学习的百分比）
     */
    private Long learnPercent;

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