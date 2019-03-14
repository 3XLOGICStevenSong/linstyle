package cn.com.dbridge.jtraining.chat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:  WSServer
 * @Description: WSServer
 * @author: 陈健飞
 * @date:   2018年12月5日 上午10:34:30
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Slf4j
public class WSServer {
    
	private static class SingletionWSServer {
		static final WSServer instance = new WSServer();
	}
	public static WSServer getInstance() {
		return SingletionWSServer.instance;
	}
	private EventLoopGroup mainGroup;
	private EventLoopGroup subGroup;
	private ServerBootstrap server;
	@SuppressWarnings("unused")
	private ChannelFuture future;
	private WSServer() {
		mainGroup = new NioEventLoopGroup();
		subGroup = new NioEventLoopGroup();
		server = new ServerBootstrap();
		server.group(mainGroup, subGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new WSServerInitialzer());
	}
	public void start(Integer port) {
		this.future = server.bind(port);
		log.info("=================================");
		log.info("netty websocket server 启动完毕...端口号[{}]",port);
		log.info("=================================");
	}
}
