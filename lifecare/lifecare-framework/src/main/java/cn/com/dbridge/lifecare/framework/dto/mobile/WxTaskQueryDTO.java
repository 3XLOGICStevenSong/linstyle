package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName: WxTaskQueryDTO
 * @Description:微信查询任务DTO
 * @author: 陈健飞
 * @date: 2019年1月8日 上午11:05:44
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class WxTaskQueryDTO {
    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    private String taskId;
    /**
     * 服务类别名称
     */
    @ApiModelProperty(value = "服务类别名称")
    private String serviceCategoryName;
    /**
     * 服务类别颜色
     */
    @ApiModelProperty(value = "服务类别颜色")
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
    private String age;
    /**
     * 订单日期
     */
    @ApiModelProperty(value = "订单日期")
    private String orderDate;
    /**
     * 订单开始时间
     */
    @ApiModelProperty(value = "订单开始时间")
    private Date orderBeginTime;
    /**
     * 订单结束时间
     */
    @ApiModelProperty(value = "订单结束时间")
    private Date orderEndTime;

    /**
     * 街区
     */
    @ApiModelProperty(value = "街区")
    private String streetVillage;
    /**
     * 楼号-房间号
     */
    @ApiModelProperty(value = "楼号-房间号")
    private String building;
    /**
     * 行政区名称
     */
    @ApiModelProperty(value = "行政区名称")
    private String districtName;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 订单状态
     */
    private String orderStatus;
    // -----------------查询条件-------------------
    /**
     * 服务人员ID
     */
    private Integer servicePersonId;
    /**
     * 客户ID
     */
    private Integer customId;
    /**
     * 头像地址
     */
    private String img;
    /**
     * 客户的生日
     */
    private Date birthday;
}
