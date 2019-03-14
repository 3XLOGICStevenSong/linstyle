package cn.com.dbridge.jtraining.framework.vo;

import lombok.Data;

@Data
public class TrainVO{
    /**
     * トレーニングID
     */
    private Integer trainId;

    /**
     * トレーニング資料番号
     */
    private Integer sourceNo;

    /**
     *  トレーニング資料種別
     */
    private Integer sourceType;

    /**
     * トレーニング資料種別名称
     */
    private String sourceTypeName;

    /**
     * トレーニング資料パス
     */
    private String sourcePath;

    /**
     * トレーニング資料画像
     */
    private String sourceDraw;

    /**
     * トレーニング資料タイトル
     */
    private String sourceTitle;

    /**
     * トレーニング資料紹介
     */
    private String sourceDesc;
}