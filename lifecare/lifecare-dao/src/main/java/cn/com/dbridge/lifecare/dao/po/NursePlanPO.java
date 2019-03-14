package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * 
 * @ClassName:  NursePlanPO
 * @Description:护理计划PO
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:13:41
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class NursePlanPO implements Serializable {
    /**
     * 护理方案编号
     */
    private Integer nursePlanId;

    /**
     * 客户主键
     */
    private Integer customId;

    /**
     * 后台人员主键
     */
    private Integer backendPersonId;
    
    /**
     * 护理方案 1:生活助理 2:老龄照护 3:临床护理
     */
    private Byte nursePlanType;
    
    /**
     * 护理方案内容
     */
    private String nursePlanContent;

    /**
     * 护理方案标题
     */
    private String nursePlanTitle;

    /**
     * 护理方案开始日期
     */
    private Date nursePlanBeginDate;

    /**
     * 方案状态(0:草稿 1:发布)
     */
    private Byte nursePlanStatus;

    /**
     * 制定日期
     */
    private Date draftDate;

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
     * 使用状态 0:启动 1：停用
     */
    private Byte useType;

    /**
     * t_nurse_plan
     */
    private static final long serialVersionUID = 1L;
}