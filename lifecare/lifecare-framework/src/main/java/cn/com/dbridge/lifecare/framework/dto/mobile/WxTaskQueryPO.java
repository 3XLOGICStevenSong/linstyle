package cn.com.dbridge.lifecare.framework.dto.mobile;

import lombok.Data;

/**
 * 
 * @ClassName:  WxTaskQueryPO
 * @Description:微信查询任务PO
 * @author: 陈健飞
 * @date:   2019年1月8日 上午11:05:44
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WxTaskQueryPO {
    /**
     * 任务ID
     */
    private String taskId;
    /**
     * 服务类别名称
     */
    private String serviceCategoryName;
    /**
     * 服务类别颜色
     */
    private String serviceCategoryColor;
    /**
     * 任务编号
     */
    private String taskNo;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private String age;
    /**
     * 订单日期
     */
    private String orderDate;
    /**
     * 订单开始时间
     */
    private String orderBeginTime;
    /**
     * 订单结束时间
     */
    private String orderEndTime;
    /**
     * 街区
     */
    private String streetVillage;
    /**
     * 行政区名称
     */
    private String districtName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 订单状态
     */
    private String orderStatus;
    
    //-----------------查询条件-------------------
    /**
     * 服务人员ID 
     */
    private Integer servicePersonId;
    /**
     * 客户ID
     */
    private Integer customId;
    /**
     * 头像地址
     */
    private String img;
    
}
