package cn.com.dbridge.lifecare.framework.dto.mobile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileTaskDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 上午8:47:43
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileTaskDTO {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;
}