package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class LearnRecordPO implements Serializable {
    /**
     * 学生番号
     */
    private String studentId;

    /**
     * トレーニングID
     */
    private Integer trainId;

    /**
     * トレーニング資料種別
     */
	private Integer sourceType;

    /**
     * トレーニング資料番号
     */
    private Integer sourceNo;

    /**
     * トレーニング資料規模(ビデオ:秒 テキスト:頁)
     */
    private Integer sourceLength;

    /**
     * トレーニング資料勉強規模(ビデオ:秒 テキスト:頁)
     */
    private Integer sourceLearnLength;

    /**
     * 完成パーセント
     */
	private BigDecimal learnPercent;

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
     * t_learn_record
     */
    private static final long serialVersionUID = 1L;

}