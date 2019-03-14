package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName:MobileMyTaskCalendarResPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 下午1:21:26
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileMyTaskCalendarResPO implements Serializable {

    private Date orderDate;

    private String orderId;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}
