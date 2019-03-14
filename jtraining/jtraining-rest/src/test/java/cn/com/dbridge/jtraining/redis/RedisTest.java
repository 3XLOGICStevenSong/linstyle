///**
// * All rights Reserved, Designed By dbridge.com.cn
// * 
// * @Title: RedisTest.java
// * @Package cn.com.dbridge.jtraining.redis
// * @Description: Redis 测试用例
// * @author: 陈健飞
// * @date: 2018年12月7日 下午2:12:45
// * @version V1.0
// * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
// *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
// */
//package cn.com.dbridge.jtraining.redis;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//
//import cn.com.dbridge.jtraining.BaseJunitTest;
//import cn.com.dbridge.jtraining.framework.util.JedisUtil;
//
///**
// * @ClassName:  RedisTest
// * @Description: Redis 测试用例
// * @author: 陈健飞
// * @date:   2018年12月7日 下午2:12:45
// * 
// * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
// *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
// */
//public class RedisTest extends BaseJunitTest {
//    @Test
//    public void test() throws Exception {
//        User user = new User("Andy", 30);
//        JedisUtil.setObject(user.getName(), user);
//        assertEquals("30",
//                ((User) JedisUtil.getObject(user.getName())).getAge() + "");
//    }
//}
