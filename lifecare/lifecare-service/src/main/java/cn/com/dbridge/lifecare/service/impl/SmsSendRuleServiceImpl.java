package cn.com.dbridge.lifecare.service.impl;

import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dbridge.lifecare.dao.po.SmsSendRulePO;
import cn.com.dbridge.lifecare.dao.po.WebSmsSendRulePO;
import cn.com.dbridge.lifecare.dao.respository.SmsSendRulePOMapper;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileSmsSendRuleDTO;
import cn.com.dbridge.lifecare.framework.dto.web.WebSmsSendRuleDTO;
import cn.com.dbridge.lifecare.framework.exception.CustomException;
import cn.com.dbridge.lifecare.framework.vo.web.WebSmsSendRuleVO;
import cn.com.dbridge.lifecare.service.SmsSendRuleService;
@Service
public class SmsSendRuleServiceImpl  implements SmsSendRuleService{
    @Autowired
    private SmsSendRulePOMapper smsSendRulePOMapper;

    @Override
    public int deleteSmsSendRuleById(Integer id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int add(MobileSmsSendRuleDTO mobileSmsSendRuleDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public SmsSendRulePO getSmsSendRuleById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SmsSendRulePO> queryAll() {
        List<SmsSendRulePO> smsSendRulePOList = smsSendRulePOMapper.selectAll();
        if (CollectionUtils.isEmpty(smsSendRulePOList)) {
            return null;
        }
        return smsSendRulePOList;
    }

    @Override
    public int updateSmsSendRule(MobileSmsSendRuleDTO mobileSmsSendRuleDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    /****
    @Title: getSmsSendRules
    * @author 王林江
    * @Description: 查询短信发送规则
    * @return
    */
    @Override
    public WebSmsSendRuleVO getSmsSendRules() {
        //处理结果
        WebSmsSendRuleVO rtnVO = null;

        //获得短信发送规则
        List<WebSmsSendRulePO> webSmsSendRulePOList = smsSendRulePOMapper
                .getSmsSendRules();
        if (webSmsSendRulePOList != null && webSmsSendRulePOList.size() > 0) {
            //处理结果
            rtnVO = new WebSmsSendRuleVO();
            for (WebSmsSendRulePO param : webSmsSendRulePOList) {
                //第一条记录的场合
                //位置区分(0:上、1：下)
                if (param.getPositionFlag() != null
                        && param.getPositionFlag() == 0) {
                    //推送标识1(0:都不发、1：客户发 2：服务人员发 3：都发)
                    rtnVO.setSendFlag1(param.getSendFlag());
                    //分钟1
                    rtnVO.setMinutes1(param.getMinutes());
                }

                //第二条记录的场合
                //位置区分(0:上、1：下)
                if (param.getPositionFlag() != null
                        && param.getPositionFlag() == 1) {
                    //推送标识2(0:都不发、1：客户发 2：服务人员发 3：都发)
                    rtnVO.setSendFlag2(param.getSendFlag());
                    //分钟2
                    rtnVO.setMinutes2(param.getMinutes());
                    //跳出循环
                    break;
                }
            }
        }
        //处理结果
        return rtnVO;
    }

    /****
    @Title: saveSmsSendRules
    * @author 王林江
    * @Description: 保存短信发送规则
    * @param webSmsSendRuleDTO 短息推送规则DTO
    * @return
    */
    @Override
    public int saveSmsSendRules(WebSmsSendRuleDTO webSmsSendRuleDTO) {
        //DB更新操作影响数据的条数
        int affectNum = 0;
        //开始服务时间1Check
        //分钟未入力的场合下
        if (webSmsSendRuleDTO.getMinutes1() == null) {
            if (webSmsSendRuleDTO.getSendFlag1() != null) {
                throw new CustomException(
                        "提醒时间1设定不正：客户1或服务人员1选择时，提醒时间1必须输入有效时间(1-9)");
            }
        } else {
            if (webSmsSendRuleDTO.getSendFlag1() == null) {
                throw new CustomException(
                        "客户1或服务人员1设定不正：提醒时间1设定时，客户1或服务人员1至少选择其中一个");
            }
        }
        //开始服务时间2Check
        //分钟未入力的场合下
        if (webSmsSendRuleDTO.getMinutes2() == null) {
            if (webSmsSendRuleDTO.getSendFlag2() != null) {
                throw new CustomException(
                        "提醒时间2设定不正：客户2或服务人员2选择时，提醒时间2必须输入有效时间(1-99)");
            }
        } else {
            if (webSmsSendRuleDTO.getSendFlag2() == null) {
                throw new CustomException(
                        "客户2或服务人员2设定不正：提醒时间2设定时，客户2或服务人员2至少选择其中一个");
            }
        }

        //开始服务时间1 >= 开始服务时间2的场合
        if (webSmsSendRuleDTO.getMinutes1() != null
                && webSmsSendRuleDTO.getMinutes2() != null) {
            if (webSmsSendRuleDTO.getMinutes1() >= webSmsSendRuleDTO
                    .getMinutes2()) {
                throw new CustomException("提醒时间设定不正：提醒时间2必须大于提醒时间1");
            }
        }

        //删除所有记录
        affectNum = smsSendRulePOMapper.deleteAllSmsSendRules();

        //短息推送规则PO
        WebSmsSendRulePO webSmsSendRulePO = new WebSmsSendRulePO();
        //创建人
        webSmsSendRulePO.setCreateBy(webSmsSendRuleDTO.getLoginUserId());
        //更新人
        webSmsSendRulePO.setUpdateBy(webSmsSendRuleDTO.getLoginUserId());

        //第一条记录处理
        if ((webSmsSendRuleDTO.getMinutes1() != null)
                && (webSmsSendRuleDTO.getSendFlag1() != null)) {
            //分钟
            webSmsSendRulePO.setMinutes(webSmsSendRuleDTO.getMinutes1());
            //标识(0:都不发、1：客户发 2：服务人员发 3：都发)
            webSmsSendRulePO.setSendFlag(webSmsSendRuleDTO.getSendFlag1());
            // 位置区分(0:上、1：下)
            webSmsSendRulePO.setPositionFlag(new Byte("0"));
            //第一条记录添加
            affectNum = smsSendRulePOMapper.insertSmsSendRule(webSmsSendRulePO);
        }
        //第二条记录处理
        if ((webSmsSendRuleDTO.getMinutes2() != null)
                && (webSmsSendRuleDTO.getSendFlag2() != null)) {
            //分钟
            webSmsSendRulePO.setMinutes(webSmsSendRuleDTO.getMinutes2());
            //标识(0:都不发、1：客户发 2：服务人员发 3：都发)
            webSmsSendRulePO.setSendFlag(webSmsSendRuleDTO.getSendFlag2());
            // 位置区分(0:上、1：下)
            webSmsSendRulePO.setPositionFlag(new Byte("1"));
            //第二条记录添加
            affectNum = smsSendRulePOMapper.insertSmsSendRule(webSmsSendRulePO);
        }
        return affectNum;
    }
}