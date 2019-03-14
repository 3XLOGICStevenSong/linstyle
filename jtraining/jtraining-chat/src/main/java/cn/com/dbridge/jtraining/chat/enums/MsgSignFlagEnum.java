package cn.com.dbridge.jtraining.chat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName:  MsgSignFlagEnum
 * @Description: 消息签收状态 枚举
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:34:30
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@AllArgsConstructor
@Getter
public enum MsgSignFlagEnum {
	/**
	 * 未签收 VALUE: 0
	 */
	unsign((byte)0, "未签收"),
	/**
	 * 已签收 VALUE: 1
	 */
	signed((byte)1, "已签收");	
	public final Byte type;
	public final String content;
}
