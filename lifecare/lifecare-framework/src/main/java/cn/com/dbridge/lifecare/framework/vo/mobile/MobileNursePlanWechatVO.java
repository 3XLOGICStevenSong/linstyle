package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MobileNursePlanWechatVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月5日 下午2:29:06
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class MobileNursePlanWechatVO {
    /**
     * 客户主键
     */
    @ApiModelProperty(value = "客户主键")
    private Integer customId;

    /**
     * 相片地址
     */
    @ApiModelProperty(value = "头像地址")
    private String img;

    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名")
    private String realName;

    /**
     * 照护方案标题
     */
    @ApiModelProperty(value = "照护方案标题")
    private String nursePlanTitle;

    /**
     * 方案开始日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "方案开始日期")
    private Date nursePlanBeginDate;

    /**
     * 方案结束日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = " 方案结束日期")
    private Date nursePlanEndDate;

    /**
     * 照护方案内容
     */
    @ApiModelProperty(value = "  照护方案内容")
    private String nursePlanContent;
}
