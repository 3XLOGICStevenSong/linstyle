/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:ChatRecordService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description:
 * @author:郭健
 * @date:2018年12月17日 4:10:58 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.service;

import java.util.List;

import cn.com.dbridge.jtraining.framework.dto.ChatRecordDTO;
import cn.com.dbridge.jtraining.framework.dto.ChatRecordQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.MyChatRecordDTO;
import cn.com.dbridge.jtraining.framework.vo.ChatRecordVO;

/**
 * @ClassName:ChatRecordService
 * @Description:チャットレコードサービス層
 * @author:郭健
 * @date:2018年12月17日 4:10:58 PM
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
public interface ChatRecordService {
    /**
     * 
     * @Title: addChatRecord  
     * @Description: チャット情報を追加する 
     * @param chatRecordDTO 挿入のためのデータ
     * @author 郭健
     * @return 影響を受けるレコードの数
     */
    int addChatRecord(ChatRecordDTO chatRecordDTO);

    /**
     * 
     * @Title: updateChatRecord 
     * @Description: チャット情報を更新する 
     * @param myChatRecordDTO 更新のためのデータ
     * @author 郭健
     * @return 影響を受けるレコードの数
     */
    int updateChatRecord(MyChatRecordDTO myChatRecordDTO);

    /**
     * 
     * @Title: selectChatRecord 
     * @Description: 未署名のメッセージのクエリ
     * @param chatRecordQueryDTO クエリ条件
     * @author 郭健
     * @return 未署名のメッセージ
     */
    List<ChatRecordVO> selectChatRecord(ChatRecordQueryDTO chatRecordQueryDTO);
}
