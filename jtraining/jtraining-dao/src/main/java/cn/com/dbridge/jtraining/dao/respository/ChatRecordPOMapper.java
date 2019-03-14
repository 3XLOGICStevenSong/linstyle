package cn.com.dbridge.jtraining.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.jtraining.dao.po.ChatRecordPO;
import cn.com.dbridge.jtraining.dao.po.ChatRecordReceivedPO;
import cn.com.dbridge.jtraining.dao.po.MyChatRecordPO;

public interface ChatRecordPOMapper {
    int deleteByPrimaryKey(@Param("no") String no, @Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId);

    ChatRecordPO selectByPrimaryKey(@Param("no") String no, @Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId);

    List<ChatRecordPO> selectAll();

    int updateChatRecordReceived(ChatRecordReceivedPO chatRecordReceivedPO);

    int insert(ChatRecordPO record);

    List<MyChatRecordPO> selectBySignFlagAndToUserId(ChatRecordPO record);
}