package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:MobileServicePersonInfoVO
 * @Description:服务人员信息VO
 * @author:陈健飞
 * @date:2018年12月27日 下午12:55:42
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class MobileServicePersonInfoVO implements Serializable {
    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 相片地址
     */
    @ApiModelProperty(value = "相片地址")
    private String img;

    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    @ApiModelProperty(value = "名称(客户名称、服务人员名称、后台用户名称)")
    private String realName;

    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "身份证号码")
    private String idCard;

    /**
     * 编号(客户编号、服务人员编号、后台用户编号)
     */
    @ApiModelProperty(value = "编号(客户编号、服务人员编号、后台用户编号)")
    private String userNumber;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobile;

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
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号")
    private String wechatId;

    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;
}