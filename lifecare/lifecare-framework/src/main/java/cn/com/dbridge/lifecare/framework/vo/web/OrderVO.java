package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @ClassName:  OrderVO
 * @Description:订单VO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:54:39
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
public class OrderVO implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 订单编号、任务编号
     */
    @ApiModelProperty(value = "订单编号、任务编号")
    private String orderNo;

    /**
     * 客户主键
     */
    @ApiModelProperty(value = "客户主键")
    private Integer customId;

    /**
     * 服务人员主键
     */
    @ApiModelProperty(value = "服务人员主键")
    private Integer servicePersonId;

    /**
     * 服务类别编码
     */
    @ApiModelProperty(value = "服务类别编码")
    private String serviceCategoryCode;

    /**
     * 性别要求(0:不限 1:男 2:女)
     */
    @ApiModelProperty(value = "性别要求(0:不限 1:男 2:女)")
    private Byte sexRequire;

    /**
     * 预约地址
     */
    @ApiModelProperty(value = "预约地址")
    private String orderAddr;

    /**
     * 预约日期
     */
    @ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDate;
    
    /**
     * 订单时长 
     */
    @ApiModelProperty(value = "订单时长")
    private String orderDuration;
    /**
     * 预约开始时间
     */
    @ApiModelProperty(value = "预约开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date orderBeginTime;

    /**
     * 预约结束时间
     */
    @ApiModelProperty(value = "预约结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date orderEndTime;

    /**
     * 服务内容
     */
    @ApiModelProperty(value = "服务内容")
    private String contenta;

    /**
     * 预约备注
     */
    @ApiModelProperty(value = "预约备注")
    private String remark;

    /**
     * 服务开始时间
     */
    @ApiModelProperty(value = "服务开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date serviceBeginTime;

    /**
     * 服务结束时间
     */
    @ApiModelProperty(value = "服务结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date serviceEndTime;

    /**
     * 服务时长
     */
    @ApiModelProperty(value = "服务时长")
    private String serviceDuration;

    /**
     * 服务描述
     */
    @ApiModelProperty(value = "服务描述")
    private String serviceDesc;

    /**
     * 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     */
    @ApiModelProperty(value = "订单状态(1：待分配 2:待完成 3.已完成 4.取消)")
    private Byte orderStatus;

    /**
     * 服务取消原因
     */
    @ApiModelProperty(value = "服务取消原因")
    private String cancelReson;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Integer updateBy;

    /**
     * t_order
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 订单编号、任务编号
     * @return order_no 订单编号、任务编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 订单编号、任务编号
     * @param orderNo 订单编号、任务编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 客户主键
     * @return custom_id 客户主键
     */
    public Integer getCustomId() {
        return customId;
    }

    /**
     * 客户主键
     * @param customId 客户主键
     */
    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    /**
     * 服务人员主键
     * @return service_person_id 服务人员主键
     */
    public Integer getServicePersonId() {
        return servicePersonId;
    }

    /**
     * 服务人员主键
     * @param servicePersonId 服务人员主键
     */
    public void setServicePersonId(Integer servicePersonId) {
        this.servicePersonId = servicePersonId;
    }

    /**
     * 服务类别编号
     * @return service_category_code 服务类别编号
     */
    public String getServiceCategoryCode() {
        return serviceCategoryCode;
    }

    /**
     * 服务类别编号
     * @param serviceCategoryId 服务类别编号
     */
    public void setServiceCategoryCode(String serviceCategoryCode) {
        this.serviceCategoryCode = serviceCategoryCode;
    }

    /**
     * 性别要求(0:不限 1:男 2:女)
     * @return sex_require 性别要求(0:不限 1:男 2:女)
     */
    public Byte getSexRequire() {
        return sexRequire;
    }

    /**
     * 性别要求(0:不限 1:男 2:女)
     * @param sexRequire 性别要求(0:不限 1:男 2:女)
     */
    public void setSexRequire(Byte sexRequire) {
        this.sexRequire = sexRequire;
    }

    /**
     * 预约地址
     * @return order_addr 预约地址
     */
    public String getOrderAddr() {
        return orderAddr;
    }

    /**
     * 预约地址
     * @param orderAddr 预约地址
     */
    public void setOrderAddr(String orderAddr) {
        this.orderAddr = orderAddr;
    }

    /**
     * 预约日期
     * @return order_date 预约日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 预约日期
     * @param orderDate 预约日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 预约开始时间
     * @return order_begin_time 预约开始时间
     */
    public Date getOrderBeginTime() {
        return orderBeginTime;
    }

    /**
     * 预约开始时间
     * @param orderBeginTime 预约开始时间
     */
    public void setOrderBeginTime(Date orderBeginTime) {
        this.orderBeginTime = orderBeginTime;
    }

    /**
     * 预约结束时间
     * @return order_end_time 预约结束时间
     */
    public Date getOrderEndTime() {
        return orderEndTime;
    }

    /**
     * 预约结束时间
     * @param orderEndTime 预约结束时间
     */
    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    /**
     * 服务内容
     * @return contenta 服务内容
     */
    public String getContenta() {
        return contenta;
    }

    /**
     * 服务内容
     * @param contenta 服务内容
     */
    public void setContenta(String contenta) {
        this.contenta = contenta;
    }

    /**
     * 预约备注
     * @return remark 预约备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 预约备注
     * @param remark 预约备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 服务开始时间
     * @return service_begin_time 服务开始时间
     */
    public Date getServiceBeginTime() {
        return serviceBeginTime;
    }

    /**
     * 服务开始时间
     * @param serviceBeginTime 服务开始时间
     */
    public void setServiceBeginTime(Date serviceBeginTime) {
        this.serviceBeginTime = serviceBeginTime;
    }

    /**
     * 服务结束时间
     * @return service_end_time 服务结束时间
     */
    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    /**
     * 服务结束时间
     * @param serviceEndTime 服务结束时间
     */
    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    /**
     * 服务时长
     * @return service_duration 服务时长
     */
    public String getServiceDuration() {
        return serviceDuration;
    }

    /**
     * 服务时长
     * @param serviceDuration 服务时长
     */
    public void setServiceDuration(String serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    /**
     * 服务描述
     * @return service_desc 服务描述
     */
    public String getServiceDesc() {
        return serviceDesc;
    }

    /**
     * 服务描述
     * @param serviceDesc 服务描述
     */
    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    /**
     * 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     * @return order_status 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     * @param orderStatus 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 服务取消原因
     * @return cancel_reson 服务取消原因
     */
    public String getCancelReson() {
        return cancelReson;
    }

    /**
     * 服务取消原因
     * @param cancelReson 服务取消原因
     */
    public void setCancelReson(String cancelReson) {
        this.cancelReson = cancelReson;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     * @return create_by 创建人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 创建人
     * @param createBy 创建人
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新人
     * @return update_by 更新人
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * 更新人
     * @param updateBy 更新人
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getOrderDuration() {
        return orderDuration;
    }

    public void setOrderDuration(String orderDuration) {
        this.orderDuration = orderDuration;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", customId=").append(customId);
        sb.append(", servicePersonId=").append(servicePersonId);
        sb.append(", serviceCategoryCode=").append(serviceCategoryCode);
        sb.append(", sexRequire=").append(sexRequire);
        sb.append(", orderAddr=").append(orderAddr);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", orderDuration=").append(orderDuration);
        sb.append(", orderBeginTime=").append(orderBeginTime);
        sb.append(", orderEndTime=").append(orderEndTime);
        sb.append(", contenta=").append(contenta);
        sb.append(", remark=").append(remark);
        sb.append(", serviceBeginTime=").append(serviceBeginTime);
        sb.append(", serviceEndTime=").append(serviceEndTime);
        sb.append(", serviceDuration=").append(serviceDuration);
        sb.append(", serviceDesc=").append(serviceDesc);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", cancelReson=").append(cancelReson);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}