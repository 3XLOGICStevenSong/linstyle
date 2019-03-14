package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @ClassName:  WebUserSelectPO
 * @Description:服务池选择服务人员PO
 * @author: 王林江
 * @date:   2019年01月05日 14:13:18
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *  注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WebUserSelectPO implements Serializable {
    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 订单主键
     */
    private Integer id;

    /**
     * 预约日期
     */
    private Date orderDate;

    /**
     * 预约时间Begin
     */
    private Date orderBeginTime;

    /**
       * 预约时间End
     */
    private Date orderEndTime;

    /**
     * 登录用户ID
     */
    private Integer loginUserId;
    /**
     * 任务ID
     */
    private Integer taskId;

    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;

}