package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName:MobileTaskPoolPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 上午10:37:29
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileTaskPoolPO implements Serializable {
    /**
     * 服务类别名称
     */
    private String name;

    /**
     * 服务类颜色
     */
    private String color;

    /**
     * 订单编号、任务编号
     */
    private String orderNo;

    /**
     * 性别(0:男 1:女)
     */
    private Byte sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 区名称
     */
    private String districtName;

    /**
     * 街道小区
     */
    private String streetVillage;

    /**
     * 预约开始时间
     */
    private Date orderBeginTime;

    /**
     * 预约结束时间
     */
    private Date orderEndTime;

    private static final long serialVersionUID = 1L;
}
