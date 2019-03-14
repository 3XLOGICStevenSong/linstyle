package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TrainCategoryUpdateDTO implements Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 7721826924381284708L;

    /**
     * トレーニング種別
     */
    @NotNull(message = "{trainType.notnull}")
    @ApiModelProperty(value = "トレーニング種別")
    private Integer trainType;

    /**
     * 種別名称
     */
    @NotEmpty(message = "{typeName.notnull}")
    @Size(min = 1, max = 10, message = "パラメータが範囲外")
    @ApiModelProperty(value = "種別名称")
    private String typeName;

}