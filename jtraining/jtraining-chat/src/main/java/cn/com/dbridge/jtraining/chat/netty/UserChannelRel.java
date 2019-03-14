package cn.com.dbridge.jtraining.chat.netty;

import java.util.HashMap;
import java.util.Set;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;


/**
 * @ClassName:  UserChannelRel
 * @Description: 用户id和channel的关联关系处理
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:34:30
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Slf4j
public class UserChannelRel {
	//TODO 临时写法   后期需要使用Redis替换
	private static HashMap<String, Channel> manager = new HashMap<>();
	public static void put(String senderId, Channel channel) {
		log.info("新用户发送连接请求,工号:[{}],Channel：[{}]",senderId,channel);
		manager.put(senderId, channel);
	}
	public static Channel get(String senderId) {
		return manager.get(senderId);
	}
	public static Set<String> getAllOn() {
		Set<String> keys =  manager.keySet();
		return keys;
	}
	public static void output() {
		for (HashMap.Entry<String, Channel> entry : manager.entrySet()) {
			log.debug("系统目前支持的用户：{},对应的Channle是:{}",entry.getKey(),entry.getValue().id().asLongText());
		}
	}
}
