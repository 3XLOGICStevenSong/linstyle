package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class UserUpdateDTO implements Serializable {
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
    private Date birthday;

    /**
     * エントリーの日付
     */
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
     * 備考
     */
    private String remarks;

    /**
     * 類別(0:管理员 1：老师 2：学生)
     */
    private Byte type;

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
     * t_user
     */
    private static final long serialVersionUID = 1L;
}