package cn.com.dbridge.lifecare.dao.po;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName:WebUnassignedTaskSeePO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月8日 下午12:54:24
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class UnassignedTaskSeePO {
    /**
     * 订单时长
     */
    private String orderDuration;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 服务人员id
     */
    private Integer servicePersonId;
    /**
     * 服务人员工号
     */
    private String userNumber;
    /**
     * 服务人员姓名
     */
    private String realName;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 区名
     */
    private String districtName;
    /**
     * 街道名
     */
    private String streetVillage;
    /**
     * 楼号
     */
    private String buildingNo;
    /**
     * 生日
     */
    private Date birthday;
    /**
     *  性别
     */
    private Byte sex;
    /**
     * 等级
     */
    private String userLevel;
    /**
     * 状态
     */
    private Byte userStatus;
}
