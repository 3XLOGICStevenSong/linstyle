package cn.com.dbridge.lifecare.framework.dto.web;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:NursePlanManageDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月9日 上午11:58:00
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class NursePlanManageDTO {
    /**
     * 客户编号
     */
    @ApiModelProperty(value = "客户编号")
    @Length(min = 0, max = 8, message = "客户编号长度不能大于8位")
    private String userNumber;
    /**
     * 客户星级
     */
    @ApiModelProperty(value = "客户星级")
    private String userLevel;
    /**
     *客户身份证号
     */
    @ApiModelProperty(value = "客户身份证号")
    @Length(min = 0, max = 18, message = "客户身份证号长度必须为18位")
    private String idCard;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    @Length(min = 0, max = 8, message = "客户姓名长度不能大于8字")
    private String realName;
    /**
     * 方案状态(0:草稿 1:发布)
     */
    @ApiModelProperty(value = "方案状态(0:草稿 1:发布)")
    private Byte nursePlanStatus;
    /**
     * 客户手机号
     */
    @ApiModelProperty(value = "客户手机号")
    @Length(min = 0, max = 11, message = "客户手机号长度必须为11位")
    @Pattern(regexp = "^(([0-9]{1,})?)|(([ ]{1,})?)$", message = "客户手机号必须为数字")
    private String mobile;
    /**
     *  客户生日
     */
    @ApiModelProperty(value = "客户生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;
    
    private Integer offset;
    private Integer limit;
}
