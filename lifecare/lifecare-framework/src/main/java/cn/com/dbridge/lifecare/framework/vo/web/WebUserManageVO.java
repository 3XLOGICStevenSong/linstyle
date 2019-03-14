package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @ClassName:  WebUserManageVO
 * @Description:用户管理页面用户信息查询VO
 * @author: 王林江
 * @date:   2019年01月05日 10:13:18
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@JsonInclude(value=Include.NON_NULL)
@Data
public class WebUserManageVO implements Serializable {
    /**
     * 用户主键
     */
    @ApiModelProperty(value = "用户主键")
    private Integer userId;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String userNumber;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String realName;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String userAddr;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 性别(0:男 1:女)
     */
    @ApiModelProperty(value = "性别(0:男 1:女)")
    private Byte sex;

    /**
     * 服务类别(000:未设定 001:生活助理 010:老龄照护 100:临床护理 011:生活助理 + 老龄照护 101:生活助理 + 临床护理 110:老龄照护 + 临床护理 111:生活助理 + 老龄照护 + 临床护理)
     */
    @ApiModelProperty(value = "服务类别(000:未设定 001:生活助理 010:老龄照护 100:临床护理 011:生活助理 + 老龄照护 101:生活助理 + 临床护理 110:老龄照护 + 临床护理 111:生活助理 + 老龄照护 + 临床护理)")
    private String serviceCategoryLevel;

    /**
     * 星级或者等级
     */
    @ApiModelProperty(value = "星级或者等级")
    private String userLevel;

    /**
     * 状态(0:启用 1:停用)
     */
    @ApiModelProperty(value = "状态(0:启用 1:停用)")
    private Byte userStatus;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;

}