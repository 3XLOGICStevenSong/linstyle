package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.util.Date;

import lombok.Data;

/**
 * 
 * @ClassName:  WxTaskQueryDetail
 * @Description:微信查询任务详情
 * @author: 陈健飞
 * @date:   2019年1月8日 上午11:05:44
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WxTaskQueryDetailDTO {
    /**
     *  订单主键
     */
    private Integer taskId;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 地址
     */
    private String orderAddr;
    /**
     * 服务内容
     */
    private String content;
    /**
     * 合计服务时长
     */
    private String orderDuration;
    /**
     * 服务日期
     */
    private Date orderDate;
    /**
     * 时间Begin
     */
    private Date orderBeginTime;
    /**
     * 时间End
     */
    private Date orderEndTime;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 任务编号
     */
    private String orderNo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 客户ID
     */
    private Integer customId;
    
    /**
     * 服务人员ID
     */
    private Integer servicePersonId;
    /**
     * 查看类型(0：任务池 1:我的任务)
     */
    private String type;
    /**
     * 服务类别名称
     */
    private String serviceCategoryName;
    /**
     * 备注
     */
    private String remark;
}
