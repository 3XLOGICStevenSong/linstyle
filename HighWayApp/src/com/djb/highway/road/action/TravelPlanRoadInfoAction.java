package com.djb.highway.road.action;

import java.io.PrintWriter;
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

import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.road.dto.travel.TravelElementDTO;
import com.djb.highway.road.dto.travel.TravelNodeDTO;
import com.djb.highway.road.dto.travel.TravelPlanParamDTO;
import com.djb.highway.road.dto.travel.TravelScreenDTO;
import com.djb.highway.road.dtoclient.OverpassClientDTO;
import com.djb.highway.road.dtoclient.PlazaClientDTO;
import com.djb.highway.road.dtoclient.TravelElementClientDTO;
import com.djb.highway.road.dtoclient.TravelNodeClientDTO;
import com.djb.highway.road.dtoclient.TravelScreenClientDTO;
import com.djb.highway.road.service.ITravelPlanRelultService;
import com.djb.highway.user.entity.OftenUsedRoadEntity;
import com.djb.highway.user.service.IOftenUsedRoadService;

@Controller("/TravelPlanRoadInfo")
public class TravelPlanRoadInfoAction extends BaseAction {

    @Autowired
    @Qualifier("iTravelPlanRelultService")
    private ITravelPlanRelultService iTravelPlanRelultService;

    @Autowired
    @Qualifier("iOftenUsedRoadService")
    private IOftenUsedRoadService iOftenUsedRoadService;

    public TravelPlanRoadInfoAction() {
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
    public ActionForward doGetTravelPlanData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // long lngTime = new Date().getTime();
        // PrintWriter out = response.getWriter();
        TravelScreenDTO dto = null;

        // 返回的页面DTO
        TravelScreenClientDTO travelScreenClientDTO = new TravelScreenClientDTO();
        try {

            TravelPlanParamDTO travelPlanParamDTO = (TravelPlanParamDTO) form;

            // ////////////// test/////////////////////////
            // TravelPlanParamDTO travelPlanParamDTO = new TravelPlanParamDTO();
            // // /////////////////
            // travelPlanParamDTO.setScreenType(2);
            // travelPlanParamDTO.setCar_type("1");
            // travelPlanParamDTO.setEntry_plaz_code("0128");
            // travelPlanParamDTO.setExit_plaz_code("0631");
            // travelPlanParamDTO.setEntry_plaz_code("0722");
            // travelPlanParamDTO.setExit_plaz_code("0223");
            //
            // // travelPlanParamDTO.setEntry_plaz_code("0128");
            // // travelPlanParamDTO.setExit_plaz_code("0158");
            //
            // travelPlanParamDTO.setEntry_plaz_code("0128");
            // travelPlanParamDTO.setExit_plaz_code("0130");
            // //////////////////////
            // //
            //
            // // /////////////////
            // travelPlanParamDTO.setScreenType(1);
            // travelPlanParamDTO.setH_id(8);
            // travelPlanParamDTO.setH_code("G1501");
            // travelPlanParamDTO.setEntry_plaz_code("0541");
            // travelPlanParamDTO.setExit_plaz_code("0176");
            // // /////////////////

            // 类型暂时不做为参数使用，暂时使用1
            travelPlanParamDTO.setCar_type("1");
            // 初始化markFlag
            travelScreenClientDTO.setMarkFlag(doMarkFlagMethod(travelPlanParamDTO));
            dto = iTravelPlanRelultService.getTravelPlanData(travelPlanParamDTO);
            // 页面DTO字段赋值
            doBackDTOtoClientDTO(dto, travelScreenClientDTO);

            // 初始化markFlag
           // travelScreenClientDTO.setMarkFlag(doMarkFlagMethod(travelPlanParamDTO));

            // test
            // this.print(dto, out);

        } catch (Exception e) {
            if (dto == null) {
                dto = new TravelScreenDTO();
            }
            dto.setErrFlg(true);
            // if (travelScreenClientDTO.getTravelNodeDTOList() == null ||
            // travelScreenClientDTO.getTravelNodeDTOList().size() <= 0)
            // {
            //
            // }
            travelScreenClientDTO.setReturnCode("-1301");
            // e.printStackTrace();
            this.logger.error("doGetTravelPlanData", e);
        }

        if (dto==null || !dto.isErrFlg()) {
            travelScreenClientDTO.setReturnCode("0");
        }
        // android
        toJson(response, travelScreenClientDTO);
        // ;

        // lngTime = (new Date().getTime()) - lngTime;
        // out.println("<BR>经过时间[" + lngTime + "]毫秒");

        return null;
    }

    private void print(TravelScreenDTO dto, PrintWriter out) {
        if (dto == null) {
            return;
        }
        if (dto.getTravelNodeDTOList() != null) {

            out.println("<BR>====================================");
            out.println("<BR>" + dto.getEntry_plaz_name() + "--" + dto.getExit_plaz_name());
            out.println("<BR>行驶时间：" + dto.getTravel_time());
            out.println("<BR>费用：" + dto.getFare());
            out.println("<BR>====================================");
            for (TravelNodeDTO travelNode : dto.getTravelNodeDTOList()) {

                out.println("<BR>====================================");
                out.println("<BR>" + travelNode.getH_code() + "-" + travelNode.getH_name() + "(" + travelNode.getEntry_plaz_name()
                                + travelNode.getEntry_plaz_code() + "-" + travelNode.getExit_plaz_name() + travelNode.getExit_plaz_code() + ")"
                                + travelNode.getPlaz_count() + "h_flg:" + travelNode.getH_flg());
                out.println("<BR>====================================");

                if (travelNode.getTravelElementDTOList() != null) {
                    // out.println("<BR>-----------------------");
                    for (TravelElementDTO travelElement : travelNode.getTravelElementDTOList()) {
                        String wendu = "";
                        if (travelElement.getPlazaDTO() != null) {

                            if (travelElement.getWeatherForcastDTO() != null) {
                                wendu = travelElement.getWeatherForcastDTO().getTemperature();
                            }
                            out.println("<BR>" + travelElement.getPlazaDTO().getPlaz_name() + travelElement.getPlazaDTO().getPlaz_code() + "  "
                                            + travelElement.getPlazaDTO().getStake_id() + "       " + wendu + "plaz_flg: "
                                            + travelElement.getPlazaDTO().getPlaz_flg());
                        }
                        if (travelElement.getEntryOverpass() != null) {
                            out.println("<BR>" + travelElement.getEntryOverpass().getO_name() + "  " + travelElement.getEntryOverpass().getExit_stake_id());
                        }
                        // out.println("<BR>-----------------------");
                        out.println("<BR>      |      |      ");
                        out.println("<BR>      |      |      ");

                        // 1.服务区
                        if (travelElement.getSaIds() != null) {
                            out.println("<BR>服务区");
                            int i = 0;
                            for (String str : travelElement.getSaDrs()) {
                                if ("1".equals(str)) {
                                    out.print("左：" + travelElement.getSaIds().get(i));
                                } else if ("2".equals(str)) {
                                    out.print("右：" + travelElement.getSaIds().get(i));
                                } else if ("3".equals(str)) {
                                    out.print("两边：" + travelElement.getSaIds().get(i));
                                }
                                i++;
                            }
                        }
                        // 2.网友发布信息
                        if (travelElement.getUdpIds() != null) {
                            out.println("<BR>网友发布信息");
                            int i = 0;
                            for (String str : travelElement.getUdpDrs()) {
                                if ("1".equals(str)) {
                                    out.print("左：" + travelElement.getUdpIds().get(i));
                                } else if ("2".equals(str)) {
                                    out.print("右：" + travelElement.getUdpIds().get(i));
                                } else if ("3".equals(str)) {
                                    out.print("两边：" + travelElement.getUdpIds().get(i));
                                }
                                i++;
                            }
                        }
                        // 3.道路管制
                        if (travelElement.getRciIds() != null) {
                            out.println("<BR>道路管制");
                            int i = 0;
                            for (String str : travelElement.getRciDrs()) {
                                if ("1".equals(str)) {
                                    out.print("左：" + travelElement.getRciIds().get(i));
                                } else if ("2".equals(str)) {
                                    out.print("右：" + travelElement.getRciIds().get(i));
                                } else if ("3".equals(str)) {
                                    out.print("两边：" + travelElement.getRciIds().get(i));
                                }
                                i++;
                            }
                        }

                        out.println("<BR>      |      |      ");
                        out.println("<BR>      |      |      ");
                        out.println("<BR>      |      |      ");

                        // out.println("<BR>-----------------------");
                        if (travelElement.getExitPlazaDTO() != null) {
                            out.println("<BR>" + travelElement.getExitPlazaDTO().getPlaz_name() + travelElement.getExitPlazaDTO().getPlaz_code() + "  "
                                            + travelElement.getExitPlazaDTO().getStake_id() + "       " + wendu + "plaz_flg: "
                                            + travelElement.getExitPlazaDTO().getPlaz_flg());
                        }
                        if (travelElement.getExitOverpass() != null) {
                            out.println("<BR>" + travelElement.getExitOverpass().getO_name() + "  " + travelElement.getExitOverpass().getEntry_stake_id());
                        }
                        // out.println("<BR>-----------------------");
                    }
                    // out.println("<BR>-----------------------");
                }
            }
        }
    }

    public TravelScreenClientDTO doBackDTOtoClientDTO(TravelScreenDTO travelScreenDTO, TravelScreenClientDTO travelScreenClientDTO) {

        // 画面类别(1:即时路况页面(主页),2:行程规划)

        travelScreenClientDTO.setEntry_plaz_code(travelScreenDTO.getEntry_plaz_code());

        travelScreenClientDTO.setExit_plaz_code(travelScreenDTO.getExit_plaz_code());

        travelScreenClientDTO.setEntry_plaz_name(travelScreenDTO.getEntry_plaz_name());

        travelScreenClientDTO.setExit_plaz_name(travelScreenDTO.getExit_plaz_name());

        // 行驶路程
        travelScreenClientDTO.setRoad_length(travelScreenDTO.getRoad_length());
        // 行驶时间
        if (travelScreenDTO.getTravel_time() == null || "".equals(travelScreenDTO.getTravel_time()) || "-1".equals(travelScreenDTO.getTravel_time())) {

            // 运行时间（暂时写死的数据）
            // road_length单位：百米
            if (travelScreenDTO.getRoad_length() != null && !("".equals(travelScreenDTO.getRoad_length()))) {
                double travelTime = ((Double.parseDouble(travelScreenDTO.getRoad_length()) / (80.0 * 10)) * 60);
                travelScreenClientDTO.setTravel_time(String.valueOf(travelTime));
            }

        } else {
            travelScreenClientDTO.setTravel_time(travelScreenDTO.getTravel_time());
        }

        // 行驶费用，暂时不显示
        // travelScreenClientDTO.setFare(travelScreenDTO.getFare());

        // 途径的高速List(画面DTO)
        List travelNodeDTOList = new ArrayList<TravelNodeClientDTO>();
        
        if(travelScreenDTO.getTravelNodeDTOList()!=null){
            for (TravelNodeDTO travelNodeDTO : travelScreenDTO.getTravelNodeDTOList()) {

                TravelNodeDTO _travelNodeDTO = new TravelNodeDTO();
                CommonUtil.reflectClass(travelNodeDTO, _travelNodeDTO);
                // 高速Id赋值操作/使用Code替换ID
                // if (travelNodeDTO.getH_code() != null) {
                //
                // String highWayId = ResourceLocator.getI18NMessage(this,
                // travelNodeDTO.getH_code());
                // if (highWayId != null) {
                // _travelNodeDTO.setH_id(Integer.parseInt(highWayId));
                // }
                //
                // }

                // 画面元素list(画面DTO)
                List travelElementDTOList = new ArrayList<TravelElementClientDTO>();
                for (TravelElementDTO travelElementDTO : travelNodeDTO.getTravelElementDTOList()) {
                    TravelElementClientDTO _travelElementDTO = new TravelElementClientDTO();
                    CommonUtil.reflectClass(travelElementDTO, _travelElementDTO);
                    // 收费站DTO
                    PlazaClientDTO plazaDTO = new PlazaClientDTO();
                    CommonUtil.reflectClass(travelElementDTO.getPlazaDTO(), plazaDTO);
                    _travelElementDTO.setPlazaDTO(plazaDTO);

                    PlazaClientDTO exitPlazaDTO = new PlazaClientDTO();
                    CommonUtil.reflectClass(travelElementDTO.getExitPlazaDTO(), exitPlazaDTO);
                    _travelElementDTO.setExitPlazaDTO(exitPlazaDTO);
                    // 入口立交
                    OverpassClientDTO entryOverpass = new OverpassClientDTO();
                    CommonUtil.reflectClass(travelElementDTO.getEntryOverpass(), entryOverpass);
                    _travelElementDTO.setEntryOverpass(entryOverpass);

                    // 出口立交
                    OverpassClientDTO entryOverpass2 = new OverpassClientDTO();
                    CommonUtil.reflectClass(travelElementDTO.getExitOverpass(), entryOverpass2);
                    _travelElementDTO.setExitOverpass(entryOverpass2);

                    // 天气信息
                    if (travelElementDTO.getWeatherForcastDTO() != null) {
                        _travelElementDTO.setWeather(travelElementDTO.getWeatherForcastDTO().getDay_pic_name());
                    }

                    travelElementDTOList.add(_travelElementDTO);

                }
                _travelNodeDTO.setTravelElementDTOList(travelElementDTOList);

                // 路段元素Link(画面DTO)

                travelNodeDTOList.add(_travelNodeDTO);

            }  
        }

        travelScreenClientDTO.setTravelNodeDTOList(travelNodeDTOList);

        return travelScreenClientDTO;
    }

    // 用户收藏：处理markFlag收藏标志位

    public boolean doMarkFlagMethod(TravelPlanParamDTO travelPlanParamDTO) {

        OftenUsedRoadEntity oftenUsedRoadEntity = new OftenUsedRoadEntity();

        oftenUsedRoadEntity.setU_id(travelPlanParamDTO.getU_id());
        // Road_type:字段添加
        oftenUsedRoadEntity.setRoad_type(travelPlanParamDTO.getRoad_type());
        oftenUsedRoadEntity.setPlaz_code_start(travelPlanParamDTO.getEntry_plaz_code());
        oftenUsedRoadEntity.setPlaz_code_end(travelPlanParamDTO.getExit_plaz_code());

        List<OftenUsedRoadEntity> list = iOftenUsedRoadService.getOftenUsedRoadList(oftenUsedRoadEntity);

        // 如果检索到数据，该路段已经被收藏
        if (list != null && list.size() != 0) {
            return true;
        } else {
            return false;
        }

    }

}
