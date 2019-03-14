package cn.com.dbridge.lifecare.dao.po;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName:UnassignedTaskPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月7日 上午11:54:22
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UnassignedTaskPO extends TaskPO {
    private static final long serialVersionUID = 1L;
    /**
     * 服务类别
     */
    private List<String> serviceCategoryCodes;
    /**
     *  客户编号
     */
    private String userNumber;
    /**
     * 预约日期From
     */
    private Date orderDateFrom;
    /**
     * 预约日期To
     */
    private Date orderDateTo;
    /**
     * 客户姓名
     */
    private String realName;
    /**
     * 预约时间From
     */
    private Date orderBeginTimeFrom;
    /**
     * 预约时间To
     */
    private Date orderBeginTimeTo;
    /**
     * 客户生日
     */
    private Date birthday;
    /**
     * 服务时长From
     */
    private Integer orderDurationFrom;
    /**
     * 服务时长To
     */
    private Integer orderDurationTo;
    /**
     * 客户手机号
     */
    private String mobile;
    /**
     * 客户身份证号
     */
    private String idCard;
}
