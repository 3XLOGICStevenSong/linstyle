package cn.com.dbridge.lifecare.framework.dto.mobile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:MobileMyInfoDTO
 * @Description:我的信息页查询DTO
 * @author:郭健
 * @date:2018年12月27日 下午12:58:18
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileMyInfoDTO{
    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private Integer userId;
}