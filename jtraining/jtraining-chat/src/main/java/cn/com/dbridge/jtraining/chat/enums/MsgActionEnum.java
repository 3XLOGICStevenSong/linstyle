package cn.com.dbridge.jtraining.chat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName:  MsgActionEnum
 * @Description: 消息动作枚举
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:34:30
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@AllArgsConstructor
@Getter
public enum MsgActionEnum {
	/**
	 * 第一次(或重连)初始化连接  VALUE: 1
	 */
	CONNECT(1, "第一次(或重连)初始化连接"),
	/**
	 * 聊天消息  VALUE: 2
	 */
	CHAT(2, "聊天消息"),
	/**
	 * 消息签收 VALUE: 3
	 */
	SIGNED(3, "消息签收"),
	/**
	 * 客户端保持心跳 VALUE: 4
	 */
	KEEPALIVE(4, "客户端保持心跳");

	public final Integer type;
	public final String content;
	
}
