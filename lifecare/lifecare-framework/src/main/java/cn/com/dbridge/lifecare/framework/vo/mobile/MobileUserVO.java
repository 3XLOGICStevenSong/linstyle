package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileUserVO
 * @Description:TODO
 * @author:郭健
 * @date:2018年12月29日 下午1:03:52
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class MobileUserVO implements Serializable {
    private static final long serialVersionUID = -1627073846822880681L;

    /**
     * 用户主键
     */
    private Integer userId;
    /**
     * 性别(0:男 1:女)
     */
    @ApiModelProperty(value = "性别(0:男 1:女)")
    private Byte sex;

}
