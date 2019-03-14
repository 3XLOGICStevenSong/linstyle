package com.djb.highway.bus.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import com.djb.highway.bus.dto.BusGroupDTO;
import com.djb.highway.bus.dtoclient.GroupClientDTO;
import com.djb.highway.bus.dtoclient.GroupDetailClientDTO;
import com.djb.highway.bus.entity.BusGroupEntity;
import com.djb.highway.bus.entity.BusUserEntity;
import com.djb.highway.bus.service.IBusGroupService;
import com.djb.highway.bus.service.IBusUserService;
import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.user.entity.UserEntity;

@Controller("/BusGroup")
public class BusGroupAction extends BaseAction {

    @Autowired
    @Qualifier("iBusGroupService")
    private IBusGroupService iBusGroupService;
    @Autowired
    @Qualifier("iBusUserService")
    private IBusUserService iBusUserService;

    public BusGroupAction() {
        super();
    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getGroupList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数Entity
        BusGroupDTO paramDTO = (BusGroupDTO) form;
        // 結果取得用DTO
        GroupClientDTO groupClientDTO = new GroupClientDTO();
        // 参数Entity
        BusGroupEntity paramEntity = new BusGroupEntity();
        // 结果Entity
        List<BusGroupEntity> resultList = null;
        List<GroupDetailClientDTO> groupDetailDTOList = new ArrayList<GroupDetailClientDTO>();

        paramEntity.setBuser_id(paramDTO.getBuser_id());

        try {
            resultList = iBusGroupService.getGroupList(paramEntity);
            if (resultList != null && resultList.size() > 0) {
                for (BusGroupEntity param : resultList) {
                    GroupDetailClientDTO groupDTO = new GroupDetailClientDTO();
                    groupDTO.setIsOwen("true");
                    groupDTO.setGroupid(param.getGroup_id());
                    groupDTO.setName(param.getGroup_name());
                    if (param.getGroup_password() == null) {
                        groupDTO.setPassword("");
                    } else {
                        groupDTO.setPassword(param.getGroup_password());

                    }

                    groupDetailDTOList.add(groupDTO);
                }

            }
            // 获取该用户加入的组
            GroupDetailClientDTO paramGroupDTO = new GroupDetailClientDTO();
            BusUserEntity busUserEntity = new BusUserEntity();
            BusUserEntity busUserResultEntity = null;
            BusGroupEntity groupEntity = new BusGroupEntity();
            busUserEntity.setBuser_id(paramDTO.getBuser_id());
            busUserResultEntity = iBusUserService.getObject(busUserEntity);
            // 如果加入的组不是自己创建的则返回组信息
            if (busUserResultEntity != null && busUserResultEntity.getGroup_id() != null && busUserResultEntity.getGroup_id() != -1) {
                groupEntity.setGroup_id(busUserResultEntity.getGroup_id());
                groupEntity = iBusGroupService.getObject(groupEntity);
                if (groupEntity != null && !groupEntity.getBuser_id().equals(paramDTO.getBuser_id())) {
                    paramGroupDTO.setGroupid(groupEntity.getGroup_id());
                    paramGroupDTO.setName(groupEntity.getGroup_name());
                    if (groupEntity.getGroup_password() == null) {
                        paramGroupDTO.setPassword("");
                    } else {
                        paramGroupDTO.setPassword(groupEntity.getGroup_password());
                    }
                    paramGroupDTO.setIsOwen("false");
                    groupDetailDTOList.add(paramGroupDTO);
                }
            }
            groupClientDTO.setList(groupDetailDTOList);

        } catch (Exception e) {
            groupClientDTO.setReturnCode("-5001");
            paramDTO.setErrFlg(true);
        }
        if (!paramDTO.isErrFlg()) {
            groupClientDTO.setReturnCode("0");
        }

        toJson(response, groupClientDTO);
        return null;

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward addGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        BusGroupDTO paramDTO = (BusGroupDTO) form;
        // 結果取得用DTO
        GroupDetailClientDTO groupDetailClientDTO = new GroupDetailClientDTO();
        BusGroupEntity paramEntity = new BusGroupEntity();

        // paramDTO.setBuser_id(266);
        // paramDTO.setGroup_name("疯狂");
        // paramDTO.setGroup_password("vg");

        try {
            // 组名是否存在
            boolean existGroupName = checkExistGroupName(paramDTO.getGroup_name());
            if (!existGroupName) {
                CommonUtil.reflectClass(paramDTO, paramEntity);
                paramEntity.setVersion(1);
                iBusGroupService.addGroup(paramEntity);
                BusGroupEntity paramBusGroupEntity = new BusGroupEntity();
                paramBusGroupEntity.setGroup_name(paramDTO.getGroup_name());
                paramEntity = iBusGroupService.getObject(paramBusGroupEntity);
                if (paramEntity != null) {
                    groupDetailClientDTO.setGroupid(paramEntity.getGroup_id());
                } else {
                    groupDetailClientDTO.setReturnCode("-5003");
                    paramDTO.setErrFlg(true);
                }
            } else {
                groupDetailClientDTO.setReturnCode("-5004");
                paramDTO.setErrFlg(true);
            }

        } catch (Exception e) {
            groupDetailClientDTO.setReturnCode("-5003");
            paramDTO.setErrFlg(true);
            logger.error("addGroup", e);
        }
        if (!paramDTO.isErrFlg()) {
            groupDetailClientDTO.setReturnCode("0");
        }

        toJson(response, groupDetailClientDTO);
        return null;

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward searchGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        BusGroupDTO paramDTO = (BusGroupDTO) form;
        // 結果取得用DTO
        GroupClientDTO groupClientDTO = new GroupClientDTO();
        BusGroupEntity paramEntity = new BusGroupEntity();
        List<BusGroupEntity> resultList = null;
        List<GroupDetailClientDTO> groupDetailDTOList = new ArrayList<GroupDetailClientDTO>();
        if ("".equals(paramDTO.getGroup_name())) {
            paramEntity.setGroup_name(null);
        } else {
            paramEntity.setGroup_name(paramDTO.getGroup_name());
        }
        // paramEntity.setBuser_id(paramDTO.getBuser_id());
        // paramDTO.setBuser_id(255);
        // paramEntity.setGroup_name("t");
        // do page;
        try {
            resultList = iBusGroupService.searchGroupList(paramEntity);

            if (resultList != null && resultList.size() > 0) {
                for (BusGroupEntity param : resultList) {
                    GroupDetailClientDTO groupDTO = new GroupDetailClientDTO();
                    if (param.getBuser_id().equals(paramDTO.getBuser_id())) {
                        groupDTO.setIsOwen("true");
                    } else {
                        groupDTO.setIsOwen("false");
                    }
                    groupDTO.setGroupid(param.getGroup_id());
                    groupDTO.setName(param.getGroup_name());
                    if (param.getGroup_password() == null) {
                        param.setGroup_password("");
                    }
                    groupDTO.setPassword(param.getGroup_password());
                    groupDetailDTOList.add(groupDTO);
                }

                groupClientDTO.setList(groupDetailDTOList);

            }
        } catch (Exception e) {
            groupClientDTO.setReturnCode("-5002");
            paramDTO.setErrFlg(true);
            logger.error("searchGroup", e);
        }
        if (!paramDTO.isErrFlg()) {
            groupClientDTO.setReturnCode("0");
        }

        toJson(response, groupClientDTO);
        return null;

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward updateGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        BusGroupDTO paramDTO = (BusGroupDTO) form;
        // 結果取得用DTO
        GroupDetailClientDTO groupDetailClientDTO = new GroupDetailClientDTO();
        BusGroupEntity paramEntity = new BusGroupEntity();

        paramEntity.setGroup_id(paramDTO.getGroup_id());

        try {

            // 组名是否存在
            boolean existGroupName = checkExistGroup(paramDTO.getGroup_name(), paramDTO.getGroup_id());
            if (!existGroupName) {
                BusGroupEntity paramBusGroupEntity = null;
                paramBusGroupEntity = iBusGroupService.getObject(paramEntity);
                // 新密码有值旧密码正确更新
                if (!"".equals(paramDTO.getGroup_password()) && paramDTO.getGroup_password() != null) {
                    if (paramBusGroupEntity.getGroup_password().equals(paramDTO.getGroup_old_password())) {
                        CommonUtil.reflectClass(paramDTO, paramEntity);
                        iBusGroupService.updateGroup(paramEntity);
                    } else {
                        groupDetailClientDTO.setReturnCode("-5007");
                        paramDTO.setErrFlg(true);
                    }
                } else {
                    // 不设新密码
                    if ("".equals(paramDTO.getGroup_old_password()) || paramDTO.getGroup_old_password() == null) {
                        paramEntity.setGroup_name(paramDTO.getGroup_name());
                        paramEntity.setGroup_id(paramDTO.getGroup_id());

                        iBusGroupService.updateGroup(paramEntity);
                    } else {
                        // 不更新密码
                        if (paramBusGroupEntity.getGroup_password().equals(paramDTO.getGroup_old_password())) {
                            CommonUtil.reflectClass(paramDTO, paramEntity);
                            // paramEntity.setGroup_password(null);
                            iBusGroupService.updateGroup(paramEntity);
                        } else {
                            groupDetailClientDTO.setReturnCode("-5007");
                            paramDTO.setErrFlg(true);
                        }
                    }

                }

            } else {
                groupDetailClientDTO.setReturnCode("-5006");
                paramDTO.setErrFlg(true);
            }

        } catch (Exception e) {
            groupDetailClientDTO.setReturnCode("-5005");
            paramDTO.setErrFlg(true);
            logger.error("updateGroup", e);
        }
        if (!paramDTO.isErrFlg()) {
            groupDetailClientDTO.setReturnCode("0");
        }

        toJson(response, groupDetailClientDTO);
        return null;

    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getGroupDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        BusGroupDTO paramDTO = (BusGroupDTO) form;
        // 結果取得用DTO
        GroupDetailClientDTO groupDetailClientDTO = new GroupDetailClientDTO();
        BusGroupEntity paramEntity = new BusGroupEntity();
        // 组名是否存在
        // paramEntity.setGroup_id(1);
        // paramDTO.setGroup_id(2);

        try {

            paramEntity.setGroup_id(paramDTO.getGroup_id());
            paramEntity = iBusGroupService.getObject(paramEntity);
            if (paramEntity != null) {
                groupDetailClientDTO.setName(paramEntity.getGroup_name());
                if (paramEntity.getGroup_password() == null) {
                    paramEntity.setGroup_password("");
                }
                groupDetailClientDTO.setPassword(paramEntity.getGroup_password());
            } else {
                groupDetailClientDTO.setReturnCode("-5008");
                paramDTO.setErrFlg(true);
            }
        } catch (Exception e) {
            groupDetailClientDTO.setReturnCode("-5008");
            paramDTO.setErrFlg(true);
            logger.error("getGroupDetail", e);
        }
        if (!paramDTO.isErrFlg()) {
            groupDetailClientDTO.setReturnCode("0");
        }

        toJson(response, groupDetailClientDTO);
        return null;

    }

    private boolean checkExistGroupName(String group_name) {
        try {
            BusGroupEntity busGroupEntity = new BusGroupEntity();
            busGroupEntity.setGroup_name(group_name);
            BusGroupEntity resultEntity = iBusGroupService.getObject(busGroupEntity);

            if (resultEntity == null) {

                return false;
            } else {

                return true;

            }
        } catch (Exception e) {
            return true;
        }

    }

    private boolean checkExistGroup(String group_name, int group_id) {
        try {
            BusGroupEntity busGroupEntity = new BusGroupEntity();
            busGroupEntity.setGroup_name(group_name);
            BusGroupEntity resultEntity = iBusGroupService.getObject(busGroupEntity);

            if (resultEntity == null) {

                return false;
            } else {
                if (resultEntity.getGroup_id().equals(group_id)) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            return true;
        }

    }
}
