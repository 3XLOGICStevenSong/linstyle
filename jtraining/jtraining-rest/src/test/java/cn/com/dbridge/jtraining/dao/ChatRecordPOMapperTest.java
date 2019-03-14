/**
 * All rights Reserved, Designed By www.tydic.com
 * @Title:ChatRecordPOMapperTest.java
 * @Package cn.com.dbridge.jtraining.dao
 * @Description:
 * @author:郭健
 * @date:2018年12月17日 下午3:08:13
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.dbridge.jtraining.BaseJunitTest;
import cn.com.dbridge.jtraining.dao.po.ChatRecordPO;
import cn.com.dbridge.jtraining.dao.respository.ChatRecordPOMapper;

/**
 * @ClassName:ChatRecordPOMapperTest
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:郭健
 * @date:2018年12月17日 下午3:08:13
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class ChatRecordPOMapperTest extends BaseJunitTest {
    @Autowired
    private ChatRecordPOMapper chatRecordPOMapper;

    @Test
    public void testInsert() {
        ChatRecordPO chatRecordPO = new ChatRecordPO();
        chatRecordPO.setFromUserId("1");
        chatRecordPO.setInsertPerson("2");
        chatRecordPOMapper.insert(chatRecordPO);
    }
}
