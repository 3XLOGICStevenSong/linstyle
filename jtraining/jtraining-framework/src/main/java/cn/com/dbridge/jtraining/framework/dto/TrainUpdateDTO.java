package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class TrainUpdateDTO implements Serializable {
    private static final long serialVersionUID = -8519228262517473620L;

    /**
     * トレーニング資料番号
     */
    @NotNull(message = "{sourceNo.notnull}")
    @ApiModelProperty(value = "トレーニング資料番号")
    private Integer sourceNo;

    /**
     * トレーニング資料種別
     */
    @NotNull(message = "{sourceType.notnull}")
    @ApiModelProperty(value = "トレーニング資料種別")
    private Integer sourceType;

    /**
     * トレーニング資料画像
     */
    @ApiModelProperty(value = "トレーニング資料画像")
    private String sourceDraw;

    /**
     * トレーニング資料タイトル
     */
    @NotEmpty(message = "{sourceTitle.notnull}")
    @Size(min = 1, max = 25, message = "単語数が範囲外です")
    @ApiModelProperty(value = "トレーニング資料タイトル")
    private String sourceTitle;

    /**
     * トレーニング資料紹介
     */
    @NotEmpty(message = "{sourceDesc.notnull}")
    @Size(min = 1, max = 255, message = "単語数が範囲外です")
    @ApiModelProperty(value = "トレーニング資料タイトル")
    private String sourceDesc;
}