package cn.com.dbridge.lifecare.dao.respository;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.SmsSendRulePO;
import cn.com.dbridge.lifecare.dao.po.WebSmsSendRulePO;

public interface SmsSendRulePOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsSendRulePO record);

    SmsSendRulePO selectByPrimaryKey(Integer id);

    List<SmsSendRulePO> selectAll();

    int updateByPrimaryKey(SmsSendRulePO record);

    /**
     * 
     * @Title: getSmsSendRules 
     * @author 王林江
     * @Description: 查询短信发送规则
     * @return
     */
    List<WebSmsSendRulePO> getSmsSendRules();

    /**
     * 
     * @Title: deleteAllSmsSendRules 
     * @author 王林江
     * @Description: 删除所有短信发送规则
     * @return
     */
    int deleteAllSmsSendRules();

    /**
     * 
     * @Title: insertSmsSendRule 
     * @author 王林江
     * @Description: 追加短信发送规则
     * @param webSmsSendRulePO
     * @return
     */
    int insertSmsSendRule(WebSmsSendRulePO webSmsSendRulePO);
}