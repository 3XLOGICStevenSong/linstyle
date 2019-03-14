/**
 * All rights Reserved, Designed By www.tydic.com
 * 
 * @Title:ChatRecordController.java
 * @Package cn.com.dbridge.jtraining.rest
 * @Description:チャット記録情報制御層
 * @author:郭健
 * @date:2018年12月17日 4:15:39 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.jtraining.framework.base.Result;
import cn.com.dbridge.jtraining.framework.dto.ChatRecordDTO;
import cn.com.dbridge.jtraining.framework.dto.ChatRecordQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.MyChatRecordDTO;
import cn.com.dbridge.jtraining.framework.vo.ChatRecordVO;
import cn.com.dbridge.jtraining.service.ChatRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName:ChatRecordController
 * @Description:チャット記録情報制御層
 * @author:郭健
 * @date:2018年12月17日 4:15:39 PM
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁じられています。
 */
@RestController
@Api(tags = "チャットの履歴")
@RequestMapping(value = "/v1/api")
public class ChatRecordController {
    @Autowired
    private ChatRecordService chatRecordService;

    /**
     * 
     * @Title: addChatRecord 
     * @Description: チャット履歴を追加する
     * @param chatRecordDTO 追加のための情報
     * @author:郭健
     * @return 動作ステータスとプロンプト
     */
    @ApiOperation(value = "チャット履歴を追加する", notes = "チャット履歴を追加する")
    @PostMapping("/chat/add")
    @RequiresAuthentication
    public Result<Object> addChatRecord(
            @RequestBody ChatRecordDTO chatRecordDTO) {
        return new Result<Object>(HttpStatus.OK.value(), "チャット履歴が正常に追加された",
                chatRecordService.addChatRecord(chatRecordDTO));
    }

    /**
     * 
     * @Title: updateChatRecord 
     * @Description: チャット履歴を更新する
     * @param myChatRecordDTO 更新のための情報
     * @author:郭健
     * @return 動作ステータスとプロンプト
     */
    @ApiOperation(value = "チャット履歴を更新する", notes = "チャット履歴を更新する")
    @PutMapping("/chat/update")
    @RequiresAuthentication
    public Result<Object> updateChatRecord(
            @RequestBody MyChatRecordDTO myChatRecordDTO) {
        return new Result<Object>(HttpStatus.OK.value(), "チャット履歴が正常に更新された",
                chatRecordService.updateChatRecord(myChatRecordDTO));
    }

    /**
     * 
     * @Title: selectChatRecord 
     * @Description: チャット履歴のクエリ
     * @param chatRecordQueryDTO クエリ条件
     * @author:郭健
     * @return 動作ステータスとプロンプト
     */
    @ApiOperation(value = "チャット履歴のクエリ", notes = "チャット履歴のクエリ")
    @PostMapping("/chat/query")
    @RequiresAuthentication
    public Result<List<ChatRecordVO>> selectChatRecord(@Valid
            @RequestBody ChatRecordQueryDTO chatRecordQueryDTO) {
        return new Result<List<ChatRecordVO>>(HttpStatus.OK.value(),
                "チャットクエリレコードが成功しました",
                chatRecordService.selectChatRecord(chatRecordQueryDTO));
    }

}
