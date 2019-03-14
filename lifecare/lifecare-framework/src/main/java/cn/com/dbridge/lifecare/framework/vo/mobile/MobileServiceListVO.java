package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName:MobileServiceListVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月2日 下午4:29:42
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileServiceListVO {
    /**
     * 相片地址
     */
    private String img;

    /**
     * 姓名
     */
    private String realName;

    /**
     * dict表名称
     */
    private String name;

    /**
     * 预约日期
     */
    private Date orderDate;

    /**
     * 预约开始时间
     */
    private Date orderBeginTime;

    /**
     * 预约结束时间
     */
    private Date orderEndTime;
}
