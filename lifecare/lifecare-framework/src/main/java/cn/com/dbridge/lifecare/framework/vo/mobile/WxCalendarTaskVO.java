package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName:  WxCalendarTaskDTO
 * @Description:微信日历任务VO
 * @author: 陈健飞
 * @date:   2019年1月8日 上午11:05:44
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WxCalendarTaskVO {
    /**
     *  预约日期
     */
    @ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDate;
    /**
     * 看看标志
     */
    @ApiModelProperty(value = "查看标志(0:未查看 1:查看)")
    private String seeFlg;
}
