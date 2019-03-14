package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName:MobileMyInfoPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月5日 上午9:52:23
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileMyInfoPO implements Serializable {
    /**
     * 用户ID
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
     * 身份证号码
     */
    private String idCard;

    /**
     * 编号(客户编号、服务人员编号、后台用户编号)
     */
    private String userNumber;

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
     * 省名称
     */
    private String provinceName;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区名称
     */
    private String districtName;

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
    private String email;

    /**
     * 微信昵称
     */
    private String wechatNickName;

    /**
     * 微信号wechatId
     */
    private String openId;

    /**
     * 用户类型(1:客户 2:服务人员3:后台用户)
     */
    private Byte userType;

    private static final long serialVersionUID = 1L;
}
