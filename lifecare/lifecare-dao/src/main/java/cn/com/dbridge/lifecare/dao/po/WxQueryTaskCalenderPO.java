package cn.com.dbridge.lifecare.dao.po;

import java.util.Date;

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
public class WxQueryTaskCalenderPO {
    /**
     * 订单日期
     */
    private Date orderDate;
    /**
     * 性别
     */
    private Byte sex;

    /**
     * 看看类型(0：任务池 1:我的任务)
     */
    private String type;

    /**
     * 登录用户ID
     */
    private Integer loginUserId;

}
