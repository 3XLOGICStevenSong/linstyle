package cn.com.dbridge.jtraining.framework.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StudentEvaluateVO {

    /**
     * 学员编号（关联用户信息表中的user_id）
     */
    private Integer studentId;

    /**
     * 学员评价
     */
    private String evaluate;

    /**
     * 評価時間
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date evaluateTime;

    /**
     * 学生の名前
     */
    private String studentName;

    /**
     * 先生の名前
     */
    private String teacherName;

    /**
     * トレーニング種別
     */
    private Integer trainType;
    /**
     * トレーニングの名前
     */
    private String trainTypeName;
}