package cn.com.dbridge.lifecare.framework.dto.mobile;

import lombok.Data;
/**
 * 
 * @ClassName:  WxUpdateTaskStatusDTO
 * @Description:微信端更新任务状态DTO
 * @author: 陈健飞
 * @date:   2019年1月9日 上午9:13:12
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WxUpdateTaskStatusDTO {
    /**
     * 主键
     */
    private Integer id;
    
    /**
     * 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     */
    private Byte orderStatus;
    
}
