package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebUserDTO
 * @Description:根据用户ID编辑用户管理信息DTO
 * @author: 王林江
 * @date:   2019年01月08日 10:13:18
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WebUserDTO implements Serializable {

    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private Integer userId;

    /**
     * 用户类型(1:客户 2:服务人员3:后台用户)
     */
    @ApiModelProperty(value = "用户类型(1:客户 2:服务人员3:后台用户)")
    @NotNull(message = "用户类型设定不正：用户类型(1:客户 2:服务人员3:后台用户)必须入力")
    @Range(min = 1, max = 3, message = "用户类型设定不正：用户类型只能设定为1:客户 2:服务人员3:后台用户")
    private Byte userType;

    /**
     * 编号(客户编号、服务人员编号、后台用户编号)
     */
    @ApiModelProperty(value = "编号(客户编号、服务人员编号、后台用户编号)")
    @Length(min = 0, max = 16, message = "编号设定不正：编号最大可输入16位")
    @Pattern(regexp = "^(([A-Za-z0-9]{1,})?)|(([ ]{1,})?)$", message = "编号设定不正：编号必须为大小字母或数字")
    private String userNumber;

    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    @ApiModelProperty(value = "名称(客户名称、服务人员名称、后台用户名称)")
    @NotBlank(message = "姓名设定不正：姓名必须入力")
    @Length(min = 0, max = 20, message = "姓名设定不正：姓名最大可输入20个字符")
    private String realName;

    /**
     * 相片地址
     */
    @ApiModelProperty(value = "相片地址")
    @Length(min = 0, max = 256, message = "相片地址设定不正：相片地址最大可输入256位")
    private String img;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @NotBlank(message = "手机号设定不正：手机号必须入力")
    @Length(min = 0, max = 11, message = "手机号设定不正：手机号最大可输入11位")
    @Pattern(regexp = "^(([0-9]{1,})?)|(([ ]{1,})?)$", message = "手机号设定不正：手机号必须为数字")
    private String mobile;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @NotNull(message = "生日设定不正：生日必须入力")
    private Date birthday;

    /**
     * 省编号
     */
    @ApiModelProperty(value = "省编号")
    @NotNull(message = "省编号设定不正：省编号必须入力")
    private Integer provinceId;

    /**
     * 市编号
     */
    @ApiModelProperty(value = "市编号")
    @NotNull(message = "市编号设定不正：市编号必须入力")
    private Integer cityId;

    /**
     * 区编号
     */
    @ApiModelProperty(value = "区编号")
    @NotNull(message = "区编号设定不正：区编号必须入力")
    private Integer districtId;

    /**
     * 街道小区
     */
    @ApiModelProperty(value = "街道小区")
    @NotBlank(message = "街道小区设定不正：街道小区必须入力")
    @Length(min = 0, max = 32, message = "街道小区设定不正：街道小区最大可输入32个字符")
    private String streetVillage;

    /**
     * 楼号单元号
     */
    @ApiModelProperty(value = "楼号单元号")
    @NotBlank(message = "楼号单元号设定不正：楼号单元号必须入力")
    @Length(min = 0, max = 32, message = "楼号单元号设定不正：楼号单元号最大可输入32个字符")
    private String buildingNo;

    /**
     * 性别(0:男 1:女)
     */
    @ApiModelProperty(value = "性别(0:男 1:女)")
    @NotNull(message = "性别设定不正：性别(0:男 1:女)必须入力")
    @Range(min = 0, max = 1, message = "性别设定不正：性别只能设定为0:男 1:女")
    private Byte sex;

    /**
     * 状态(0:启用 1:停用)
     */
    @ApiModelProperty(value = "状态(0:启用 1:停用)")
    @Range(min = 0, max = 1, message = "状态设定不正：状态只能设定为0:启用 1:停用")
    private Byte userStatus;

    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "身份证号码")
    @NotBlank(message = "身份证号设定不正：身份证号码必须入力")
    @Length(min = 0, max = 18, message = "身份证号设定不正：身份证号最大可输入18位")
    @Pattern(regexp = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)|(^([ ]{1,})?$)", message = "身份证号设定不正：身份证号格式不正")
    private String idCard;

    /**
     * 紧急联系人A
     */
    @ApiModelProperty(value = "紧急联系人A")
    @NotBlank(message = "紧急联系人A设定不正：紧急联系人A必须入力")
    @Length(min = 0, max = 32, message = "紧急联系人A设定不正：紧急联系人A最大可输入32个字符")
    private String emergencyContactA;
    
    /**
     * 与联系人A的关系
     */
    @ApiModelProperty(value = "与联系人A的关系")
    @Length(min = 0, max = 32, message = "与联系人A的关系设定不正：与联系人A的关系最大可输入32个字符")
    private String  relationWithA;

    /**
     * 紧急联系人B
     */
    @ApiModelProperty(value = "紧急联系人B")
    @Length(min = 0, max = 32, message = "紧急联系人B设定不正：紧急联系人B最大可输入32个字符")
    private String emergencyContactB;
    
    /**
     * 与联系人B的关系
     */
    @ApiModelProperty(value = "与联系人B的关系")
    @Length(min = 0, max = 32, message = "与联系人B的关系设定不正：与联系人B的关系最大可输入32个字符")
    private String  relationWithB;

    /**
     * 等级(客户、服务人员都有等级)
     */
    @ApiModelProperty(value = "等级(客户、服务人员都有等级)")
    @Length(min = 0, max = 16, message = "星级或等级设定不正：星级或等级最大可输入16位")
    private String userLevel;

    /**
     * 背景(针对客户)
     */
    @ApiModelProperty(value = "背景(针对客户)")
    @Length(min = 0, max = 500, message = "背景设定不正：背景最大可输入500个字符")
    private String backInfo;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Length(min = 0, max = 500, message = "备注设定不正：备注最大可输入500个字符")
    private String remarks;

    /**
     * 服务类别(A:生活助理 B:老龄照护 C:临床护理)
     */
    @ApiModelProperty(value = "服务类别(A:生活助理 B:老龄照护 C:临床护理)")
    private List<String> serviceCategoryLevel;

    /**
     * 制定照护方案(0:不可 1:可)
     */
    @ApiModelProperty(value = "制定照护方案(0:不可 1:可)")
    @Range(min = 0, max = 1, message = "制定照护方案设定不正：制定照护方案只能设定为0:不可 1:可")
    private Byte draftNursePlan;

    /**
     * 登录用户ID
     */
    @ApiModelProperty(value = "登录用户ID")
    @NotNull(message = "登录用户ID设定不正：登录用户ID必须入力")
    private Integer loginUserId;
    /**
     * 图片名称
     */
    @ApiModelProperty(value = "图片名称")
    public String imgName;
    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;
}