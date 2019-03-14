/**
 * All rights Reserved, Designed By www.tydic.com
 * @Title:PhoneUpdateChatRecordPO.java
 * @Package cn.com.dbridge.jtraining.dao.po
 * @Description:
 * @author:郭健
 * @date:2018年12月17日 下午1:45:28
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.dao.po;

import java.util.List;

import lombok.Data;

/**
 * @ClassName:PhoneUpdateChatRecordPO
 * @Description:チャット履歴ステータスのエンティティクラスを更新する
 * @author:郭健
 * @date:2018年12月17日 午后1:45:28
 *
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、Betjean Information Technology Co.、Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Data
public class PhoneUpdateChatRecordPO {
    private List<String> noList;
    private Byte signFlag;
}
