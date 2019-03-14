package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @ClassName:  HomePageInfoVO
 * @Description:主页VO
 * @author: 陈健飞
 * @date:   2018年12月27日 下午12:54:03
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
public class HomePageInfoVO implements Serializable {
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

    /**
     * 主键
     * @return home_page_id 主键
     */
    public Integer getHomePageId() {
        return homePageId;
    }

    /**
     * 主键
     * @param homePageId 主键
     */
    public void setHomePageId(Integer homePageId) {
        this.homePageId = homePageId;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     * @return create_by 创建人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 创建人
     * @param createBy 创建人
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新人
     * @return update_by 更新人
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * 更新人
     * @param updateBy 更新人
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 培训服务信息
     * @return train_info 培训服务信息
     */
    public String getTrainInfo() {
        return trainInfo;
    }

    /**
     * 培训服务信息
     * @param trainInfo 培训服务信息
     */
    public void setTrainInfo(String trainInfo) {
        this.trainInfo = trainInfo;
    }

    /**
     * 驿站服务信息
     * @return dak_info 驿站服务信息
     */
    public String getDakInfo() {
        return dakInfo;
    }

    /**
     * 驿站服务信息
     * @param dakInfo 驿站服务信息
     */
    public void setDakInfo(String dakInfo) {
        this.dakInfo = dakInfo;
    }

    /**
     * 巡回车服务信息
     * @return tour_bus_info 巡回车服务信息
     */
    public String getTourBusInfo() {
        return tourBusInfo;
    }

    /**
     * 巡回车服务信息
     * @param tourBusInfo 巡回车服务信息
     */
    public void setTourBusInfo(String tourBusInfo) {
        this.tourBusInfo = tourBusInfo;
    }

    /**
     * 久久义工敬老服务信息
     * @return duty_info 久久义工敬老服务信息
     */
    public String getDutyInfo() {
        return dutyInfo;
    }

    /**
     * 久久义工敬老服务信息
     * @param dutyInfo 久久义工敬老服务信息
     */
    public void setDutyInfo(String dutyInfo) {
        this.dutyInfo = dutyInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", homePageId=").append(homePageId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", trainInfo=").append(trainInfo);
        sb.append(", dakInfo=").append(dakInfo);
        sb.append(", tourBusInfo=").append(tourBusInfo);
        sb.append(", dutyInfo=").append(dutyInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}