package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  UserDTO
 * @Description:用户信息DTO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:58:08
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class UserDTO implements Serializable {
    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private Integer userId;

    /**
     * 编号(客户编号、服务人员编号、后台用户编号)
     */
    @ApiModelProperty(value = "编号(客户编号、服务人员编号、后台用户编号)")
    private String userNumber;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    @ApiModelProperty(value = "名称(客户名称、服务人员名称、后台用户名称)")
    private String realName;

    /**
     * 相片地址
     */
    @ApiModelProperty(value = "相片地址")
    private String img;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "微信昵称")
    private String wechatNickName;

    /**
     * 微信OPEN_ID
     */
    @ApiModelProperty(value = "微信OpenId")
    private String openId;
    /**
     * 微信UNION_ID
     */
    @ApiModelProperty(value = "微信UnionId")
    private String unionId;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 省编号
     */
    @ApiModelProperty(value = "省编号")
    private Integer provinceId;

    /**
     * 市编号
     */
    @ApiModelProperty(value = "市编号")
    private Integer cityId;

    /**
     * 区编号
     */
    @ApiModelProperty(value = "区编号")
    private Integer districtId;

    /**
     * 街道小区
     */
    @ApiModelProperty(value = "街道小区")
    private String streetVillage;

    /**
     * 楼号单元号
     */
    @ApiModelProperty(value = "楼号单元号")
    private String buildingNo;

    /**
     * 性别(0:男 1:女)
     */
    @ApiModelProperty(value = "性别(0:男 1:女)")
    private Byte sex;

    /**
     * 状态(0:启用 1:停用)
     */
    @ApiModelProperty(value = "状态(0:启用 1:停用)")
    private Byte userStatus;

    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "身份证号码")
    private String idCard;

    /**
     * 紧急联系人A
     */
    @ApiModelProperty(value = "紧急联系人A")
    private String emergencyContactA;

    /**
     * 紧急联系人B
     */
    @ApiModelProperty(value = "紧急联系人B")
    private String emergencyContactB;

    /**
     * 等级(客户、服务人员都有等级)
     */
    @ApiModelProperty(value = "等级(客户、服务人员都有等级)")
    private String level;

    /**
     * 背景(针对客户)
     */
    @ApiModelProperty(value = "背景(针对客户)")
    private String backInfo;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Integer updateBy;
    /**
     * 用户类型(1:客户 2:服务人员3:后台用户)
     */
    @ApiModelProperty(value = "用户类别(1:客户 2:服务人员3:后台用户)")
    private String userType;
    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;
}