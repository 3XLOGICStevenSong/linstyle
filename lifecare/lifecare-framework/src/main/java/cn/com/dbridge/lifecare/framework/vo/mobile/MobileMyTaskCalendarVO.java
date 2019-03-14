package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileMyTaskCalendarVO
 * @Description:预约日期VO
 * @author:郭健
 * @date:2019年1月3日 下午2:44:29
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileMyTaskCalendarVO {
    /**
     * 预约日期
     */
    @ApiModelProperty(value = "预约日期")
    private Date orderDate;

    /**
     * 是否看过
     */
    @ApiModelProperty(value = "是否看过")
    private String seeFlag;
}
