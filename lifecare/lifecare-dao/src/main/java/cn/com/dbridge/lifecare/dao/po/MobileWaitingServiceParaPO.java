package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;

import lombok.Data;

/**
 * @ClassName:MobileWaitingServicePO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 上午11:14:25
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileWaitingServiceParaPO implements Serializable {
    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 当前页数
     */
    private Integer offset;

    /**
     * 每页显示数
     */
    private Integer limit;

    private static final long serialVersionUID = 1L;
}
