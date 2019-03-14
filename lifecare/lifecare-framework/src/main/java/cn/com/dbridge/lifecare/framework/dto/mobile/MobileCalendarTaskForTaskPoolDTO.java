package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileCalendarTaskForTaskPoolDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月4日 上午9:45:05
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileCalendarTaskForTaskPoolDTO {
    /**
     * 预约日期
     */
    @ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDate;

    /**
     * 性别(0:男 1:女)
     */
    @ApiModelProperty(value = "性别(0:男 1:女)")
    private Byte sex;
}
