package cn.com.dbridge.lifecare.framework.dto.mobile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:  WxWaitingServiceDTO
 * @Description:客户服务人员关联任务DTO
 * @author: 陈健飞
 * @date:   2019年1月9日 上午9:42 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WxCustomServicePersonTaskDTO {
    /**
     * 客户ID
     */
    private Integer customId;
    /**
     * 服务人员ID
     */
    private Integer servicePersonId;
    /**
     * 开始记录数
     */
    @ApiModelProperty(value = "开始记录数")
    private Integer offset;

    /**
     * 每页显示数
     */
    @ApiModelProperty(value = "每页显示数")
    private Integer limit;
}
