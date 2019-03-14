package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName:MobileWaitingServiceResPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 下午3:08:53
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileWaitingServiceResPO implements Serializable {
    /**
     * 客户主键
     */
    private Integer customId;

    /**
     * 服务类别名称
     */
    private String name;

    /**
     * 服务类颜色
     */
    private String color;
    /**
     * 名称(客户名称、服务人员名称、后台用户名称)
     */
    private String realName;

    /**
     * 预约开始时间
     */
    private Date orderBeginTime;

    /**
     * 预约结束时间
     */
    private Date orderEndTime;

    /**
     * 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     */
    private Byte orderStatus;

    private static final long serialVersionUID = 1L;
}
