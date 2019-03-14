package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @ClassName:  SmsMsgPO
 * @Description:短信消息PO
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:15:30
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class SmsMsgPO implements Serializable {
    /**
     * 短信模板编号
     */
    private Integer id;

    /**
     * 订单编号
     */
    private Integer taskId;
    
    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 1客户 0服务人员
     */
    private Byte userType;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 发送短信间隔时间
     */
    private Integer step;

    /**
     * 发送时间
     */
    private Date sendTime;

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

    private static final long serialVersionUID = 1L;
}