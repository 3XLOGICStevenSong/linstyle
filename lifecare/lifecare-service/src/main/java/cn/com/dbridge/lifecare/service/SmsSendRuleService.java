package cn.com.dbridge.lifecare.service;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.SmsSendRulePO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileSmsSendRuleDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebSmsSendRuleDTO;
import cn.com.dbridge.lifecare.framework.vo.web.WebSmsSendRuleVO;

public interface SmsSendRuleService {
    int deleteSmsSendRuleById(Integer id);

    int add(MobileSmsSendRuleDTO mobileSmsSendRuleDTO);

    SmsSendRulePO getSmsSendRuleById(Integer id);

    List<SmsSendRulePO> queryAll();

    int updateSmsSendRule(MobileSmsSendRuleDTO mobileSmsSendRuleDTO);

    /**
     * 
     * @Title: getSmsSendRules
     * @author 王林江
     * @Description: 查询短信发送规则
     * @return
     */
    WebSmsSendRuleVO getSmsSendRules();

    /**
     * 
     * @Title: saveSmsSendRules
     * @author 王林江
     * @Description: 保存短信发送规则
     * @param webSmsSendRuleDTO 短息推送规则DTO
     * @return
     */
    int saveSmsSendRules(WebSmsSendRuleDTO webSmsSendRuleDTO);
}