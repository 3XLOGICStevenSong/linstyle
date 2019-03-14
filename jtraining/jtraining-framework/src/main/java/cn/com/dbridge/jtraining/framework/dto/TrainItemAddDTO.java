package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TrainItemAddDTO implements Serializable{

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -6156041576531840146L;

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
    @Size(min = 1, max = 25, message = "単語数が範囲外です")
    @ApiModelProperty(value = "トレーニングタイトル")
    private String trainTitle;

    /**
     * トレーニング紹介
     */
    @NotEmpty(message = "{trainDesc.notnull}")
    @Size(min = 1, max = 255, message = "単語数が範囲外です")
    @ApiModelProperty(value = "トレーニング紹介")
    private String trainDesc;
    /**
     * トレーニング画像
     */
    @NotEmpty(message = "{trainDesc.notnull}")
    @ApiModelProperty(value = "トレーニング画像")
    private String trainDraw;
}