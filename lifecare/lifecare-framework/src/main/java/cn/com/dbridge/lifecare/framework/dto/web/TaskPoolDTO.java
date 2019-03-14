package cn.com.dbridge.lifecare.framework.dto.web;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:WebTaskPoolDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月8日 下午4:39:08
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class TaskPoolDTO {
    /**
     * 查询日期
     */
    @ApiModelProperty(value = "查询日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @NotNull(message = "查询日期必须设定为有效日期")
    private Date orderDate;

}
