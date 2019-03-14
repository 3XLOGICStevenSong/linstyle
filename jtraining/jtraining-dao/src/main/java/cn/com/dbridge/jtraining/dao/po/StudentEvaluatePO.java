package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
@Data
public class StudentEvaluatePO implements Serializable {
    /**
     * 評価教師
     */
    private String teacherId;

    /**
     * 学生番号
     */
    private String studentId;

    /**
     * 学生評価
     */
    private String evaluate;

    /**
     * 評価時間
     */
    private Timestamp evaluateTime;

    /**
     * 有効フラグ(0:有効 1:無効)
     */
    private Byte useStatus;

    /**
     * 作成日
     */
    private Timestamp insertDate;

    /**
     * 作成人
     */
    private String insertPerson;

    /**
     * 更新日
     */
    private Timestamp updateDate;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * t_student_evaluate
     */
    private static final long serialVersionUID = 1L;

}