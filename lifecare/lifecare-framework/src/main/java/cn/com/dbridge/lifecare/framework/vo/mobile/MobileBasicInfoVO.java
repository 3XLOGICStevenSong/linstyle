package cn.com.dbridge.lifecare.framework.vo.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * @ClassName:MobileBasicInfoVO
 * @Description:TODO
 * @author:郭健
 * @date:2019年1月5日 下午1:07:15
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@JsonInclude(value=Include.NON_NULL)
public class MobileBasicInfoVO {
    /**
     * 客户主键
     */
    private Integer customId;

    /**
     * 相片地址
     */
    private String img;

    /**
     * 客户姓名
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 生日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 地址
     */
    private String userAddr;

    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 背景
     */
    private String backInfo;
}
