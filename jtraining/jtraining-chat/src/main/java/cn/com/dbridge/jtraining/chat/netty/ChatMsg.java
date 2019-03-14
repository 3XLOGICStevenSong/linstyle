package cn.com.dbridge.jtraining.chat.netty;

import java.io.Serializable;

import lombok.Data;
/**
 * @ClassName:  ChatMsg
 * @Description: 处理消息的handler
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:34:30
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class ChatMsg implements Serializable {
	private static final long serialVersionUID = 3611169682695799175L;

	/**
	 * 发送者的用户id
	 */
	private String senderId;
	/**
	 * 接受者的用户id
	 */
	private String receiverId;
	/**
	 * 聊天内容
	 */
	private String msg;
	/**
	 * 用于消息的签收
	 */
	private String msgId;
	/**
	 * 消息发送时间
	 */
	private long msgTime;
	/**
	 * 发送者姓名
	 */
	private String senderName;
	/**
     * 接受者姓名
     */
    private String receiverName;
	/**
	 * 发送者头像
	 */
	private String senderAvatar;
}
