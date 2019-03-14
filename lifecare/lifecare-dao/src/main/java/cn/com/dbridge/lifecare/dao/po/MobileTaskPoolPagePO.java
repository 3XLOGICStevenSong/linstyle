package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName:MobileTaskPoolPagePO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 上午10:37:14
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileTaskPoolPagePO implements Serializable {
    /**
     * 预约日期
     */
    private Date orderDate;

    /**
     * 性别(0:男 1:女)
     */
    private Byte sex;

    /**
     * 当前页数
     */
    private Integer offset;

    /**
     * 每页记录数
     */
    private Integer limit;

    private static final long serialVersionUID = 1L;
}
