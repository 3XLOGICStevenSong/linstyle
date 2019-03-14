package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WxMyTaskQueryVO
 * @Description:微信任务查询接口
 * @author: 陈健飞
 * @date:   2019年1月8日 上午11:07:18
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
@Data
public class WxTaskPoolQueryVO {
    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    private String taskId;
    /**
     * 服务类别编码
     */
    @ApiModelProperty(value = "任务类别名称")
    private String serviceCategoryName;
    /**
     * 服务类别颜色
     */
    @ApiModelProperty(value = "任务类别名称")
    private String serviceCategoryColor;
    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    private String taskNo;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;
    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;
    /**
     * 订单日期
     */
    @ApiModelProperty(value = "订单日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date orderDate;
    /**
     * 订单开始时间
     */
    @ApiModelProperty(value = "订单开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    private Date orderBeginTime;
    /**
     * 订单结束时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm")
    @ApiModelProperty(value = "订单结束时间")
    private Date orderEndTime;
    /**
     * 街区
     */
    @ApiModelProperty(value = "街区")
    private String streetVillage;
    /**
     * 行政区名称
     */
    @ApiModelProperty(value = "行政区名称")
    private String districtName;
    /**
     * 查看标志
     */
    @ApiModelProperty(value = "查看标志")
    private String seeFlg;
    /**
     * 订单状态
     */
    private String orderStatus;
    //-----------------查询条件-------------------
    /**
     * 服务人员ID 
     */
    private Integer servicePersonId;
    
}
