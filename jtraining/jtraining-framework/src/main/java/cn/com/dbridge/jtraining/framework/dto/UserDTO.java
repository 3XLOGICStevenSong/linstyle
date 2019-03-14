package cn.com.dbridge.jtraining.framework.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author Andy
 * @date 2018/8/30 10:34
 */
@Data
public class UserDTO{

    /**
     * 番号
     */

    @NotEmpty(message = "no.notEmpty")
    @ApiModelProperty(value = " 番号")
    private String no;

    /**
     * 名前
     */
    @Size(min = 1, max = 10, message = "name.oversize")
    @NotEmpty(message = "name.notEmpty")
    @ApiModelProperty(value = " 名前")
    private String name;

    /**
     * 性別
     */
    @Range(min = 0, max = 1, message = "sex.not.true")
    @NotNull(message = "sex.notnull")
    @ApiModelProperty(value = " 性別")
    private Byte sex;

    /**
     * エントリーの日付
     */
    @NotNull(message = "applyDate.notnull")
    @ApiModelProperty(value = " エントリーの日付")
    private Date applyDate;

    /**
     * 誕生日
     */
    @NotNull(message = "applyDate.birthday")
    @ApiModelProperty(value = " 誕生日")
    private Date birthday;
    
    /**
     * 職歴
     */
    @ApiModelProperty(value = " 職歴")
    private String vocational;
    
    /**
     * 備考
     */
    @ApiModelProperty(value = " 備考")
    private String remarks;
    
    /**
     * 紹介
     */
    @ApiModelProperty(value = "  紹介")
    private String introduction;

    /**
     * 类别(0:管理员 1：老师 2：学生)
     */
    @NotNull(message = "type.notnull")
    @ApiModelProperty(value = " 类别(0:管理员 1：老师 2：学生)")
    private Byte type;

    /**
     * トレーニング種別
     */
    @ApiModelProperty(value = " トレーニング種別")
    private Integer trainType;
    /**
     * パスワード
     */
    @ApiModelProperty(value = " パスワード")
    private String password;

    /**
     * 現在のページ数
     */
    @ApiModelProperty(value = " ページ番号")
    private Integer offset;

    /**
     * 1ページあたりの行数
     */
    @ApiModelProperty(value = " レコード数")
    private Integer limit;

    /**
     * 本人画像
     */
    @NotEmpty(message = "personDraw.notEmpty", groups = {
            ValidateGroupOne.class})
    @ApiModelProperty(value = " 本人画像")
    private String personDraw;

    /**
     * 有効フラグ(0:有効 1:無効)
     */
    private Byte useStatus;

}
