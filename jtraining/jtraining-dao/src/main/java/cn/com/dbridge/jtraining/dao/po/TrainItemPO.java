package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class TrainItemPO implements Serializable {
    /**
     * トレーニングID
     */
    private Integer trainId;

    /**
     * トレーニング種別
     */
    private Integer trainType;

    /**
     * トレーニング教师
     */
    private String trainTeacher;

    /**
     * トレーニングタイトル
     */
    private String trainTitle;

    /**
     * トレーニング紹介
     */
    private String trainDesc;

    /**
     * トレーニング画像
     */
    private String trainDraw;

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
     * t_train_item
     */
    private static final long serialVersionUID = 1L;

}