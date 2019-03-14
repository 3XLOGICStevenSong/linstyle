package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TrainItemUpdateDTO implements Serializable{

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 8792787285622397069L;

    /**
     * トレーニングID
     */
    @NotNull(message = "{trainId.notnull}")
    @ApiModelProperty(value = "トレーニングID")
    private Integer trainId;
    /**
     * トレーニング種別
     */
    @NotNull(message = "{trainType.notnull}")
    @ApiModelProperty(value = "トレーニング種別")
    private Integer trainType;

    /**
     * トレーニング教师
     */
    @NotEmpty(message = "{trainTeacher.notnull}")
    @ApiModelProperty(value = "トレーニング教师")
    private String trainTeacher;

    /**
     * トレーニングタイトル
     */
    @NotEmpty(message = "{trainTitle.notnull}")
    @ApiModelProperty(value = "トレーニングタイトル")
    private String trainTitle;

    /**
     * トレーニング紹介
     */
    @NotEmpty(message = "{trainDesc.notnull}")
    @ApiModelProperty(value = "トレーニング紹介")
    private String trainDesc;
    /**
     * トレーニング画像
     */
    @ApiModelProperty(value = "トレーニング画像")
    private String trainDraw;
}