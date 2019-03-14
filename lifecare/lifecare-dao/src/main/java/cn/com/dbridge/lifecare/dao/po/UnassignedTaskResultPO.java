package cn.com.dbridge.lifecare.dao.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName:WebUnassignedTaskPO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月7日 下午12:00:23
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UnassignedTaskResultPO extends TaskPO {
    private static final long serialVersionUID = 1L;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 客户编号
     */
    private Integer customId;
    /**
     * 客户名称
     */
    private String realName;
    /**
     * 客户手机
     */
    private String mobile;
}
