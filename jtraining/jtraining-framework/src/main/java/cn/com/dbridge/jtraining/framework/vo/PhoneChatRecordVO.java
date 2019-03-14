/**
 * All rights Reserved, Designed By www.tydic.com
 * @Title:PhoneChatRecordVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:
 * @author:郭健
 * @date:2018年12月17日 上午11:43:28
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.framework.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @ClassName:PhoneChatRecordVO
 * @Description:チャットレコード出力
 * @author:郭健
 * @date:2018年12月17日、午前11時43分28秒
 *
 * @Copyright:2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */

@Data
public class PhoneChatRecordVO {
    //連番 消息编号
    private String no;
    //发送用户编号
    private String fromUserId;
    //接收用户编号
    private String toUserId;
    //消息是否已签收 0：未签收  1：已签收
    private Byte signFlag;
    //チャット記録
    private String msg;
    //作成日
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp insertDate;
    //作成人
    private String insert_person;
    //更新日
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;
    //更新人
    private String updatePerson;
}
