//package cn.com.dbridge.lifecare.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import cn.com.dbridge.lifecare.dao.respository.SmsMsgMapper;
//import cn.com.dbridge.lifecare.dao.respository.SmsSendRulePOMapper;
//import cn.com.dbridge.lifecare.dao.respository.TaskPOMapper;
//import cn.com.dbridge.lifecare.dao.respository.UserPOMapper;
//import cn.com.dbridge.lifecare.framework.dto.web.SmsMsgTaskDTO;
//import cn.com.dbridge.lifecare.service.SmsMsgService;
//
//public class SmsMsgServiceImpl implements SmsMsgService {
//    @Autowired
//    private SmsSendRulePOMapper smsSendRulePOMapper;
//    @Autowired
//    private SmsMsgMapper smsMsgMapper;
//    @Autowired
//    private TaskPOMapper taskPOMapper;
//    @Autowired
//    private UserPOMapper userPOMapper;
//    /**   
//     * Title: addSmsMsg  
//     * Description:  
//     * @param smsMsgDTO
//     * @return   
//     * @see cn.com.dbridge.lifecare.service.SmsMsgService#addSmsMsg(cn.com.dbridge.lifecare.framework.dto.web.SmsMsgDTO)   
//     */
//    @Override
//    public int addSmsMsg(SmsMsgTaskDTO smsMsgTaskDTO) {
//        return 0;
////        Integer taskId = smsMsgTaskDTO.getTaskId();
////        TaskPO taskPO = new TaskPO();
////        taskPO.setId(taskId);
////        List<TaskPO> taskPOList = taskPOMapper.selectTask(taskPO);
////        if (!CollectionUtils.isEmpty(taskPOList)) {
////            TaskPO taskPOtemp = taskPOList.get(0);
////            Integer customId = taskPOtemp.getCustomId();
////            if (null != customId) {
////                UserPO customPO = userPOMapper.selectByPrimaryKey(customId);
////                String customRealName = customPO.getRealName();
////                String customMobile = customPO.getMobile();
////            }
////            Integer servicePersonId = taskPOtemp.getServicePersonId();
////            if (null != servicePersonId) {
////                UserPO servicePersonPO = userPOMapper
////                        .selectByPrimaryKey(servicePersonId);
////                String servicePersonRealName = servicePersonPO.getRealName();
////                String servicePersonMobie = servicePersonPO.getMobile();
////            }
////
////            List<SmsSendRulePO> smsSendRulePOList = smsSendRulePOMapper
////                    .selectAll();
////            if (!CollectionUtils.isEmpty(smsSendRulePOList)) {
////                for (SmsSendRulePO smsSendRulePO : smsSendRulePOList) {
////                    int count = 0;
////                    Byte sendFlag = smsSendRulePO.getSendFlag();
////                    Integer minutes = smsSendRulePO.getMinutes();
////                    if (sendFlag == 1) {
////                        count++;
////                    } else if (sendFlag == 2) {
////                        count++;
////                    } else if (sendFlag == 3) {
////                        count += 2;
////                    }
////                    for (int i = 0; i < count; i++) {
////                        if (sendFlag == 1) {
////                            //插入一条客户的
////                            Byte type = 1;
////                            SmsMsgPO SmsMsgPO = new SmsMsgPO();
////                            BeanUtils.copyProperties(smsMsgDTO, SmsMsgPO);
////                            SmsMsgPO.setType(type);
////                            smsMsgMapper.insert(SmsMsgPO);
////                        } else if (sendFlag == 2) {
////                            //插入一条服务人员的
////                            Byte type = 0;
////                            SmsMsgPO SmsMsgPO = new SmsMsgPO();
////                            BeanUtils.copyProperties(smsMsgDTO, SmsMsgPO);
////                            SmsMsgPO.setType(type);
////                            smsMsgMapper.insert(SmsMsgPO);
////                        } else if (sendFlag == 3 && i != 0) {
////                            //插入2条
////                            Byte type = 0;
////                            SmsMsgPO SmsMsgPO = new SmsMsgPO();
////                            BeanUtils.copyProperties(smsMsgDTO, SmsMsgPO);
////                            SmsMsgPO.setType(type);
////                            smsMsgMapper.insert(SmsMsgPO);
////                        } else if (sendFlag == 3 && i == 0) {
////                            Byte type = 1;
////                            SmsMsgPO SmsMsgPO = new SmsMsgPO();
////                            BeanUtils.copyProperties(smsMsgDTO, SmsMsgPO);
////                            SmsMsgPO.setType(type);
////                            smsMsgMapper.insert(SmsMsgPO);
////                        }
////                    }
////                }
////            }
//        //  }
//    }
//}
