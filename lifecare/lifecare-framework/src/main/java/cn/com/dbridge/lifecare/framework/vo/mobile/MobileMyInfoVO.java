package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileMyInfoVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月5日 上午9:17:12
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class MobileMyInfoVO {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 头像地址
     */
    @ApiModelProperty(value = "照片地址")
    private String img;

    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    @ApiModelProperty(value = "服务人员姓名")
    private String realName;

    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "服务人员身份证号码")
    private String idCard;

    /**
     * 编号(客户编号、服务人员编号、后台用户编号)
     */
    @ApiModelProperty(value = "至亲久久员工工号")
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
    @ApiModelProperty(value = "电话")
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
    private String streetVillage;

    /**
     * 楼号单元号
     */
    private String buildingNo;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    /**
     * 微信昵称
     */
    @ApiModelProperty(value = "微信昵称")
    private String nickName;

    /**
     * 微信OpenId
     */
    @ApiModelProperty(value = "微信OpenId")
    private String openId;
    
    /**
     * 微信UnionId
     */
    @ApiModelProperty(value = "微信UnionId")
    private String unionId;
}
