/**
 * All rights Reserved, Designed By www.tydic.com
 * @Title:MyChatRecordPO.java
 * @Package cn.com.dbridge.jtraining.dao.po
 * @Description:
 * @author:郭健
 * @date:2018年12月17日 下午5:07:34
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.dao.po;

import java.sql.Timestamp;

import lombok.Data;

/**
 * @ClassName:ChatRecordPO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:郭健
 * @date:2018年12月17日 下午5:07:34
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class MyChatRecordPO {
    private String no;

    private String fromUserId;

    private String senderName;

    private String personDraw;

    private String toUserId;

    private Byte signFlag;

    private String msg;

    private Timestamp insertDate;

    private String insertPerson;

    private Timestamp updateDate;

    private String updatePerson;
}
