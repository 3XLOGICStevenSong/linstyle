package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebUserSelectVO
 * @Description:获取选择用户管理信息VO
 * @author: 王林江
 * @date:   2019年01月07日 13:13:18
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
@Data
public class WebUserSelectVO implements Serializable {
    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private Integer userId;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String userNumber;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String realName;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 星级或者等级
     */
    @ApiModelProperty(value = "星级或者等级")
    private String userLevel;

    /**
     * 省名称
     */
    @ApiModelProperty(value = "省名称")
    private String provinceName;

    /**
     * 市名称
     */
    @ApiModelProperty(value = "市名称")
    private String cityName;

    /**
     * 区名称
     */
    @ApiModelProperty(value = "区名称")
    private String districtName;

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
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;

}