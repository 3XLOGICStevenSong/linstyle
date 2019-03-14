package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:NursePlanDTO
 * @Description:护理计划DTO
 * @author:陈健飞
 * @date:2018年12月27日 下午12:56:42
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileNursePlanDTO implements Serializable {

    /**
     * 客户主键
     */
    @ApiModelProperty(value = "客户主键")
    private Integer customId;
    /**
     * 照护方案类型 1:生活助理 2:老龄照护 3:临床护理'
     */
    @ApiModelProperty(value = "照护方案类型 1:生活助理 2:老龄照护 3:临床护理")
    private Byte nursePlanType;
    /**
     * t_nurse_plan
     */
    private static final long serialVersionUID = 1L;
}