package cn.com.dbridge.jtraining.chat.netty;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import cn.com.dbridge.jtraining.chat.SpringUtil;
import cn.com.dbridge.jtraining.chat.enums.MsgActionEnum;
import cn.com.dbridge.jtraining.chat.enums.MsgSignFlagEnum;
import cn.com.dbridge.jtraining.chat.utils.JsonUtils;
import cn.com.dbridge.jtraining.framework.dto.ChatRecordDTO;
import cn.com.dbridge.jtraining.framework.dto.MyChatRecordDTO;
import cn.com.dbridge.jtraining.service.ChatRecordService;
import cn.com.dbridge.jtraining.service.impl.ChatRecordServiceImpl;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: ChatHandler
 * @Description: 处理消息的handler
 * @author: 陈健飞
 * @date: 2018年12月5日 上午10:34:30
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 注意：本内容仅限于必捷必信息技术有限公司
 *             内部传阅，禁止外泄以及用于其他的商业目
 */
@Slf4j
public class ChatHandler
        extends
            SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Autowired
    public static ChannelGroup users = new DefaultChannelGroup(
            GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
            TextWebSocketFrame msg) throws Exception {
        String content = msg.text();
        Channel currentChannel = ctx.channel();
        DataContent dataContent = JsonUtils.jsonToPojo(content,
                DataContent.class);
        Integer action = dataContent.getAction();
        if (MsgActionEnum.CONNECT.type.equals(action)) {
            // 初始化连接
            String senderId = dataContent.getChatMsg().getSenderId();
            log.info("初始化连接用户：{},ChannelId:{}", senderId, currentChannel.id());
            //TODO 将在线状态存储到缓存中 KEY 为currentChannel.id() VALUE为senderId
            UserChannelRel.put(senderId, currentChannel);
            UserChannelRel.output();
        } else if (MsgActionEnum.CHAT.type.equals(action)) {
            // 聊天消息
            ChatMsg chatMsg = dataContent.getChatMsg();
            String msgText = chatMsg.getMsg();
            String receiverId = chatMsg.getReceiverId();
            String senderId = chatMsg.getSenderId();
            log.info("发送用户:{},接收用户:{},发送的消息内容：{},发送中···", senderId, receiverId,
                    msgText);
            //TODO 这个nid需要更换其他技术
            String nid = UUID.randomUUID().toString().replace("-", "");
            ChatRecordDTO chatRecordDTO = new ChatRecordDTO();
            Timestamp now = new Timestamp(System.currentTimeMillis());
            Long millisecond = Instant.now().toEpochMilli();
            chatRecordDTO.setFromUserId(senderId);
            chatRecordDTO.setInsertDate(now);
            chatRecordDTO.setMsg(msgText);
            chatRecordDTO.setNo(nid);
            chatRecordDTO.setSignFlag(MsgSignFlagEnum.unsign.type);
            chatRecordDTO.setToUserId(receiverId);
            chatRecordDTO.setInsertPerson(senderId);
            chatRecordDTO.setUpdatePerson(senderId);
            chatRecordDTO.setUpdateDate(now);
            ChatRecordService chatRecordService = SpringUtil
                    .getBean(ChatRecordServiceImpl.class);
            int count = chatRecordService.addChatRecord(chatRecordDTO);
            if(count > 0) {
                log.info("发送用户:{},接收用户:{},发送的消息内容：{},已入库···", senderId, receiverId,
                        msgText);
            }else {
                log.info("发送用户:{},接收用户:{},发送的消息内容：{},入库失败···", senderId, receiverId,
                        msgText);
            }
            chatMsg.setMsgId(nid);
            chatMsg.setMsgTime(millisecond);
            DataContent dataContentMsg = new DataContent();
            dataContentMsg.setChatMsg(chatMsg);
            Channel receiverChannel = UserChannelRel.get(receiverId);
            if (receiverChannel == null) {
                // TODO channel为空代表用户离线，推送消息（JPushJPush，个推，小米推送）
            } else {
                // 当receiverChannel不为空的时候，从ChannelGroup去查找对应的channel是否存在
                Channel findChannel = users.find(receiverChannel.id());
                if (findChannel != null) {
                    // 用户在线
                    String sendContent = JsonUtils.objectToJson(dataContentMsg);
                    log.info("发送用户:{},接收用户:{},发送的消息内容：{},已发送", senderId,
                            receiverId, sendContent);
                    receiverChannel
                            .writeAndFlush(new TextWebSocketFrame(sendContent));
                } else {
                    // 用户离线 TODO 推送消息
                }
            }
        } else if (MsgActionEnum.SIGNED.type.equals(action)) {
            // 消息签收
            String msgIdsStr = dataContent.getExtand();
            List<String> msgIdList = Arrays.asList(msgIdsStr.split(","));
            if (!CollectionUtils.isEmpty(msgIdList)) {
                // 批量签收
                MyChatRecordDTO myChatRecordDTO = new MyChatRecordDTO();
                myChatRecordDTO.setNoList(msgIdList);
                myChatRecordDTO.setSignFlag(MsgSignFlagEnum.signed.type);
                ChatRecordService chatRecordService = SpringUtil
                        .getBean(ChatRecordServiceImpl.class);
                chatRecordService.updateChatRecord(myChatRecordDTO);
            }
            log.info("消息签收：" + msgIdList);
        } else if (MsgActionEnum.KEEPALIVE.type.equals(action)) {
            // 心跳
            log.info("收到来自channel为[" + currentChannel + "]的心跳包...");
        }
    }

    /**
     * 客戶端添加
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    /**
     * 客戶端移除
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String channelId = ctx.channel().id().asShortText();
        log.info("客户端被移除，channelId为：" + channelId);
        users.remove(ctx.channel());
    }

    /**
     * 捕获客户端发生错误
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
        log.info("发生异常，关闭channelId为：{},异常信息为：{}" ,ctx.channel(),cause.getMessage());
        users.remove(ctx.channel());
    }
}
