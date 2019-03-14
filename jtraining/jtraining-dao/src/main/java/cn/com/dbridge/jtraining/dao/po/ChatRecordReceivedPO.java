package cn.com.dbridge.jtraining.dao.po;

import java.util.List;

import lombok.Data;

/**
 * 
 * @ClassName:  ChatRecordReceivedPO
 * @Description:消息签收PO
 * @author: 陈健飞
 * @date:   2018年12月28日 上午10:14:43
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class ChatRecordReceivedPO {
    private Byte signFlag;
    private List<String> noList;
}
