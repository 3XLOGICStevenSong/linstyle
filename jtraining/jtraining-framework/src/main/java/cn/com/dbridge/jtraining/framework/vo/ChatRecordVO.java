/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:PhoneUserVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:チャットレコード情報表示結果
 * @author:郭健
 * @date:2018年12月17日 午後9時01分58秒
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.framework.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 
 * @ClassName:ChatRecordVO
 * @Description:チャットレコード情報表示結果
 * @author:郭健
 * @date:2018年12月17日 午後9時01分58秒
 *
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Data
public class ChatRecordVO {
    /**
     * 連番 メッセージ番号
     */
    private String no;

    /**
     * 送信ユーザー番号
     */
    private String fromUserId;

    /**
     * 送信ユーザー名前
     */
    private String senderName;

    /**
     * 送信ユーザー画像
     */
    private String personDraw;

    /**
      * 受信ユーザー番号
     */
    private String toUserId;

    /**
     * メッセージが署名されているかどうか0：署名なし1：署名済み
     */
    private Byte signFlag;

    /**
     * チャット記録
     */
    private String msg;

    /**
     * 作成日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp insertDate;

    /**
     * 作成者
     */
    private String insertPerson;

    /**
     * 更新日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

    /**
     * 更新者
     */
    private String updatePerson;
}