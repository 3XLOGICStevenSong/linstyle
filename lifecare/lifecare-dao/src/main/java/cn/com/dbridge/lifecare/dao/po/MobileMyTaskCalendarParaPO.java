package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName:MobileMyTaskCalendarParaPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 上午11:45:39
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileMyTaskCalendarParaPO implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 预约日期
     */
    private Date orderDate;

    /**
     *  查看类型(0：任务池 1:我的任务)
     */
    private Byte type;

    private static final long serialVersionUID = 1L;
}