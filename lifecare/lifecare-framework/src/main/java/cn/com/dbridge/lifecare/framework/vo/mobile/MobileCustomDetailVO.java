package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName:MobileCustomDetailVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月5日 上午11:25:31
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
public class MobileCustomDetailVO {
    /**
     * 服务类别名称
     */
    @ApiModelProperty(value = "服务类别名称")
    private String serviceCategoryName;

    /**
     * 服务类颜色
     */
    @ApiModelProperty(value = "服务类颜色")
    private String serviceCategoryColor;
    /**
     * 服务开始时间
     */
    @ApiModelProperty(value = "服务开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date serviceBeginTime;

    /**
     * 服务结束时间
     */
    @ApiModelProperty(value = "服务结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date serviceEndTime;
}
