package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebUserManageDTO
 * @Description:用户管理页面用户信息查询DTO
 * @author: 王林江
 * @date:   2019年01月07日 11:13:18
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WebUserManageDTO implements Serializable {

    /**
     *用户编号
     */
    @ApiModelProperty(value = "用户编号")
    @Length(min = 0, max = 16, message = "编号设定不正：编号最大可输入16位")
    private String userNumber;

    /**
     * 用户身份证号
     */
    @ApiModelProperty(value = "用户身份证号")
    @Length(min = 0, max = 18, message = "身份证号设定不正：身份证号最大可输入18位")
    private String idCard;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    @Length(min = 0, max = 8, message = "姓名设定不正：姓名最大可输入8个字符")
    private String realName;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    @Length(min = 0, max = 11, message = "手机号设定不正：手机号最大可输入11位")
    @Pattern(regexp = "^([0-9]{1,})?$", message = "手机号设定不正：手机号必须为数字")
    private String mobile;

    /**
     * 用户生日
     */
    @ApiModelProperty(value = "用户生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 等级(客户、服务人员都有等级)
     */
    @ApiModelProperty(value = "等级(客户、服务人员都有等级)")
    @Length(min = 0, max = 16, message = "星级或等级设定不正：星级或等级最大可输入16位")
    private String userLevel;

    /**
     * 用户类型(1:客户 2:服务人员3:后台用户)
     */
    @ApiModelProperty(value = "用户类型(1:客户 2:服务人员3:后台用户)")
    @NotNull(message = "用户类型设定不正：用户类型(1:客户 2:服务人员3:后台用户)必须入力")
    @Range(min = 1, max = 3, message = "用户类型设定不正：用户类型只能设定为1:客户 2:服务人员3:后台用户")
    private Byte userType;

    /**
     * 状态(0:启用 1:停用)
     */
    @ApiModelProperty(value = "状态(0:启用 1:停用)")
    @Range(min = 0, max = 1, message = "状态设定不正：状态只能设定为0:启用 1:停用")
    private Byte userStatus;

    /**
     * 性别要求(0:不限 1:男 2:女)
     */
    @ApiModelProperty(value = "性别要求(0:不限 1:男 2:女)")
    @Range(min = 0, max = 2, message = "性别要求设定不正：性别要求只能设定为0:不限 1:男 2:女")
    private Byte sexRequire;

    /**
     * 制定照护方案(0:不可 1:可)
     */
    @ApiModelProperty(value = "制定照护方案(0:不可 1:可)")
    @Range(min = 0, max = 1, message = "制定照护方案设定不正：制定照护方案只能设定为0:不可 1:可")
    private Byte draftNursePlan;

    /**
     * 服务类别编码(A:生活助理 B:老龄照护 C:临床护理)
     */
    @ApiModelProperty(value = "服务类别编码(A:生活助理 B:老龄照护 C:临床护理)")
    @Pattern(regexp = "^[A-C]{1}$", message = "服务类别编码设定不正：服务类别编码只能设定为A:生活助理 B:老龄照护 C:临床护理")
    private String serviceCategoryCode;

    /**
     * 预约日期
     */
    @ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDate;

    /**
     * 预约时间Begin
     */
    @ApiModelProperty(value = "预约时间Begin")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTime;

    /**
       * 预约时间End
     */
    @ApiModelProperty(value = "预约时间End")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderEndTime;

    /**
     * 订单主键
     */
    @ApiModelProperty(value = "订单主键")
    private Integer id;

    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数")
    private Integer offset;

    /**
     * 每页显示数
     */
    @ApiModelProperty(value = "每页显示数")
    private Integer limit;

    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;
}