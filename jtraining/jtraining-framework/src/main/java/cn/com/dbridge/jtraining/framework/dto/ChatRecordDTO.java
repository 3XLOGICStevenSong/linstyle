/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:ChatRecordDTO.java
 * @Package cn.com.dbridge.jtraining.framework.dto
 * @Description:チャット追加のための情報
 * @author:郭健
 * @date:2018年12月14日 11:47:32 AM
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc.すべての権利を保有します。
 *               *注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:ChatRecordDTO
 * @Description:チャット追加のための情報
 * @author:郭健
 * @date:2018年12月14日 11:47:32 AM
 *
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Data
public class ChatRecordDTO implements Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 740824396052199521L;

    /**
     * 連番 メッセージ番号
     */
    @NotEmpty(message = "{no.notnull}")
    @ApiModelProperty(value = "連番 メッセージ番号")
    private String no;

    /**
     * 送信ユーザー番号
     */
    @NotEmpty(message = "{fromUserId.notnull}")
    @ApiModelProperty(value = "送信ユーザー番号")
    private String fromUserId;

    /**
     *受信ユーザー番号
     */
    @NotEmpty(message = "{toUserId.notnull}")
    @ApiModelProperty(value = "受信ユーザー番号")
    private String toUserId;

    /**
     * メッセージが署名されているかどうか0：署名なし1：署名済み
     */
    @ApiModelProperty(value = "メッセージが署名されているかどうか0：署名なし1：署名済み")
    private Byte signFlag;

    /**
     *チャット記録
     */
    @ApiModelProperty(value = "チャット記録")
    private String msg;

    /**
     *作成日
     */
    @ApiModelProperty(value = "作成日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp insertDate;

    /**
     *作成者
     */
    @ApiModelProperty(value = "作成者")
    private String insertPerson;

    /**
     *更新日
     */
    @ApiModelProperty(value = "更新日")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

    /**
     *更新者
     */
    @ApiModelProperty(value = "更新者")
    private String updatePerson;

}
