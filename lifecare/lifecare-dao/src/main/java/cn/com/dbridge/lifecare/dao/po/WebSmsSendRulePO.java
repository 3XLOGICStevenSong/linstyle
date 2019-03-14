package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @ClassName:  WebSmsSendRulePO
 * @Description:短息推送规则PO
 * @author: 王林江
 * @date:   2019年01月11日 13:13:18
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *  注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WebSmsSendRulePO implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 标识(0:都不发、1：客户发 2：服务人员发 3：都发)
     */
    private Byte sendFlag;

    /**
     * 分钟
     */
    private Integer minutes;

    /**
                 *  位置区分(0:上、1：下)
     */
    private Byte positionFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private Integer updateBy;

    /**
     * t_sms_send_rule
     */
    private static final long serialVersionUID = 1L;
}