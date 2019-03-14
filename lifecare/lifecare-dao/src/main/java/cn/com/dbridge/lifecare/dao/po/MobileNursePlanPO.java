package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName:MobileNursePlanPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月5日 下午2:52:16
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileNursePlanPO implements Serializable {
    /**
     * 客户主键
     */
    private Integer customId;

    /**
     * 相片地址
     */
    private String img;

    /**
     * 客户姓名
     */
    private String realName;

    /**
     * 照护方案标题
     */
    private String nursePlanTitle;

    /**
     * 方案开始日期
     */
    private Date nursePlanBeginDate;

    /**
     * 方案结束日期
     */
    private Date nursePlanEndDate;

    /**
     * 照护方案内容
     */
    private String nursePlanContent;

    private static final long serialVersionUID = 1L;
}
