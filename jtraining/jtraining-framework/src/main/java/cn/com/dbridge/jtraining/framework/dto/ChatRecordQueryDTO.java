/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:ChatRecordQueryDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:チャットクエリのための情報
 * @author:郭健
 * @date:2018年12月17日 4:49:29 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:ChatRecordQueryDTO
 * @Description:チャットクエリのための情報
 * @author:郭健
 * @date:2018年12月17日 4:49:29 PM
 *
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Data
public class ChatRecordQueryDTO {
    /**
     * メッセージが署名されているかどうか0：署名なし1：署名済み
     */
    @ApiModelProperty(value = "メッセージが署名されているかどうか0：署名なし1：署名済み")
    @Min(0) 
    @Max(1)
    private Byte signFlag;

    private String toUserId;
}
