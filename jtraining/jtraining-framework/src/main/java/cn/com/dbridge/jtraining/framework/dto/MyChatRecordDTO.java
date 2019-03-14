/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:MyChatRecordDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:チャット更新のための情報
 * @author:郭健
 * @date:2018年12月17日 午後7時41分21秒
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:MyChatRecordDTO
 * @Description:チャット更新のための情報
 * @author:郭健
 * @date:2018年12月17日 午後7時41分21秒
 *
 * @Copyright:2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Data
public class MyChatRecordDTO {
    /**
     * メッセージが署名されているかどうか0：署名なし1：署名済み
     */
    @ApiModelProperty(value = "メッセージが署名されているかどうか0：署名なし1：署名済み")
    private Byte signFlag;

    /**
     * 変更する必要がある番号
     */
    @ApiModelProperty(value = "変更する必要がある番号")
    private List<String> noList;
}
