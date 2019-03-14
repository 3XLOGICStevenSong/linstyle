package cn.com.dbridge.jtraining.chat.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:  HeartBeatHandler
 * @Description: 用于检测channel的心跳
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:34:30
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent)evt;
			if (event.state() == IdleState.READER_IDLE) {
				log.debug("#########进入读空闲#########");
			} else if (event.state() == IdleState.WRITER_IDLE) {
				log.debug("#########进入写空闲#########");
			} else if (event.state() == IdleState.ALL_IDLE) {
				log.debug("channel关闭前，users的数量为：" + ChatHandler.users.size()+"关闭Channel:"+ctx.channel().id());
				//TODO Channel关闭时候，在Redis中删除Channel
				Channel channel = ctx.channel();
                channel.close();
				log.debug("channel关闭后，users的数量为：" + ChatHandler.users.size() );
			}
		}
	}
}
