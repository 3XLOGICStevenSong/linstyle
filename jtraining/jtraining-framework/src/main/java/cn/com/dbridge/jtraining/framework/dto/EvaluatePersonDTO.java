/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:EvaluatePersonDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:クエリ評価関連情報
 * @author:郭健
 * @date:2018年12月14日 午後2時40分48秒
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc.すべての権利を保有します。
 *               *注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:EvaluatePersonDTO
 * @Description:クエリ評価関連情報
 * @author:郭健
 * @date:2018年12月14日 午後2時40分48秒
 *
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Data
public class EvaluatePersonDTO {

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
}
