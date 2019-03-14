package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:  WxCalendarTask
 * @Description:微信日历任务DTO
 * @author: 陈健飞
 * @date:   2019年1月8日 上午11:05:44
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WxCalendarTaskDTO {
    /**
     *  预约日期
     */
    @ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM")
    @NotNull(message = "预约日期设定不正：预约日期必须入力")
    private Date orderDate;
    /**
     * 性别(0:男 1:女)
     */
    @ApiModelProperty(value = "性别(0:男 1:女)")
    private byte sex;
    /**
     * 查看类型(0：任务池 1:我的任务)
     */
    @ApiModelProperty(value = "查看类型(0：任务池 1:我的任务)")
    @NotBlank(message = "看看类型设定不正：查看类型(0：任务池 1:我的任务)必须入力")
    @Pattern(regexp = "^(([0-1]{1})?)|(([ ]{1,})?)$", message = "查看类型设定不正：查看类型只能设定为0：任务池 1:我的任务")
    private String type;
    
    /**
     * 登录用户ID
     */
    @ApiModelProperty(value = "登录用户ID")
    private Integer loginUserId;
}
