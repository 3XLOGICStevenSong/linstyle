package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserAddDTO implements Serializable {
    /**
     * t_user
     */
    private static final long serialVersionUID = 6311825164275870213L;

    /**
     * 番号
     */
    private String no;

    /**
     * パスワード
     */
    private String password;

    /**
     * 名前
     */
    private String name;

    /**
     * トレーニング種別
     */
    private Integer trainType;

    /**
     * 年齢
     */
    private Integer age;

    /**
     * 性別
     */
    private Byte sex;

    /**
     * 誕生日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * エントリーの日付
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

    /**
     * 職歴
     */
    private String vocational;

    /**
     * 本人画像
     */
    private String personDraw;

    /**
     * 紹介
     */
    private String introduction;

    /**
     * 類別(0:管理员 1：老师 2：学生)
     */
    private Byte type;



    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;



    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**

     * 有効フラグ(0:有効 1:無効)
     */
    private Byte useStatus;

    /**
     * 作成日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp insertDate;

    /**
     * 作成人
     */
    private String insertPerson;

    /**
     * 更新日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

    /**
     * 更新人
     */
    private String updatePerson;

}