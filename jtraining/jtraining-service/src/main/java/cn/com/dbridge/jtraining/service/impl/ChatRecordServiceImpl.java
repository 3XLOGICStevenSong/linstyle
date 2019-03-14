/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:ChatRecordServiceImpl.java
 * @Package cn.com.dbridge.jtraining.service.impl
 * @Description:チャットレコードサービス層の実装
 * @author:郭健
 * @date:2018年12月17日、4:12:31 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.com.dbridge.jtraining.dao.po.ChatRecordPO;
import cn.com.dbridge.jtraining.dao.po.ChatRecordReceivedPO;
import cn.com.dbridge.jtraining.dao.po.MyChatRecordPO;
import cn.com.dbridge.jtraining.dao.respository.ChatRecordPOMapper;
import cn.com.dbridge.jtraining.framework.dto.ChatRecordDTO;
import cn.com.dbridge.jtraining.framework.dto.ChatRecordQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.MyChatRecordDTO;
import cn.com.dbridge.jtraining.framework.vo.ChatRecordVO;
import cn.com.dbridge.jtraining.service.ChatRecordService;

/**
 * @ClassName:ChatRecordServiceImpl
 * @Description:チャットレコードサービス層の実装
 * @author:郭健
 * @date:2018年12月17日、4:12:31 PM
 *
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Service
public class ChatRecordServiceImpl implements ChatRecordService {

    @Autowired
    private ChatRecordPOMapper chatRecordPOMapper;

    /**   
     * Title: addChatRecord
     * Description:チャット情報を追加する
     * @param chatRecordDTO 挿入のためのデータ
     * @return 影響を受けるレコードの数
     * @author 郭健
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see cn.com.dbridge.jtraining.service.ChatRecordService#addChatRecord(cn.com.dbridge.jtraining.framework.dto.ChatRecordDTO)   
     */
    @Override
    public int addChatRecord(ChatRecordDTO chatRecordDTO) {
        int affectNum = 0;
        ChatRecordPO chatRecordPO = new ChatRecordPO();
        if (null != chatRecordDTO) {
            BeanUtils.copyProperties(chatRecordDTO, chatRecordPO);
            affectNum = chatRecordPOMapper.insert(chatRecordPO);
        }
        return affectNum;
    }
    /**   
     * Title: updateChatRecord  
     * Description:チャット情報を更新する
     * @param chatRecordDTO 更新のためのデータ
     * @return 影響を受けるレコードの数
     * @author 郭健
     * @see cn.com.dbridge.jtraining.service.ChatRecordService#updateChatRecord(cn.com.dbridge.jtraining.framework.dto.ChatRecordDTO)   
     */
    @Override
    public int updateChatRecord(MyChatRecordDTO myChatRecordDTO) {
        int affectNum = 0;
        ChatRecordReceivedPO chatRecordReceivedPO = new ChatRecordReceivedPO();
        if (null != myChatRecordDTO) {
            BeanUtils.copyProperties(myChatRecordDTO, chatRecordReceivedPO);
            affectNum = chatRecordPOMapper.updateChatRecordReceived(chatRecordReceivedPO);
        }
        return affectNum;
    }
    /**   
     * Title: selectBySignFlag
     * Description:未署名のメッセージのクエリ
     * @param chatRecordQueryDTO クエリ条件
     * @return 未署名のメッセージ
     * @author 郭健
     * @see cn.com.dbridge.jtraining.service.ChatRecordService#selectBySignFlag(cn.com.dbridge.jtraining.framework.dto.ChatRecordQueryDTO)   
     */
    @Override
    public List<ChatRecordVO> selectChatRecord(
            ChatRecordQueryDTO chatRecordQueryDTO) {
        List<ChatRecordVO> chatRecordVOList = new ArrayList<ChatRecordVO>();
        ChatRecordPO chatRecordPO = new ChatRecordPO();
        if (null != chatRecordQueryDTO) {
            BeanUtils.copyProperties(chatRecordQueryDTO, chatRecordPO);
            List<MyChatRecordPO> myChatRecordPOList = chatRecordPOMapper
                    .selectBySignFlagAndToUserId(chatRecordPO);
            ChatRecordVO chatRecordVO = null;
            if (!CollectionUtils.isEmpty(myChatRecordPOList)) {
                for (MyChatRecordPO myChatRecordPO : myChatRecordPOList) {
                    chatRecordVO = new ChatRecordVO();
                    BeanUtils.copyProperties(myChatRecordPO, chatRecordVO);
                    chatRecordVOList.add(chatRecordVO);
                }
            }
        }
        return chatRecordVOList;
    }
}
