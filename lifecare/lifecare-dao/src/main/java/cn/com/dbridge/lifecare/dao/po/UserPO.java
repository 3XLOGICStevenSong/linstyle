package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserPO implements Serializable {
    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 编号(客户编号、服务人员编号、后台用户编号)
     */
    private String userNumber;

    /**
     * 密码
     */
    private String password;

    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    private String realName;
    
    /**
     * 相片地址
     */
    private String img;

    /**
     * 昵称
     */
    private String wechatNickName;

    /**
     * 微信OPEN_ID
     */
    private String openId;

    /**
     * 微信UNION_ID
     */
    private String unionId;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 生日
     */
    private Date birthday;

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
     * 性别(0:男 1:女)
     */
    private Byte sex;

    /**
     * 状态(0:启用 1:停用)
     */
    private Byte userStatus;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 紧急联系人A
     */
    private String emergencyContactA;
    /**
     * 与联系人A的关系
     */
    private String  relationWithA;

    /**
     * 紧急联系人B
     */
    private String emergencyContactB;
    
    /**
     * 与联系人B的关系
     */
    private String  relationWithB;
    /**
     * 等级(客户、服务人员都有等级)
     */
    private String userLevel;

    /**
     * 背景(针对客户)
     */
    private String backInfo;
    
    /**
     * 服务类别等级
	 * 000:未设定
	 * 001:生活助理
	 * 010:老龄照护
	 * 100:临床护理
	 * 011:生活助理 + 老龄照护
	 * 101:生活助理 + 临床护理
	 * 110:老龄照护 + 临床护理
	 * 111:生活助理 + 老龄照护 + 临床护理
     */
    private String serviceCategoryLevel;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private Integer updateBy;

    /**
     * 字典表中的CODE编码
     */
    private String dictTypeCode;

    /**
     * 用户类型(1:客户 2:服务人员3:后台用户)
     */
    private String userType;
    
    /**
     * 制定照护方案(0:不可 1:可)
     */
    private Byte draftNursePlan;
    
    /**
     * 地址 详细的描述地址
     */
    private String userAddr;
    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;
}