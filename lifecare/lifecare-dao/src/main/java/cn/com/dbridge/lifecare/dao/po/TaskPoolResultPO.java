package cn.com.dbridge.lifecare.dao.po;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName:TaskPoolResultPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月14日 下午2:44:42
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class TaskPoolResultPO {
    /**
     * 描述
     */
    private String description;
    /**
     * 客户主键
     */
    private Integer customId;
    /**
     * 预约地址
     */
    private String orderAddr;
    /**
     * 预约日期
     */
    private Date orderDate;
    /**
     * 预约开始时间
     */
    private Date orderBeginTime;
    /**
     * 预约结束时间
     */
    private Date orderEndTime;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 客户性别(0:男 1:女)
     */
    private Byte sex;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 订单编号、任务编号
     */
    private String orderNo;
    /**
     * 服务类别编码
     */
    private String serviceCategoryCode;

    /**
     * 性别要求(0:不限 1:男 2:女)
     */
    private Byte sexRequire;
}
