package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * 
 * @ClassName:  TaskPO
 * @Description:任务信息PO
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:16:21
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class TaskPO implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 订单编号、任务编号
     */
    private String orderNo;

    /**
     * 客户主键
     */
    private Integer customId;

    /**
     * 服务人员主键
     */
    private Integer servicePersonId;

    /**
     * 服务类别编码
     */
    private String serviceCategoryCode;

    /**
     * 性别要求(0:不限 1:男 2:女)
     */
    private Byte sexRequire;
    /**
     * 所属区
     */
    private Integer districtId;
    /**
     * 所属街区
     */
    private Integer streetId;
    /**
     * 预约地址
     */
    private String orderAddr;

    /**
     * 预约日期
     */
    private Date orderDate;

    /**
     * 订单时长 
     */
    private Integer orderDuration;
    
    /**
     * 预约开始时间
     */
    private Date orderBeginTime;

    /**
     * 预约结束时间
     */
    private Date orderEndTime;

    /**
     * 服务内容
     */
    private String content;

    /**
     * 预约备注
     */
    private String remark;

    /**
     * 服务开始时间
     */
    private Date serviceBeginTime;

    /**
     * 服务结束时间
     */
    private Date serviceEndTime;

    /**
     * 服务时长
     */
    private Integer serviceDuration;

    /**
     * 服务描述
     */
    private String serviceDesc;

    /**
     * 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     */
    private Byte orderStatus;

    /**
     * 服务取消原因
     */
    private String cancelReson;

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
     * t_order
     */
    private static final long serialVersionUID = 1L;
}