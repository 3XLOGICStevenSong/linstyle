package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class TrainAddDTO implements Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -1849199734641945511L;
    /**
     * トレーニングID
     */
    @NotNull(message = "{trainId.notnull}")
    @ApiModelProperty(value = "トレーニングID")
    private Integer trainId;

    /**
     * トレーニング資料種別
     */
    @NotNull(message = "{sourceType.notnull}")
    @ApiModelProperty(value = "トレーニング資料種別")
    private Integer sourceType;

    /**
     * トレーニング資料パス
     */
    @NotEmpty(message = "{sourcePath.notnull}")
    @ApiModelProperty(value = "トレーニング資料パス")
    private String sourcePath;

    /**
     * トレーニング資料規模(ビデオ:秒 テキスト:頁)
     */
    @NotNull(message = "{sourceLength.notnull}")
    @ApiModelProperty(value = "トレーニング資料規模(ビデオ:秒 テキスト:頁)")
    private Integer sourceLength;

    /**
     * トレーニング資料画像
     */
    @NotEmpty(message = "{sourceDraw.notnull}")
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
    @ApiModelProperty(value = "トレーニング資料紹介")
    private String sourceDesc;
}