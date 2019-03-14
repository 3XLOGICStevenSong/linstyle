package cn.com.dbridge.lifecare.framework.vo.web;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * @ClassName:WebUnassignedTaskSeeVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月8日 上午10:35:33
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class UnassignedTaskSeeVO {
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
     * 地址
     */
    private String userAddr;
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
    /**
     * 查看标志(0:未读 1:已读)
     */
    private Byte seeFlg;
}
