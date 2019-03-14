package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class TrainSourcePO implements Serializable {
    /**
     * トレーニング資料番号
     */
    private Integer sourceNo;

    /**
     * トレーニング資料種別
     */
    private Integer sourceType;

    /**
     * トレーニング資料パス
     */
    private String sourcePath;

    /**
     * トレーニング資料タイトル
     */
    private String sourceTitle;

    /**
     * トレーニング資料紹介
     */
    private String sourceDesc;

    /**
     * トレーニング資料画像
     */
    private String sourceDraw;

    /**
     * トレーニング資料規模(ビデオ:秒 テキスト:頁)
     */
    private Integer sourceLength;

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
     * t_train_source
     */
    private static final long serialVersionUID = 1L;

}