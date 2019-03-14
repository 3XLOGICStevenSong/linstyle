package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebUserVO
 * @Description:用户管理信息VO
 * @author: 王林江
 * @date:   2019年01月08日 09:13:18
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
@Data
public class WebUserVO implements Serializable {

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
     * 与紧急联系人A的关系
     */
    private String relationWithA;

    /**
     * 紧急联系人B
     */
    @ApiModelProperty(value = "紧急联系人B")
    private String emergencyContactB;
    
    /**
     * 与紧急联系人B的关系
     */
    private String relationWithB;

    /**
     * 服务类别(A:生活助理 B:老龄照护 C:临床护理)
     */
    @ApiModelProperty(value = "服务类别(A:生活助理 B:老龄照护 C:临床护理)")
    private List<String> serviceCategoryLevel;

    /**
     * 制定照护方案(0:不可 1:可)
     */
    @ApiModelProperty(value = "制定照护方案(0:不可 1:可)")
    private Byte draftNursePlan;

    /**
     * 等级(客户、服务人员都有等级)
     */
    @ApiModelProperty(value = "等级(客户、服务人员都有等级)")
    private String userLevel;

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
     * t_user
     */
    private static final long serialVersionUID = 1L;

}