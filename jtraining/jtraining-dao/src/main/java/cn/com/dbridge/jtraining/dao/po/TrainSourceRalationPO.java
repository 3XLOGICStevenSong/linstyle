package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
@Data
public class TrainSourceRalationPO implements Serializable {
    /**
     * トレーニングID
     */
    private Integer trainId;

    /**
     * トレーニング資料番号
     */
    private Integer sourceNo;

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
     * t_train_source_ralation
     */
    private static final long serialVersionUID = 1L;
}