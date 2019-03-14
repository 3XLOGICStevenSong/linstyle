package cn.com.dbridge.lifecare.dao.po;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @ClassName:TaskManagePO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月16日 下午2:53:07
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class TaskManagePO {
    /**
     * 任务编号
     */
    private String orderNo;
    /**
     * 服务类别
     */
    private List<String> serviceCategoryCodes;
    /**
     * 客户编号
     */
    private String customUserNumber;
    /**
     * 服务人员工号
     */
    private String servicePersonUserNumber;
    /**
     * 客户姓名
     */
    private String customRealName;
    /**
     * 服务人员姓名
     */
    private String servicePersonRealName;
    /**
     * 客户生日
     */
    private Date customBirthday;
    /**
     * 服务人员生日
     */
    private Date servicePersonBirthday;
    /**
     * 客户身份证号
     */
    private String customIdCard;
    /**
     * 服务人员身份证号
     */
    private String servicePersonIdCard;
    /**
     * 客户手机号
     */
    private String customMobile;
    /**
     * 服务人员手机号
     */
    private String servicePersonMobile;
    /**
     * 任务状态
     */
    private List<Byte> orderStatus;
    /**
     * 预约日期From
     */
    private Date orderDateFrom;
    /**
     * 预约日期To
     */
    private Date orderDateTo;
    /**
     * 服务时长From
     */
    private Integer orderDurationFrom;
    /**
     * 服务时长To
     */
    private Integer orderDurationTo;
    /**
     * 预约时间From
     */
    private Date orderBeginTimeFrom;
    /**
     * 预约时间To
     */
    private Date orderBeginTimeTo;
    /**
     * 异常状态任务(0:否 1:是 2:全选或全不选)
     */
    private List<Byte> abnormalOrder;
}
