package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName:MobileCustomerInfoVO
 * @Description:客户信息VO
 * @author:郭健
 * @date:2018年12月29日 上午11:46:49
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileCustomerInfoVO {
    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 相片地址
     */
    private String img;

    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    private String realName;

    /**
     * 性别(0:男 1:女)
     */
    private Byte sex;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 省编号
     */
    private Integer provinceId;

    /**
     * 市编号
     */
    private Integer cityId;

    /**
     * 区编号
     */
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
     * 备注
     */
    private String remarks;

    /**
     * 背景(针对客户)
     */
    private String backInfo;

    /**
     * 编号(客户编号、服务人员编号、后台用户编号)
     */
    private String userNumber;

}
