package cn.com.dbridge.lifecare.framework.dto.mobile;

import lombok.Data;

/**
 * @ClassName:MobileOrderStatusUpdateDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 上午8:29:04
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileOrderStatusUpdateDTO {
    /**
     * 订单主键
     */
    private Integer id;

    /**
     * 操作类型(0:开始服务 1:完成服务
     */
    private String optType;
}
