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
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.road.dtoclient.BaseClientDTO;
import com.djb.highway.user.dto.OftenUsedRoadDTO;
import com.djb.highway.user.dtoclient.MyStoreClientDTO;
import com.djb.highway.user.dtoclient.MyStoreDetailDTO;
import com.djb.highway.user.entity.OftenUsedRoadEntity;
import com.djb.highway.user.service.IOftenUsedRoadService;

@Controller("/OftenUsedRoad")
public class OftenUsedRoadAction extends BaseAction {

    @Autowired
    @Qualifier("iOftenUsedRoadService")
    private IOftenUsedRoadService iOftenUsedRoadService;

    /**
     * <P>
     * コンストラクタ
     * </P>
     */
    public OftenUsedRoadAction() {
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
     * @param myStoreDetailDTO
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    // 收藏一览,不做分页操作
    @SuppressWarnings("unchecked")
    public ActionForward doGetOftenUsedRoadList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        OftenUsedRoadDTO oftenUsedRoadDTO = (OftenUsedRoadDTO) form;
        // test
        // oftenUsedRoadDTO.setU_id(11);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // oftenUsedRoadDTO.setOur_version_time("2013-07-22 22:29:28");
        // 参数Entity
        OftenUsedRoadEntity oftenUsedRoadEntity = new OftenUsedRoadEntity();
        int uId = oftenUsedRoadDTO.getU_id();
        oftenUsedRoadEntity.setU_id(uId);
        // 存入数据库时,时间类型转换：
        oftenUsedRoadEntity.setOur_version_time(sf.parse(oftenUsedRoadDTO.getOur_version_time()));
        // 結果取得用DTO
        MyStoreClientDTO myStoreClientDTO = new MyStoreClientDTO();
        OftenUsedRoadDTO resultDTO = new OftenUsedRoadDTO();
        List<MyStoreDetailDTO> myStoreDetailDTOList = new ArrayList<MyStoreDetailDTO>();
        List<OftenUsedRoadEntity> resultList = null;
        // 获取数据

        try {
            // 获取数据List
            // 第一次获取数据，当获取所有的数据时，版本号为：1970-01-01 00:00:00
            // 再次获取数据时，当版本号码不为默认时，获取需要返回的新数据：

            if (oftenUsedRoadEntity.getOur_version_time() != null) {
                resultList = iOftenUsedRoadService.getOftenUsedRoadListByTimeStamp(oftenUsedRoadEntity);

                // System.out.println("-----");

            }
            if (resultList != null) {
                for (OftenUsedRoadEntity our : resultList) {
                    MyStoreDetailDTO myStoreDetailDTO = new MyStoreDetailDTO();
                    myStoreDetailDTO.setOur_id(our.getOur_id());
                    myStoreDetailDTO.setU_id(our.getU_id());
                    myStoreDetailDTO.setH_id(our.getH_id());
                    myStoreDetailDTO.setOur_road_name(our.getOur_road_name());
                    myStoreDetailDTO.setPlaz_code_start(our.getPlaz_code_start());
                    myStoreDetailDTO.setPlaz_code_end(our.getPlaz_code_end());
                    myStoreDetailDTO.setRoad_type(our.getRoad_type());
                    // myStoreDetailDTO.setTotal_money(our.getTotal_money());
                    // myStoreDetailDTO.setTotal_time(our.getTotal_time());
                    // myStoreDetailDTO.setTotal_distance(our.getTotal_distance());
                    myStoreDetailDTO.setCar_type(our.getCar_type());
                    myStoreDetailDTOList.add(myStoreDetailDTO);
                }
            }
            myStoreClientDTO.setList(myStoreDetailDTOList);

            // 获取最新的更新时间戳：
            OftenUsedRoadEntity temp = iOftenUsedRoadService.getMaxTimeStampObject(oftenUsedRoadEntity);
            // SimpleDateFormat sf = new
            // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if (temp.getOur_reg_time() != null) {
                myStoreClientDTO.setOur_version_time(sf.format(temp.getOur_reg_time()));

            }

        } catch (Exception e) {
            myStoreClientDTO.setReturnCode("-632");
            resultDTO.setErrFlg(true);
            // myStoreClientDTO.setErrFlg(true);
            // formMessages(resultDTO,
            // Constants.MSG_KEY_GET_OFTENUSEDROAD_ERROR, null);

        }
        if (!oftenUsedRoadDTO.isErrFlg()) {
            myStoreClientDTO.setReturnCode("0");
        }
        toJson(response, myStoreClientDTO);
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
    // 添加收藏记录
    public ActionForward doInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        OftenUsedRoadDTO oftenUsedRoadDTO = (OftenUsedRoadDTO) form;
        // test
        // oftenUsedRoadDTO.setU_id(5);
        // oftenUsedRoadDTO.setH_id(3);
        // oftenUsedRoadDTO.setPlaz_code_start("0542");
        // oftenUsedRoadDTO.setPlaz_code_end("0546");
        // oftenUsedRoadDTO.setCar_type("1");
      
        // 参数Entity
        OftenUsedRoadEntity oftenUsedRoadEntity = new OftenUsedRoadEntity();
        CommonUtil.reflectClass(oftenUsedRoadDTO, oftenUsedRoadEntity);
        // oftenUsedRoadEntity.setU_id(oftenUsedRoadDTO.getU_id());
        // oftenUsedRoadEntity.setPlaz_code_start(oftenUsedRoadDTO.getPlaz_code_start());
        // oftenUsedRoadEntity.setOur_direction(oftenUsedRoadDTO.getOur_direction());
        // oftenUsedRoadEntity.setPlaz_code_end(oftenUsedRoadDTO.getPlaz_code_end());
        // oftenUsedRoadEntity.setTotal_distance(oftenUsedRoadDTO.getTotal_distance());
        // oftenUsedRoadEntity.setTotal_time(oftenUsedRoadDTO.getTotal_time());
        // oftenUsedRoadEntity.setH_id(oftenUsedRoadDTO.getH_id());
        // oftenUsedRoadEntity.setTotal_money(oftenUsedRoadDTO.getTotal_money());
        oftenUsedRoadEntity.setCar_type(oftenUsedRoadDTO.getCar_type());
        oftenUsedRoadEntity.setRoad_type(oftenUsedRoadDTO.getRoad_type());
         BaseClientDTO baseClientDTO =new  BaseClientDTO();
        
        // 結果取得用DTO
        OftenUsedRoadDTO resultDTO = new OftenUsedRoadDTO();
        // 添加数据
        try {
            // Road_type类型：当为1时，为跳转至道路详情画面；当为2时，为跳转至行程规划画面
            // resultDTO.setRoad_type("1");
            oftenUsedRoadEntity.setOur_reg_time(new Date());
            iOftenUsedRoadService.addOftenUsedRoad(oftenUsedRoadEntity);

        } catch (Exception e) {
        	baseClientDTO.setReturnCode("-630");
            resultDTO.setErrFlg(true);
            // formMessages(resultDTO,
            // Constants.MSG_KEY_INSERT_USERDEPLOYPIC_ERROR, null);

        }
        if (!resultDTO.isErrFlg()) {
        	baseClientDTO.setReturnCode("0");
        }
        // resultDTO.setRoad_type("1");
        toJson(response,baseClientDTO);
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
    // 删除收藏记录（单条记录）
    public ActionForward doDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        OftenUsedRoadDTO oftenUsedRoadDTO = (OftenUsedRoadDTO) form;
        BaseClientDTO baseClientDTO=new BaseClientDTO();
        // test
        // oftenUsedRoadDTO.setU_id(11);
        // oftenUsedRoadDTO.setPlaz_code_start("0457");
        // oftenUsedRoadDTO.setPlaz_code_end("0232");
        // oftenUsedRoadDTO.setRoad_type("2");
        // 参数Entity
        OftenUsedRoadEntity oftenUsedRoadEntity = new OftenUsedRoadEntity();
        oftenUsedRoadEntity.setU_id(oftenUsedRoadDTO.getU_id());
        oftenUsedRoadEntity.setPlaz_code_start(oftenUsedRoadDTO.getPlaz_code_start());
        oftenUsedRoadEntity.setPlaz_code_end(oftenUsedRoadDTO.getPlaz_code_end());
        oftenUsedRoadEntity.setRoad_type(oftenUsedRoadDTO.getRoad_type());

        // CommonUtil.reflectClass(oftenUsedRoadDTO, oftenUsedRoadEntity);
        // 結果取得用DTO
        OftenUsedRoadDTO resultDTO = new OftenUsedRoadDTO();
        // 添加数据
        try {
            iOftenUsedRoadService.deleteOftenUsedRoad(oftenUsedRoadEntity);
        } catch (Exception e) {
        	baseClientDTO.setReturnCode("-631");
            resultDTO.setErrFlg(true);
            // formMessages(resultDTO,
            // Constants.MSG_KEY_DELETE_OFTENUSEDROAD_ERROR, null);

        }
        if (!resultDTO.isErrFlg()) {
        	baseClientDTO.setReturnCode("0");
        }
        toJson(response, baseClientDTO);
        return null;

    }

  

}
