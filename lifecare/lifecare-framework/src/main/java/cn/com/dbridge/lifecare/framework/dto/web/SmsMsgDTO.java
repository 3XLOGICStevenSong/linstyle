package cn.com.dbridge.lifecare.framework.dto.web;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName:SmsMsgDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月16日 下午4:21:39
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class SmsMsgDTO {
    /**
     * 订单编号
     */
    private Integer taskId;

    /**
     * 1客户 0服务人员
     */
    private Byte type;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 发送短信间隔时间
     */
    private Integer step;

    /**
     * 发送时间
     */
    private Date sendTime;

}
