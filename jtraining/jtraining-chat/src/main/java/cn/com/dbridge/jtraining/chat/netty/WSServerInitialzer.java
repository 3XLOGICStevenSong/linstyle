package cn.com.dbridge.jtraining.chat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @ClassName:  WSServerInitialzer
 * @Description: Netty Server 初始化
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:34:30
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//将请求和应答消息编码或解码为HTTP消息
		pipeline.addLast(new HttpServerCodec());
		// 对写大数据流的支持
		pipeline.addLast(new ChunkedWriteHandler());
		//将HTTP消息的多个部分组合成为一条完整的HTTP消息
		pipeline.addLast(new HttpObjectAggregator(1024*64));
		//读空闲8秒 写空闲 10秒 空闲超时时间12秒-->客户端心跳时间必须小于12秒
		pipeline.addLast(new IdleStateHandler(8, 10, 12));
		//添加自定义心跳处理Handler
		pipeline.addLast(new HeartBeatHandler());
		/**
		 * websocket 服务器处理的协议，用于指定给客户端连接访问的路由 : /ws
		 * 会帮你处理握手动作： handshaking（close, ping, pong） ping + pong = 心跳
		 */
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		// 自定义聊天的handler
		pipeline.addLast(new ChatHandler());
	}

}
