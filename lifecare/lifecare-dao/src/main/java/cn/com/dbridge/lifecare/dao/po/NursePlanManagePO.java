package cn.com.dbridge.lifecare.dao.po;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName:NursePlanManagePO
 * @Description:护理计划PO
 * @author:郭健
 * @date:2019年1月9日 下午1:18:58
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class NursePlanManagePO {
    /**
     * 客户编号
     */
    private String userNumber;
    /**
     *客户身份证号
     */
    private String idCard;
    /**
     * 客户名称
     */
    private String realName;
    /**
     * 客户手机号
     */
    private String mobile;
    /**
     *  客户生日
     */
    private Date birthday;
    /**
     * 客户星级
     */
    private String userLevel;

    /**
     * 方案状态(0:草稿 1:发布)
     */
    private Byte nursePlanStatus;
}
