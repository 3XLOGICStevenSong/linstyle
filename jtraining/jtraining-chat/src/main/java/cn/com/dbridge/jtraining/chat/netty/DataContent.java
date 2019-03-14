package cn.com.dbridge.jtraining.chat.netty;

import lombok.Data;

import java.io.Serializable;
/**
 * @ClassName:  DataContent
 * @Description: 传输消息模型
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:34:30
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class DataContent implements Serializable {
	private static final long serialVersionUID = 8021381444738260454L;
	/**
	 * 动作类型
	 */
	private Integer action;
	/**
	 * 用户的聊天内容entity
	 */
	private ChatMsg chatMsg;
	/**
	 * 扩展字段
	 */
	private String extand;
}
