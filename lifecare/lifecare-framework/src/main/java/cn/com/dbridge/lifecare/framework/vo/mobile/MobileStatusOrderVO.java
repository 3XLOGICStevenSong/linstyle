package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName:MobileStatusOrderVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月2日 下午5:42:02
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class MobileStatusOrderVO {
    /**
     * 姓名
     */
    private String realName;
    
    /**
     * 服务开始时间
     */
    private Date serviceBeginTime;

    /**
     * 服务结束时间
     */
    private Date serviceEndTime;
    
    /**
     * 订单状态(1：待分配 2:待完成 3.已完成 4.取消)
     */
    private Byte orderStatus;
    
    /**
     * 手机
     */
    private String mobile;
    
    /**
     * 省
     */
    private String provinceName;
    
    /**
     * 市
     */
    private String cityName;
    
    /**
     * 区
     */
    private String districtName;
    
    /**
     * 街道小区
     */
    private String streetVillage;

    /**
     * 楼号
     */
    private String buildingNo;

}
