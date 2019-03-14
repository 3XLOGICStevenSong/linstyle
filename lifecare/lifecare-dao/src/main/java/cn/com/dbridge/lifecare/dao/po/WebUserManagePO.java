package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @ClassName:  WebUserManagePO
 * @Description:用户管理页面用户信息查询PO
 * @author: 王林江
 * @date:   2019年01月05日 13:13:18
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *  注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WebUserManagePO implements Serializable {
    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 编号(客户编号、服务人员编号、后台用户编号)
     */
    private String userNumber;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    private String realName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 用户类型(1:客户 2:服务人员3:后台用户)
     */
    private Byte userType;

    /**
     * 地址
     */
    private String userAddr;

    /**
     * 性别(0:男 1:女)
     */
    private Byte sex;

    /**
     * 等级(客户、服务人员都有等级)
     */
    private String userLevel;

    /**
     * 状态(0:启用 1:停用)
     */
    private Byte userStatus;

    /**
     * 制定照护方案(0:不可 1:可)
     */
    private Byte draftNursePlan;

    /**
     * 服务类别(000:未设定 001:生活助理 010:老龄照护 100:临床护理 011:生活助理 + 老龄照护 101:生活助理 + 临床护理 110:老龄照护 + 临床护理 111:生活助理 + 老龄照护 + 临床护理)
     */
    private String serviceCategoryLevel;

    /**
     * 服务类别编码(A:生活助理 B:老龄照护 C:临床护理)
     */
    private String serviceCategoryCode;

    /**
     * 预约日期
     */
    private Date orderDate;

    /**
     * 预约时间Begin
     */
    private Date orderBeginTime;

    /**
       * 预约时间End
     */
    private Date orderEndTime;

    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;

}