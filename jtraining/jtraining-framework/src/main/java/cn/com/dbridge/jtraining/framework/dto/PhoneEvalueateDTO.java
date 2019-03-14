/**
 * All rights Reserved, Designed By www.tydic.com
 * @Title:PhoneEvalueateDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:
 * @author:郭健
 * @date:2018年12月18日 下午1:17:57
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:PhoneEvalueateDTO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:郭健
 * @date:2018年12月18日 下午1:17:57
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class PhoneEvalueateDTO {
    /**
     * 学生番号
     */
    @NotEmpty(message = "{studentId.notnull}")
    @ApiModelProperty(value = "学生番号")
    private String studentId;

    /**
     * 评价教师
     */
    @NotEmpty(message = "{teacherId.notnull}")
    @ApiModelProperty(value = "评价教师")
    private String teacherId;
}
