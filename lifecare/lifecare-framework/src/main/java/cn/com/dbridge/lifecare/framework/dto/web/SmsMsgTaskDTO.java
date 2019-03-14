package cn.com.dbridge.lifecare.framework.dto.web;

import lombok.Data;

/**
 * @ClassName:SmsMsgTaskDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月17日 下午7:18:49
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class SmsMsgTaskDTO {
    /**
     * 订单编号
     */
    private Integer taskId;

    private String customRealName;

    private String servicePersonRealName;

    private String customMobile;

    private String servicePersonMobile;

}
