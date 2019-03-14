package cn.com.dbridge.jtraining.framework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserUpdatePasswordDTO {
    /**
     * 番号
     */
    @ApiModelProperty(value = " 番号")
    private String no;

    /**
     * パスワード
     */
    @ApiModelProperty(value = " パスワード")
    private String password;

}
