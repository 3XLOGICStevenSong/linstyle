package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileMyTaskDTO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月3日 下午12:52:53
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileMyTaskDTO {

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Integer userId;

    /**
     * 预约日期
     */
    @ApiModelProperty(value = "预约日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDate;
    
    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数")
    private Integer offset;
    
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private Integer limit;
}
