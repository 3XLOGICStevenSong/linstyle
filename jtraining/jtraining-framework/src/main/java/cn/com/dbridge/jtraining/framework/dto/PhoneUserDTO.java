/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:PhoneUserDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:クエリ関連
 * @author:郭健
 * @date:2018年12月17日 午後8時58分20秒
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:PhoneUserDTO
 * @Description:クエリ関連
 * @author:郭健
 * @date:2018年12月17日 午後8時58分20秒
 *
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Data
public class PhoneUserDTO {
    /**
     * 番号
     */
    @NotEmpty(message = "{no.notnull}")
    @ApiModelProperty(value = "番号")
    private String no;
}
