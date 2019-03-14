package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName:TaskManageResPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月16日 下午2:56:00
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class TaskManageResPO implements Serializable{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -6157005334284926178L;
    /**
     * 订单主键
     */
    private Integer id;
    /**
     * 任务编号
     */
    private String orderNo;
    /**
     * 服务类别
     */
    private String serviceCategoryName;
    /**
     * 预约日期
     */
    private Date orderDate;
    /**
     * 预约开始时间
     */
    private Date orderBeginTime;
    /**
     * 服务时长
     */
    private Integer orderDuration;
    /**
     * 实际时长
     */
    private Integer serviceDuration;
    /**
     * 客户编号
     */
    private Integer customId;
    /**
     * 服务人员编号
     */
    private Integer servicePersonId;
    /**
     * 客户姓名
     */
    private String customRealName;
    /**
     * 服务人员姓名
     */
    private String servicePersonRealName;
    /**
     * 订单状态
     */
    private Byte orderStatus;
    /**
     * 服务实际开始时间
     */
    private Date serviceBeginTime;
}
