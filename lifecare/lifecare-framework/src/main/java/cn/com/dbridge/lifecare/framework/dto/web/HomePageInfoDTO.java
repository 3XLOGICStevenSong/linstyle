package cn.com.dbridge.lifecare.framework.dto.web;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  HomePageInfoDTO
 * @Description:主页信息DTO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:56:16
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class HomePageInfoDTO implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer homePageId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Integer updateBy;

    /**
     * 培训服务信息
     */
    @ApiModelProperty(value = "培训服务信息")
    private String trainInfo;

    /**
     * 驿站服务信息
     */
    @ApiModelProperty(value = "驿站服务信息")
    private String dakInfo;

    /**
     * 巡回车服务信息
     */
    @ApiModelProperty(value = "巡回车服务信息")
    private String tourBusInfo;

    /**
     * 久久义工敬老服务信息
     */
    @ApiModelProperty(value = "久久义工敬老服务信息")
    private String dutyInfo;

    /**
     * t_home_page_info
     */
    private static final long serialVersionUID = 1L;
}