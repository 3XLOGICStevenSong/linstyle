package cn.com.dbridge.lifecare.dao.po;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName:NursePlanManageResPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月16日 上午10:21:57
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class NursePlanManageResPO {

    /**
     * 护理方案编号
     */
    private Integer nursePlanId;
    
    /**
     * 客户编号
     */
    private String userNumber;

    /**
     * 客户主键
     */
    private Integer customId;
    
    /**
     * 服务方案类型
     */
    private Byte nursePlanType;
    
    /**
     * 护理方案标题
     */
    private String nursePlanTitle;
    
    /**
     * 护理方案开始日期
     */
    private Date nursePlanBeginDate;

    /**
     * 后台人员主键
     */
    private Integer backendPersonId;

    /**
     * 制定日期
     */
    private Date draftDate;
    
    /**
     * 使用状态 0:启动 1：停用
     */
    private Byte useType;
}
