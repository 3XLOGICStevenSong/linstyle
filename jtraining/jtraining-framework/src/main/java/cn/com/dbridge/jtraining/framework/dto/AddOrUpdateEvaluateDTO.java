/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:AddOrUpdateEvaluateDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:評価の追加または編集に関する情報
 * @author:郭健
 * @date:2018年12月18日 1:44:27 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:AddOrUpdateEvaluateDTO
 * @Description:評価の追加または編集に関する情報
 * @author:郭健
 * @date:2018年12月18日 1:44:27 PM
 *
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Data
public class AddOrUpdateEvaluateDTO implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -4350708945140567052L;

    /**
     * 学生番号
     */
    @NotEmpty(message = "{studentId.notnull}")
    @ApiModelProperty(value = "学生番号")
    private String studentId;

    /**
     * 評価教師
     */
    @NotEmpty(message = "{teacherId.notnull}")
    @ApiModelProperty(value = "評価教師")
    private String teacherId;

    /**
     * 学生評価
     */
    @ApiModelProperty(value = "学生評価")
    private String evaluate;
}
