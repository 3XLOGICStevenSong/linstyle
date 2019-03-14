package cn.com.dbridge.lifecare.dao.po;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName:TaskPoolPO
 * @Description:
 * @author:郭健
 * @date:2019年1月14日 上午11:21:58
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class TaskPoolPO {
    /**
     * 查询日期
     */
    private Date orderDate;
    /**
     * 查询天数
     */
    private Integer orderDateCnt;
}
