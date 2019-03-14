package cn.com.dbridge.lifecare.framework.dto.web;

import lombok.Data;

/**
 * @ClassName:UserTaskRealDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月8日 下午1:50:50
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class WebUserTaskRealDTO {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 服务人员id
     */
    private Integer userId;
    /**
     * 查看类型(0：任务池 1:我的任务)
     */
    private String type;

}
