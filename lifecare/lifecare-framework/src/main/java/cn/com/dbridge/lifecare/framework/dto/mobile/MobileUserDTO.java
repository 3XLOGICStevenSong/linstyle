package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:UserDTO
 * @Description:用户信息DTO
 * @author:陈健飞
 * @date:2018年12月27日 下午12:58:08
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileUserDTO implements Serializable {
    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private Integer userId;

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
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    /**
     * 微信昵称
     */
    @ApiModelProperty(value = "微信昵称")
    private String wechatNickName;
    /**
     * 微信OPEN_ID
     */
    @ApiModelProperty(value = "微信OPEN_ID")
    private String openId;
    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;
}