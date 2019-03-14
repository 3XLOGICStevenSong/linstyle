package com.djb.highway.user.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.common.util.Constants;
import com.djb.highway.common.util.DateUtil;
import com.djb.highway.common.util.ResourceLocator;
import com.djb.highway.common.util.UploadUtil;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.road.dto.PlazaDTO;
import com.djb.highway.road.dto.TravelPlanDTO;
import com.djb.highway.road.entity.PlazaEntity;
import com.djb.highway.road.entity.TravelPlanEntity;
import com.djb.highway.road.service.IHighWayService;
import com.djb.highway.road.service.IPlazaService;
import com.djb.highway.road.service.ITravelPlanService;
import com.djb.highway.user.dto.UserDeployPicDTO;
import com.djb.highway.user.dtoclient.FavoCountDTO;
import com.djb.highway.user.dtoclient.MoreShareDTO;
import com.djb.highway.user.dtoclient.MoreShareHIdDTO;
import com.djb.highway.user.dtoclient.MoreShareHIdItemDTO;
import com.djb.highway.user.dtoclient.MoreShareItemDTO;
import com.djb.highway.user.dtoclient.MyShareDTO;
import com.djb.highway.user.dtoclient.UserDeployPicClearDTO;
import com.djb.highway.user.entity.UserDeployPicEntity;
import com.djb.highway.user.entity.UserEntity;
import com.djb.highway.user.entity.UserReviewEntity;
import com.djb.highway.user.entity.UserSupportEntity;
import com.djb.highway.user.service.IUserDeployPicService;
import com.djb.highway.user.service.IUserReviewService;
import com.djb.highway.user.service.IUserService;
import com.djb.highway.user.service.IUserSupportService;

@Controller("/UserDeployPic")
public class UserDeployPicAction extends BaseAction {

    @Autowired
    @Qualifier("iUserDeployPicService")
    private IUserDeployPicService iUserDeployPicService;
    @Autowired
    @Qualifier("iUserReviewService")
    private IUserReviewService iUserReviewService;
    @Autowired
    @Qualifier("iUserSupportService")
    private IUserSupportService iUserSupportService;
    @Autowired
    @Qualifier("iUserService")
    private IUserService iUserService;

    @Autowired
    @Qualifier("iTravelPlanService")
    private ITravelPlanService iTravelPlanService;

    @Autowired
    @Qualifier("iPlazaService")
    private IPlazaService iPlazaService;
    @Autowired
    @Qualifier("iHighWayService")
    private IHighWayService iHighWayService;

    public UserDeployPicAction() {
        super();
    }

    // 实现分页的更多车友分享：获取更多车友分享信息
    public ActionForward doGetMoreUserDeployPicPageList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        UserDeployPicDTO paramDTO = (UserDeployPicDTO) form;

        // test数据

        // paramDTO.setStartRow(1);
        // paramDTO.setEndRow(5);
        // paramDTO.setUdp_share_type("1");
        // paramDTO.setType("0");

        // 结果DTO
        UserDeployPicDTO resultDTO = new UserDeployPicDTO();
        // 返回的页面DTO
        MoreShareDTO moreShareDTO = new MoreShareDTO();
        // 参数Entity
        UserDeployPicEntity paramEntity = new UserDeployPicEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果Entity
        List<UserDeployPicEntity> resultEntityList = null;

        // DB操作
        try {
            // 通过type 调用不同的方法，用于获取不同的数据

            String type = paramDTO.getType();
            // test测试数据
            if ("0".equals(type)) {
                // 测试数据：
                // paramDTO.setPlaz_code_start("0525");
                // paramDTO.setPlaz_code_end("0533");
                // 实现更多车友分享（设置起点和终点）,起点和终点可以任意选择，通过查询线路表，获取数据
                // 取得行驶路线
                doGetRoadLine(paramEntity, paramDTO);

                // System.out.println("startRow1---:" + paramDTO.getStartRow());
                // System.out.println("endRow1---" + paramDTO.getEndRow());
                // 使用具有分页功能的方法：
                paramEntity.setStartRow(paramDTO.getStartRow());
                paramEntity.setEndRow(paramDTO.getEndRow());
                resultEntityList = iUserDeployPicService.findMoreUserDeployPicList(paramEntity);

            } else if ("1".equals(type)) {
                // 测试数据：
                // paramDTO.setPlaz_code_start("0525");
                // paramDTO.setPlaz_code_end("0533");
                // 实现更多车友分享（起点和终点为相邻的收费站Code）,将起点和终点拼接成LinkCode，用于检索
                // 收费站Code
                paramEntity.setPlaz_code_start(paramDTO.getPlaz_code_start());
                // 下一收费站Code
                paramEntity.setPlaz_code_end(paramDTO.getPlaz_code_end());

                // 使用分页的检索方法：
                paramEntity.setStartRow(paramDTO.getStartRow());
                paramEntity.setEndRow(paramDTO.getEndRow());
                resultEntityList = iUserDeployPicService.getUserDeployPicPageList(paramEntity);
            }

            if (resultEntityList != null) {
                // List<UserDeployPicDTO> resultListDTO = new
                // ArrayList<UserDeployPicDTO>();
                List<MoreShareItemDTO> resultListDTO = new ArrayList<MoreShareItemDTO>();
                for (UserDeployPicEntity userDeployPicEntity : resultEntityList) {
                    // 使用UserDeployPicDTO 获取用户
                    // UserDeployPicDTO _userDeployPicDTO = new
                    // UserDeployPicDTO();
                    // CommonUtil.reflectClass(userDeployPicEntity,
                    // _userDeployPicDTO);
                    //
                    // // 获取用户昵称
                    // UserEntity userEntity = new UserEntity();
                    // userEntity.setU_id(userDeployPicEntity.getU_id());
                    // UserEntity _userEntity =
                    // iUserService.getObject(userEntity);
                    // UserDTO userDTO = new UserDTO();
                    // userDTO.setU_name(_userEntity.getU_name());
                    // _userDeployPicDTO.setUserDTO(userDTO);
                    //
                    // resultListDTO.add(_userDeployPicDTO);
                    // 使用页面DTO，减少数据冗余
                    MoreShareItemDTO _userDeployPicDTO = new MoreShareItemDTO();

                    // 赋值操作：
                    CommonUtil.reflectClass(userDeployPicEntity, _userDeployPicDTO);
                    _userDeployPicDTO.setEventName(userDeployPicEntity.getUdp_share_type());
                    // 范围：
                    // _userDeployPicDTO.setDirection("白塔堡-北李官");

                    // 处理赞的数量
                    if (userDeployPicEntity.getCommend_count() == null) {
                        _userDeployPicDTO.setZanCount("0");
                    } else {
                        _userDeployPicDTO.setZanCount("" + userDeployPicEntity.getCommend_count());
                    }

                    // 处理时间格式
                    SimpleDateFormat sdf = new SimpleDateFormat(" 今天  HH:mm");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日 HH:mm");

                    if (userDeployPicEntity.getUdp_deploy_time() != null && DateUtil.isToday(userDeployPicEntity.getUdp_deploy_time())) {
                        _userDeployPicDTO.setUdp_deploy_time(sdf.format(userDeployPicEntity.getUdp_deploy_time()));

                    } else {
                        _userDeployPicDTO.setUdp_deploy_time(sdf2.format(userDeployPicEntity.getUdp_deploy_time()));
                    }

                    // 获取用户昵称
                    UserEntity userEntity = new UserEntity();
                    userEntity.setU_id(userDeployPicEntity.getU_id());
                    UserEntity _userEntity = iUserService.getObject(userEntity);
                    _userDeployPicDTO.setU_name(_userEntity.getU_name());

                    resultListDTO.add(_userDeployPicDTO);

                }
                // 初始化页面DTO，减少数据冗余
                moreShareDTO.setList(resultListDTO);
            }
        } catch (Exception e) {
            moreShareDTO.setReturnCode("-1204");
            resultDTO.setErrFlg(true);
            // moreShareDTO.setErrFlg(true);
            // formMessages(moreShareDTO,
            // Constants.MSG_KEY_GET_USERDEPLOYPIC_ERROR, null);
        }
        if (!resultDTO.isErrFlg()) {
            moreShareDTO.setReturnCode("0");
        }
        // android
        toJson(response, moreShareDTO);
        // ;

        return null;

    }

    // 实现分页的更多车友分享：通过高速ID获取更多车友分享信息
    public ActionForward doGetMoreUserDeployPicByHId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        UserDeployPicDTO paramDTO = (UserDeployPicDTO) form;
        // paramDTO.setH_id(6);
        // paramDTO.setStartStakeId("331.9");
        // paramDTO.setEndStakeId("332.9");
        // paramDTO.setStartRow(1);
        // paramDTO.setEndRow(5);

        // 结果DTO
        UserDeployPicDTO resultDTO = new UserDeployPicDTO();
        // 返回的页面DTO
        MoreShareHIdDTO moreShareHIdDTO = new MoreShareHIdDTO();
        // 参数Entity
        UserDeployPicEntity paramEntity = new UserDeployPicEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果Entity
        List<UserDeployPicEntity> resultEntityList = null;

        // DB操作
        try {
            // 通过type 调用不同的方法，用于获取不同的数据
            if (0 == paramEntity.getH_id()) {

                resultEntityList = iUserDeployPicService.getUserDeployPicList();
            } else {
                if (paramEntity.getH_id() != null) {
                    // doGetHighRoadLine(paramEntity, paramDTO);
                    paramEntity.setStartRow(paramDTO.getStartRow());
                    paramEntity.setEndRow(paramDTO.getEndRow());
                    resultEntityList = iUserDeployPicService.findMoreUserDeployPicHIdList(paramEntity);
                }
            }
            if (resultEntityList != null) {
                // List<UserDeployPicDTO> resultListDTO = new
                // ArrayList<UserDeployPicDTO>();
                List<MoreShareHIdItemDTO> resultListDTO = new ArrayList<MoreShareHIdItemDTO>();
                for (UserDeployPicEntity userDeployPicEntity : resultEntityList) {

                    // 使用页面DTO，减少数据冗余
                    MoreShareHIdItemDTO _userDeployPicDTO = new MoreShareHIdItemDTO();

                    // 赋值操作：
                    CommonUtil.reflectClass(userDeployPicEntity, _userDeployPicDTO);
                    _userDeployPicDTO.setEventName(userDeployPicEntity.getUdp_share_type());
                    // 范围：
                    // _userDeployPicDTO.setDirection("白塔堡-北李官");

                    // 处理赞的数量
                    if (userDeployPicEntity.getCommend_count() == null) {
                        _userDeployPicDTO.setZanCount("0");
                    } else {
                        _userDeployPicDTO.setZanCount("" + userDeployPicEntity.getCommend_count());
                    }

                    // 处理时间格式
                    SimpleDateFormat sdf = new SimpleDateFormat(" 今天  HH:mm");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日 HH:mm");

                    if (userDeployPicEntity.getUdp_deploy_time() != null && DateUtil.isToday(userDeployPicEntity.getUdp_deploy_time())) {
                        _userDeployPicDTO.setUdp_deploy_time(sdf.format(userDeployPicEntity.getUdp_deploy_time()));

                    } else {
                        _userDeployPicDTO.setUdp_deploy_time(sdf2.format(userDeployPicEntity.getUdp_deploy_time()));
                    }

                    // 获取用户昵称
                    UserEntity userEntity = new UserEntity();
                    userEntity.setU_id(userDeployPicEntity.getU_id());
                    UserEntity _userEntity = iUserService.getObject(userEntity);
                    _userDeployPicDTO.setU_name(_userEntity.getU_name());

                    resultListDTO.add(_userDeployPicDTO);

                }
                // 初始化页面DTO，减少数据冗余
                moreShareHIdDTO.setList(resultListDTO);
            }

        } catch (Exception e) {
            moreShareHIdDTO.setReturnCode("-1205");
            resultDTO.setErrFlg(true);
            // moreShareDTO.setErrFlg(true);
            // formMessages(moreShareDTO,
            // Constants.MSG_KEY_GET_USERDEPLOYPIC_ERROR, null);
        }
        if (!resultDTO.isErrFlg()) {
            moreShareHIdDTO.setReturnCode("0");
        }
        // android
        toJson(response, moreShareHIdDTO);
        // ;

        return null;

    }

    // 我要发布：实现用户分享信息的功能
    public ActionForward doPublish(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserDeployPicDTO userDeployPicDTO = (UserDeployPicDTO) form;

        UserDeployPicEntity userDeployPicEntity = new UserDeployPicEntity();
        // BeanUtils.copyProperties(userEntity, userDTO);
        // test
        // UserDeployPicDTO userDeployPicDTO = new UserDeployPicDTO();
        BaseDTO baseDTO = new BaseDTO();
        // userDeployPicDTO.setUdp_latitude("123.");
        // userDeployPicDTO.setUdp_longitude("123.25");
        // userDeployPicDTO.setU_id(240);
        // userDeployPicDTO.setSection_id(1L);
        // userDeployPicDTO.setDeploy_msg("test good data");
        // userDeployPicDTO.setPlaz_code_start("0525");
        // userDeployPicDTO.setPlaz_code_end("0533");
        // userDeployPicDTO.setH_id(6);
        // userDeployPicDTO.setStart_stake_id("20");
        // userDeployPicDTO.setEnd_stake_id("40");
        userDeployPicDTO.setLink_code(userDeployPicDTO.getPlaz_code_start() + userDeployPicDTO.getPlaz_code_end());
        CommonUtil.reflectClass(userDeployPicDTO, userDeployPicEntity);
        try {
            if (userDeployPicDTO.getImage() != null) {
                String filePath = UploadUtil.doUpload(request, response, userDeployPicDTO.getImage(), userDeployPicDTO.getU_id());
                userDeployPicEntity.setUdp_pic_path(ResourceLocator.getI18NMessage(this, Constants.MSG_KEY_HOST_ADDRESS) + filePath);
            }

            userDeployPicEntity.setUdp_deploy_time(new Date());
            userDeployPicEntity.setUdp_status(0);

            // 截取字符串
            if (userDeployPicEntity.getUdp_latitude() != null) {
                String preA = userDeployPicEntity.getUdp_latitude().substring(0, userDeployPicEntity.getUdp_latitude().indexOf(".") + 1);
                String tempA = userDeployPicEntity.getUdp_latitude().substring(userDeployPicEntity.getUdp_latitude().indexOf(".") + 1);
                String sufA = "";
                if (tempA.length() > 5) {
                    sufA = userDeployPicEntity.getUdp_latitude().substring(userDeployPicEntity.getUdp_latitude().indexOf(".") + 1,
                                    userDeployPicEntity.getUdp_latitude().indexOf(".") + 6);
                } else {
                    sufA = tempA;
                }
                String lad = preA + sufA;

                if (!"".equals(lad)) {
                    userDeployPicEntity.setUdp_latitude(lad);
                }
            }
            if (userDeployPicEntity.getUdp_longitude() != null) {
                String preO = userDeployPicEntity.getUdp_longitude().substring(0, userDeployPicEntity.getUdp_longitude().indexOf(".") + 1);

                String tempO = userDeployPicEntity.getUdp_longitude().substring(userDeployPicEntity.getUdp_longitude().indexOf(".") + 1);

                String sufO = "";
                if (tempO.length() > 5) {
                    sufO = userDeployPicEntity.getUdp_longitude().substring(userDeployPicEntity.getUdp_longitude().indexOf(".") + 1,
                                    userDeployPicEntity.getUdp_longitude().indexOf(".") + 6);
                } else {
                    sufO = tempO;
                }
                String lon = preO + sufO;
                if (!"".equals(lon)) {

                    userDeployPicEntity.setUdp_longitude(lon);
                }
            }
            iUserDeployPicService.addUserDeployPic(userDeployPicEntity);
            CommonUtil.reflectClass(userDeployPicEntity, baseDTO);
        } catch (Exception e) {
            baseDTO.setErrFlg(true);
            baseDTO.setReturnCode("-662");
            // userDeployPicDTO = new UserDeployPicDTO();
            // userDeployPicDTO.setReturnCode("-662");
            // userDeployPicDTO.setErrFlg(true);
            // formMessages(userDeployPicDTO,
            // Constants.MSG_KEY_INSERT_USERDEPLOYPIC_ERROR, null);

        }
        if (!baseDTO.isErrFlg()) {
            baseDTO.setReturnCode("0");
        }
        // android
        toJson(response, baseDTO);
        // ;
        return null;

    }

    // 实现画面初始化操作：暂未使用
    public ActionForward initMoreUserDeployPicScreen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数Entity
        PlazaEntity paramEntity = new PlazaEntity();
        paramEntity.setH_id(1);
        // 结果DTO
        UserDeployPicDTO resultDTO = new UserDeployPicDTO();
        List<PlazaDTO> tmPlazDTOs = new ArrayList<PlazaDTO>();

        // find All list
        List<PlazaEntity> resultList = iPlazaService.getPlazaList(paramEntity);
        if (resultList != null) {
            for (PlazaEntity _resultEntity : resultList) {
                PlazaDTO _resultDTO = new PlazaDTO();
                CommonUtil.reflectClass(_resultEntity, _resultDTO);
                tmPlazDTOs.add(_resultDTO);
            }
        }
        // 收费站list（初始化下拉列表用）初始化
        resultDTO.setPlazaDTOs(tmPlazDTOs);

        // android
        if (!resultDTO.isErrFlg()) {
            resultDTO.setReturnCode("0");
        }

        toJson(response, resultDTO);
        // ;

        return null;
    }

    // 删除操作：删除用户自己分享的信息
    public ActionForward doDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 参数DTO
        UserDeployPicDTO userDeployPicDTO = (UserDeployPicDTO) form;
        // 返回给页面DTO，返回操作结果
        BaseDTO baseDTO = new BaseDTO();
        UserDeployPicEntity userDeployPicEntity = new UserDeployPicEntity();
        userDeployPicEntity.setUdp_id(userDeployPicDTO.getUdp_id());
        // test
        // UserDeployPicDTO userDeployPicDTO = new UserDeployPicDTO();
        // UserDeployPicEntity userDeployPicEntity = new UserDeployPicEntity();
        // userDeployPicEntity.setUdp_id(4);

        try {

            // 删除多条评论信息
            UserReviewEntity userReview = new UserReviewEntity();
            userReview.setUdp_id(userDeployPicDTO.getUdp_id());
            List<UserReviewEntity> list = iUserReviewService.getUserReviewList(userReview);
            if (list != null) {
                iUserReviewService.deleteUserReviewBatch(list);
            }

            // 删除多条赞的信息
            // 当客户端添加赞的功能后，再实现删除赞的信息
            UserSupportEntity userSupport = new UserSupportEntity();
            userSupport.setUdp_id(userDeployPicDTO.getUdp_id());
            List<UserSupportEntity> listSupport = iUserSupportService.getUserSupportList(userSupport);
            if (listSupport != null) {
                iUserSupportService.deleteUserSupportBatch(listSupport);
            }

            // 删除分享的图片信息
            if (userDeployPicEntity != null) {
                iUserDeployPicService.deleteUserDeployPic(userDeployPicEntity);
            }

        } catch (Exception e) {
            baseDTO.setReturnCode("-661");
            baseDTO.setErrFlg(true);

            // userDeployPicDTO = new UserDeployPicDTO();
            // userDeployPicDTO.setErrFlg(true);
            // formMessages(baseDTO,
            // Constants.MSG_KEY_DELETE_USERDEPLOYPIC_ERROR,
            // null);
        }

        // return MessageInfo
        if (!baseDTO.isErrFlg()) {
            baseDTO.setReturnCode("0");
        }
        // android
        toJson(response, baseDTO);
        // ;
        return null;

    }

    // 我的分享：
    // 用于实现分页功能的我的分享（分页）
    public ActionForward doGetUserDeployPicByPageList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        UserDeployPicDTO paramDTO = (UserDeployPicDTO) form;

        // test
        // paramDTO.setU_id(15);
        // paramDTO.setStartRow(1);
        // paramDTO.setEndRow(5);
        // 结果DTO
        UserDeployPicDTO resultDTO = new UserDeployPicDTO();
        // 初始化页面DTO
        MyShareDTO myShareDTO = new MyShareDTO();
        // 参数Entity
        UserDeployPicEntity paramEntity = new UserDeployPicEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果Entity
        List<UserDeployPicEntity> userDeployPicEntityList = null;
        List<UserDeployPicClearDTO> userDeployPicListDTO = new ArrayList<UserDeployPicClearDTO>();
        // DB操作
        try {
            // 用于测试分页
            paramEntity.setStartRow(paramDTO.getStartRow());
            paramEntity.setEndRow(paramDTO.getEndRow());
            // 使用具有分页功能的方法：
            userDeployPicEntityList = iUserDeployPicService.getUserDeployPicPageList(paramEntity);
            // 操作数据List
            if (userDeployPicEntityList != null) {
                for (UserDeployPicEntity udp : userDeployPicEntityList) {
                    UserDeployPicClearDTO userDeployPicClearDTO = new UserDeployPicClearDTO();
                    // 字段赋值操作
                    CommonUtil.reflectClass(udp, userDeployPicClearDTO);
                    userDeployPicClearDTO.setSection_name(udp.getSection_name());
                    // 处理赞的数量
                    if (udp.getCommend_count() == null) {
                        userDeployPicClearDTO.setZanCount("0");
                    } else {
                        userDeployPicClearDTO.setZanCount("" + udp.getCommend_count());
                    }

                    // 日期格式化处理
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日");

                    if (udp.getUdp_deploy_time() != null && DateUtil.isToday(udp.getUdp_deploy_time())) {
                        userDeployPicClearDTO.setUdp_deploy_time(sdf.format(udp.getUdp_deploy_time()));

                    } else {
                        userDeployPicClearDTO.setUdp_deploy_time(sdf2.format(udp.getUdp_deploy_time()));
                    }
                    if (userDeployPicClearDTO.getUdp_pic_path() == null) {
                        userDeployPicClearDTO.setUdp_pic_path("");
                    }
                    if (userDeployPicClearDTO.getDeploy_msg() == null) {
                        userDeployPicClearDTO.setDeploy_msg("");
                    }
                    if (userDeployPicClearDTO.getPlaz_code_end() == null) {
                        userDeployPicClearDTO.setPlaz_code_end("");
                    }
                    if (userDeployPicClearDTO.getPlaz_code_start() == null) {
                        userDeployPicClearDTO.setPlaz_code_start("");
                    }
                    if (userDeployPicClearDTO.getUdp_share_type() == null) {
                        userDeployPicClearDTO.setUdp_share_type("");
                    }
                    if (userDeployPicClearDTO.getZanCount() == null) {
                        userDeployPicClearDTO.setZanCount("");

                    }
                    if (userDeployPicClearDTO.getSection_name() == null) {
                        userDeployPicClearDTO.setSection_name("");

                    }
                    // userDeployPicClearDTO.getSection_name()
                    userDeployPicListDTO.add(userDeployPicClearDTO);

                }
                myShareDTO.setUserDeployPicDTOs(userDeployPicListDTO);

            }
        } catch (Exception e) {
            myShareDTO.setReturnCode("-660");
            resultDTO.setErrFlg(true);
            // formMessages(myShareDTO,
            // Constants.MSG_KEY_GET_USERDEPLOYPIC_ERROR,
            // null);
        }

        if (!resultDTO.isErrFlg()) {
            myShareDTO.setReturnCode("0");
        }

        // android
        toJson(response, myShareDTO);
        // ;

        return null;
    }

    // // 我的分享：
    // // 用于实现分页功能的我的分享（分页）采用时间戳实现机制
    // public ActionForward doGetUserDeployPicPageList(ActionMapping mapping,
    // ActionForm form, HttpServletRequest request, HttpServletResponse
    // response)
    // throws Exception {
    //
    // // 参数DTO
    // UserDeployPicDTO paramDTO = (UserDeployPicDTO) form;
    //
    // // test
    // // paramDTO.setU_id(15);
    // // paramDTO.setStartRow(1);
    // // paramDTO.setEndRow(5);
    // // 格式化时间戳
    // // paramDTO.setUdp_version_time("1970-01-01 00:00:00");
    // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // paramDTO.setUdp_version_time(paramDTO.getUdp_version_time());
    // // 结果DTO
    // UserDeployPicDTO resultDTO = new UserDeployPicDTO();
    // // 初始化页面DTO
    // MyShareDTO myShareDTO = new MyShareDTO();
    // // 参数Entity
    // UserDeployPicEntity paramEntity = new UserDeployPicEntity();
    // // 参数DTO -> 参数Entity
    // CommonUtil.reflectClass(paramDTO, paramEntity);
    // // 结果Entity
    // UserDeployPicEntity resultEntity = new UserDeployPicEntity();
    //
    // // 存入数据库时,时间类型转换：
    // paramEntity.setUdp_version_time(sf.parse(paramDTO.getUdp_version_time()));
    //
    // List<UserDeployPicClearDTO> userDeployPicListDTO = new
    // ArrayList<UserDeployPicClearDTO>();
    // List<UserDeployPicEntity> userDeployPicEntityList = null;
    //
    // // DB操作
    // try {
    // // 用于测试分页
    // paramEntity.setStartRow(paramDTO.getStartRow());
    // paramEntity.setEndRow(paramDTO.getEndRow());
    // // 使用具有分页功能的方法：
    // System.out.println("-------startrow:" + paramEntity.getStartRow());
    // System.out.println("-------endrow:" + paramEntity.getEndRow());
    // // // 获取数据List
    // // // 当版本号码为0时，获取所有的数据：
    // // if (paramEntity.getUdp_version_time().equals("0")) {
    // // userDeployPicEntityList = iUserDeployPicService
    // // .getUserDeployPicList(paramEntity);
    // // } else {
    // // userDeployPicEntityList = iUserDeployPicService
    // // .getUserDeployPicListByTimeStamp(paramEntity);
    // // }
    //
    // // 获取数据List
    // // 第一次获取数据，当获取所有的数据时，版本号为：1970-01-01 00:00:00
    // // 再次获取数据时，当版本号码不为默认时，获取需要返回的新数据：
    //
    // if (paramEntity.getUdp_version_time() != null) {
    // userDeployPicEntityList =
    // iUserDeployPicService.getUserDeployPicListByTimeStamp(paramEntity);
    //
    // }
    //
    // // 操作数据List
    // if (userDeployPicEntityList != null) {
    // for (UserDeployPicEntity udp : userDeployPicEntityList) {
    // UserDeployPicClearDTO userDeployPicClearDTO = new
    // UserDeployPicClearDTO();
    // // 字段赋值操作
    // CommonUtil.reflectClass(udp, userDeployPicClearDTO);
    // userDeployPicClearDTO.setSection_name(udp.getSection_name());
    // // 处理赞的数量
    // if (udp.getCommend_count() == null) {
    // userDeployPicClearDTO.setZanCount("0");
    // } else {
    // userDeployPicClearDTO.setZanCount("" + udp.getCommend_count());
    // }
    //
    // // 日期格式化处理
    // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    // SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日");
    //
    // if (udp.getUdp_deploy_time() != null &&
    // DateUtil.isToday(udp.getUdp_deploy_time())) {
    // userDeployPicClearDTO.setUdp_deploy_time(sdf.format(udp.getUdp_deploy_time()));
    //
    // } else {
    // userDeployPicClearDTO.setUdp_deploy_time(sdf2.format(udp.getUdp_deploy_time()));
    // }
    // if (userDeployPicClearDTO.getUdp_pic_path() == null) {
    // userDeployPicClearDTO.setUdp_pic_path("");
    // }
    // if (userDeployPicClearDTO.getDeploy_msg() == null) {
    // userDeployPicClearDTO.setDeploy_msg("");
    // }
    // if (userDeployPicClearDTO.getPlaz_code_end() == null) {
    // userDeployPicClearDTO.setPlaz_code_end("");
    // }
    // if (userDeployPicClearDTO.getPlaz_code_start() == null) {
    // userDeployPicClearDTO.setPlaz_code_start("");
    // }
    // if (userDeployPicClearDTO.getUdp_share_type() == null) {
    // userDeployPicClearDTO.setUdp_share_type("");
    // }
    // if (userDeployPicClearDTO.getZanCount() == null) {
    // userDeployPicClearDTO.setZanCount("");
    //
    // }
    // if (userDeployPicClearDTO.getSection_name() == null) {
    // userDeployPicClearDTO.setSection_name("");
    //
    // }
    // // userDeployPicClearDTO.getSection_name()
    // userDeployPicListDTO.add(userDeployPicClearDTO);
    //
    // }
    // myShareDTO.setUserDeployPicDTOs(userDeployPicListDTO);
    //
    // // 给时间戳赋值
    // // 获取最新的更新时间戳：
    // // SimpleDateFormat sf = new
    // // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //
    // // 获取最新时间戳
    // UserDeployPicEntity tempObj =
    // iUserDeployPicService.getMaxTimeStampObject(paramEntity);
    // if (tempObj != null) {
    // // 如果时间戳有更新，更新时间戳
    // if (tempObj.getUdp_deploy_time() != null) {
    // myShareDTO.setUdp_version_time(sf.format(tempObj.getUdp_deploy_time()));
    // }
    //
    // } else {
    // // 如果时间戳不需要更新，使用客户端传输来的时间戳
    // myShareDTO.setUdp_version_time(paramDTO.getUdp_deploy_time());
    //
    // }
    //
    // System.out.println("time:---" + myShareDTO.getUdp_version_time());
    //
    // }
    //
    // } catch (Exception e) {
    // myShareDTO.setReturnCode("-660");
    // resultDTO.setErrFlg(true);
    // // formMessages(myShareDTO,
    // // Constants.MSG_KEY_GET_USERDEPLOYPIC_ERROR,
    // // null);
    // }
    //
    // if (!resultDTO.isErrFlg()) {
    // myShareDTO.setReturnCode("0");
    // }
    //
    // // android
    // toJson(response, myShareDTO);
    // // ;
    //
    // return null;
    // }

    // 更新赞的数量：暂时这样实现
    public ActionForward doUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserDeployPicDTO userDeployPicDTO = (UserDeployPicDTO) form;
        UserDeployPicDTO resultDTO = new UserDeployPicDTO();
        UserDeployPicEntity userDeployPicEntity = new UserDeployPicEntity();
        // BeanUtils.copyProperties(userEntity, userDTO);
        CommonUtil.reflectClass(userDeployPicDTO, userDeployPicEntity);
        FavoCountDTO favoCountDTO = null;
        try {
            // 更新时间戳
            userDeployPicEntity.setUdp_version_time(new Date());
            iUserDeployPicService.updateUserDeployPic(userDeployPicEntity);
            // 获取用户赞的数量
            UserDeployPicEntity resultTemp = iUserDeployPicService.getObject(userDeployPicEntity);
            favoCountDTO = new FavoCountDTO();
            favoCountDTO.setZanCount("" + resultTemp.getCommend_count());

        } catch (Exception e) {
            favoCountDTO.setReturnCode("-1203");
            resultDTO.setErrFlg(true);
            // formMessages(favoCountDTO,
            // Constants.MSG_KEY_UPDATE_USERDEPLOYPIC_ERROR, null);
        }
        if (!resultDTO.isErrFlg()) {
            favoCountDTO.setReturnCode("0");
        }
        // android
        toJson(response, favoCountDTO);
        // ;
        return null;

    }

    private void doGetRoadLine(UserDeployPicEntity paramEntity, UserDeployPicDTO paramDTO) throws Exception {

        // get 行驶路线
        TravelPlanDTO travelPlanDTO = new TravelPlanDTO();
        travelPlanDTO.setCar_type("1");
        travelPlanDTO.setEntry_plaz_code(paramDTO.getPlaz_code_start());
        travelPlanDTO.setExit_plaz_code(paramDTO.getPlaz_code_end());
        TravelPlanEntity travelPlanEntity = getTravelPlanDate(travelPlanDTO);

        String[] roadNames = null;
        String[] linkCodes = null;
        List<String> linkCodeList = null;
        try {
            roadNames = splitRoadLine(travelPlanEntity);
            if (roadNames != null) {
                linkCodeList = new ArrayList<String>();
                for (int i = 0; i < roadNames.length; i++) {
                    if (i < roadNames.length - 1) {
                        linkCodeList.add(roadNames[i] + roadNames[i + 1]);
                    } else {
                        break;
                    }

                }
                linkCodes = new String[linkCodeList.size()];
                linkCodes = (String[]) linkCodeList.toArray(linkCodes);
            }

        } catch (Exception e) {
            linkCodeList = null;
        }
        // paramEntity.setRoadNames(roadNames);
        if (linkCodes != null && linkCodes.length > 0) {
            paramEntity.setLinkCodes(linkCodes);
        } else {
            paramEntity.setLinkCodes(null);
        }

    }

    // 获取行程路线，将路线拆分成String[]形式
    public String[] splitRoadLine(TravelPlanEntity travelPlanEntity) {

        if (travelPlanEntity.getRoad_line() != null) {

            return travelPlanEntity.getRoad_line().split("\\|");

        } else {
            return null;
        }

    }

    public TravelPlanEntity getTravelPlanDate(TravelPlanDTO travelPlanDTO) throws Exception {

        // 参数 travelPlanEntity
        TravelPlanEntity travelPlanEntity = new TravelPlanEntity();
        travelPlanEntity.setCar_type(travelPlanDTO.getCar_type());
        travelPlanEntity.setEntry_plaz_code(travelPlanDTO.getEntry_plaz_code());
        travelPlanEntity.setExit_plaz_code(travelPlanDTO.getExit_plaz_code());

        // 结果 travelPlanEntity
        TravelPlanEntity travelPlanResultEntity = iTravelPlanService.getObject(travelPlanEntity);

        return travelPlanResultEntity;
    }

    // 通过ID获取用户分享信息
    public ActionForward doGetUserDeployInfoById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 参数DTO
        UserDeployPicDTO paramDTO = (UserDeployPicDTO) form;
        // test
        paramDTO.setUdp_id(400);
        // 参数Entity
        UserDeployPicEntity paramEntity = new UserDeployPicEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果Entity
        UserDeployPicEntity userDeployPicEntity = null;
        // 客户端DTO
        UserDeployPicClearDTO userDeployPicClearDTO = new UserDeployPicClearDTO();
        try {
            userDeployPicEntity = iUserDeployPicService.getObject(paramEntity);
            if (userDeployPicEntity != null) {

                // 字段赋值操作
                CommonUtil.reflectClass(userDeployPicEntity, userDeployPicClearDTO);
                userDeployPicClearDTO.setSection_name(userDeployPicEntity.getSection_name());
                // 处理赞的数量
                if (userDeployPicEntity.getCommend_count() == null) {
                    userDeployPicClearDTO.setZanCount("0");
                } else {
                    userDeployPicClearDTO.setZanCount("" + userDeployPicEntity.getCommend_count());
                }

                // 日期格式化处理
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日");

                if (userDeployPicClearDTO.getUdp_deploy_time() != null && DateUtil.isToday(userDeployPicEntity.getUdp_deploy_time())) {
                    userDeployPicClearDTO.setUdp_deploy_time(sdf.format(userDeployPicClearDTO.getUdp_deploy_time()));

                } else if (userDeployPicClearDTO.getUdp_deploy_time() != null) {
                    userDeployPicClearDTO.setUdp_deploy_time(sdf2.format(userDeployPicClearDTO.getUdp_deploy_time()));
                }

                // 获取用户昵称
                if (userDeployPicEntity.getU_id() != null) {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setU_id(userDeployPicEntity.getU_id());
                    userEntity = iUserService.getObject(userEntity);
                    if (userEntity != null) {

                        userDeployPicClearDTO.setU_name(userEntity.getU_name());
                    }

                }

            }

        } catch (Exception e) {
            paramDTO.setErrFlg(true);
            userDeployPicClearDTO.setReturnCode("-3110");
        }

        if (!paramDTO.isErrFlg()) {
            userDeployPicClearDTO.setReturnCode("0");
        }

        // android
        toJson(response, userDeployPicClearDTO);
        // ;
        return null;

    }

    // 通过当前用户的位置，获取用户分享信息
    public ActionForward doGetUserDeployPicPointList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        UserDeployPicDTO paramDTO = (UserDeployPicDTO) form;
        // test
        // paramDTO.setUdp_latitude("41.668383");
        // paramDTO.setUdp_longitude("123.32727");
        // paramDTO.setH_code("G1501");
        // 结果DTO
        UserDeployPicDTO resultDTO = new UserDeployPicDTO();
        // 初始化页面DTO
        MyShareDTO myShareDTO = new MyShareDTO();
        // 参数Entity
        UserDeployPicEntity paramEntity = new UserDeployPicEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果Entity
        List<UserDeployPicEntity> userDeployPicEntityList = null;
        List<UserDeployPicClearDTO> userDeployPicListDTO = null;
        // DB操作
        try {
            // 通过当前经纬度，获取车友分享

            userDeployPicEntityList = iUserDeployPicService.getUserDeployPicPointList(paramEntity);

            // 操作数据List
            if (userDeployPicEntityList != null && userDeployPicEntityList.size() > 0) {

                userDeployPicListDTO = new ArrayList<UserDeployPicClearDTO>();
                for (UserDeployPicEntity udp : userDeployPicEntityList) {
                    UserDeployPicClearDTO userDeployPicClearDTO = new UserDeployPicClearDTO();
                    // 字段赋值操作
                    userDeployPicClearDTO.setUdp_id(udp.getUdp_id());
                    userDeployPicClearDTO.setLatitude_b(udp.getUdp_latitude());
                    userDeployPicClearDTO.setLongitude_b(udp.getUdp_longitude());
                    userDeployPicListDTO.add(userDeployPicClearDTO);

                }
                myShareDTO.setUserDeployPicDTOs(userDeployPicListDTO);

            }
        } catch (Exception e) {
            myShareDTO.setReturnCode("-3050");
            resultDTO.setErrFlg(true);
        }

        if (!resultDTO.isErrFlg()) {
            myShareDTO.setReturnCode("0");
        }

        // android
        toJson(response, myShareDTO);
        // ;

        return null;
    }

    // 新添加接口
    // 首页车友分享： 实现分页的更多车友分享：通过高速ID获取更多车友分享信息
    public ActionForward doGetMoreUserDeployPicforHome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        UserDeployPicDTO paramDTO = (UserDeployPicDTO) form;
        // paramDTO.setH_id(0);
        // paramDTO.setStartStakeId("331.9");
        // paramDTO.setEndStakeId("332.9");
        // paramDTO.setStartRow(1);
        // paramDTO.setEndRow(5);

        // 结果DTO
        UserDeployPicDTO resultDTO = new UserDeployPicDTO();
        // 返回的页面DTO
        MoreShareHIdDTO moreShareHIdDTO = new MoreShareHIdDTO();
        // 参数Entity
        UserDeployPicEntity paramEntity = new UserDeployPicEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);
        // 结果Entity
        List<UserDeployPicEntity> resultEntityList = null;

        // DB操作
        try {
            // 通过type 调用不同的方法，用于获取不同的数据
            if (0 == paramEntity.getH_id()) {
                paramEntity.setStartRow(paramDTO.getStartRow());
                paramEntity.setEndRow(paramDTO.getEndRow());
                resultEntityList = iUserDeployPicService.findListbyPage(paramEntity);
            } else {
                if (paramEntity.getH_id() != null) {
                    // doGetHighRoadLine(paramEntity, paramDTO);
                    paramEntity.setStartRow(paramDTO.getStartRow());
                    paramEntity.setEndRow(paramDTO.getEndRow());
                    resultEntityList = iUserDeployPicService.findMoreUserDeployPicHIdList(paramEntity);
                }
            }
            if (resultEntityList != null) {
                // List<UserDeployPicDTO> resultListDTO = new
                // ArrayList<UserDeployPicDTO>();
                List<MoreShareHIdItemDTO> resultListDTO = new ArrayList<MoreShareHIdItemDTO>();
                for (UserDeployPicEntity userDeployPicEntity : resultEntityList) {

                    // 使用页面DTO，减少数据冗余
                    MoreShareHIdItemDTO _userDeployPicDTO = new MoreShareHIdItemDTO();

                    // 赋值操作：
                    CommonUtil.reflectClass(userDeployPicEntity, _userDeployPicDTO);
                    _userDeployPicDTO.setEventName(userDeployPicEntity.getUdp_share_type());
                    // 范围：
                    // _userDeployPicDTO.setDirection("白塔堡-北李官");

                    // 处理赞的数量
                    if (userDeployPicEntity.getCommend_count() == null) {
                        _userDeployPicDTO.setZanCount("0");
                    } else {
                        _userDeployPicDTO.setZanCount("" + userDeployPicEntity.getCommend_count());
                    }

                    // 处理时间格式
                    SimpleDateFormat sdf = new SimpleDateFormat(" 今天  HH:mm");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日 HH:mm");

                    if (userDeployPicEntity.getUdp_deploy_time() != null && DateUtil.isToday(userDeployPicEntity.getUdp_deploy_time())) {
                        _userDeployPicDTO.setUdp_deploy_time(sdf.format(userDeployPicEntity.getUdp_deploy_time()));

                    } else {
                        _userDeployPicDTO.setUdp_deploy_time(sdf2.format(userDeployPicEntity.getUdp_deploy_time()));
                    }

                    // 获取用户昵称
                    UserEntity userEntity = new UserEntity();
                    userEntity.setU_id(userDeployPicEntity.getU_id());
                    UserEntity _userEntity = iUserService.getObject(userEntity);
                    _userDeployPicDTO.setU_name(_userEntity.getU_name());

                    resultListDTO.add(_userDeployPicDTO);

                }
                // 初始化页面DTO，减少数据冗余
                moreShareHIdDTO.setList(resultListDTO);
            }

        } catch (Exception e) {
            moreShareHIdDTO.setReturnCode("-1205");
            resultDTO.setErrFlg(true);
            // moreShareDTO.setErrFlg(true);
            // formMessages(moreShareDTO,
            // Constants.MSG_KEY_GET_USERDEPLOYPIC_ERROR, null);
        }
        if (!resultDTO.isErrFlg()) {
            moreShareHIdDTO.setReturnCode("0");
        }
        // android
        toJson(response, moreShareHIdDTO);
        // ;

        return null;

    }

}
