package cn.com.dbridge.jtraining.chat;

import cn.com.dbridge.jtraining.chat.netty.WSServer;
import com.google.common.base.Predicates;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @ClassName: NettyBooter
 * @Description: Netty启动类
 * @author: 陈健飞
 * @date: 2018年12月5日 上午10:34:30
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 * 注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * 对于TOMCAT应用来说一般会加载两个上下文容器一个父容器，一个mvc子容器</br>
     * 这样就会触发两次ContextRefreshedEvent事件，导致监听此事件所作的逻辑执行两次。</br>
     * 所以在这里必须进行Check </br>
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        if (Predicates.isNull().apply(context.getParent())) {
            try {
                String wsPort = context.getEnvironment().getProperty("websocket.port");
                Integer port = Integer.parseInt(wsPort);
                WSServer.getInstance().start(port);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
