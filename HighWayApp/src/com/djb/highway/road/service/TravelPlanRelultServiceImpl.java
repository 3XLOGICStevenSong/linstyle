package com.djb.highway.road.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.common.util.RoadInfoUtil;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.ICameraDao;
import com.djb.highway.road.dao.IHighWayDao;
import com.djb.highway.road.dao.IInfoBoardDao;
import com.djb.highway.road.dao.IOverpassDao;
import com.djb.highway.road.dao.IOverpassDetailDao;
import com.djb.highway.road.dao.IPlazaDao;
import com.djb.highway.road.dao.IRoadControlInfoDao;
import com.djb.highway.road.dao.ISectionDao;
import com.djb.highway.road.dao.IServiceAreaDao;
import com.djb.highway.road.dao.ITravelPlanDao;
import com.djb.highway.road.dao.IWeatherForcastDao;
import com.djb.highway.road.dto.OverpassDTO;
import com.djb.highway.road.dto.PlazaDTO;
import com.djb.highway.road.dto.WeatherForcastDTO;
import com.djb.highway.road.dto.travel.DoubleLinkObject;
import com.djb.highway.road.dto.travel.Link;
import com.djb.highway.road.dto.travel.OverpassComparator;
import com.djb.highway.road.dto.travel.TravelElementDTO;
import com.djb.highway.road.dto.travel.TravelNodeDTO;
import com.djb.highway.road.dto.travel.TravelPlanParamDTO;
import com.djb.highway.road.dto.travel.TravelScreenDTO;
import com.djb.highway.road.entity.HighWayEntity;
import com.djb.highway.road.entity.OverpassDetailEntity;
import com.djb.highway.road.entity.OverpassEntity;
import com.djb.highway.road.entity.PlazaEntity;
import com.djb.highway.road.entity.RoadControlInfoEntity;
import com.djb.highway.road.entity.ServiceAreaEntity;
import com.djb.highway.road.entity.TravelPlanEntity;
import com.djb.highway.road.entity.WeatherForcastEntity;
import com.djb.highway.user.dao.IUserDeployPicDao;
import com.djb.highway.user.entity.UserDeployPicEntity;

@Service("iTravelPlanRelultService")
public class TravelPlanRelultServiceImpl extends BaseService implements ITravelPlanRelultService {

    @Autowired
    @Qualifier("highWayDao")
    private IHighWayDao highWayDao;

    @Autowired
    @Qualifier("plazaDao")
    private IPlazaDao plazaDao;

    @Autowired
    @Qualifier("cameraDao")
    private ICameraDao cameraDao;

    @Autowired
    @Qualifier("roadControlInfoDao")
    private IRoadControlInfoDao roadControlInfoDao;

    @Autowired
    @Qualifier("infoBoardDao")
    private IInfoBoardDao infoBoardDao;

    @Autowired
    @Qualifier("serviceAreaDao")
    private IServiceAreaDao serviceAreaDao;

    @Autowired
    @Qualifier("overpassDao")
    private IOverpassDao overpassDao;

    @Autowired
    @Qualifier("overpassDetailDao")
    private IOverpassDetailDao overpassDetailDao;

    @Autowired
    @Qualifier("travelPlanDao")
    private ITravelPlanDao travelPlanDao;

    @Autowired
    @Qualifier("sectionDao")
    private ISectionDao sectionDao;

    @Autowired
    @Qualifier("weatherForcastDao")
    private IWeatherForcastDao weatherForcastDao;

    @Autowired
    @Qualifier("userDeployPicDao")
    private IUserDeployPicDao userDeployPicDao;

    // 画面类别(1:即时路况页面(主页),2:行程规划)
    private int screenType;

    // 途经收费站List
    private List<PlazaDTO> plazaDTOList;

    // 途经服务区List
    private List<ServiceAreaEntity> serviceAreaEntityList;

    // 途经的用户分享信息List
    private List<UserDeployPicEntity> userDeployPicEntityList;

    // 途经的收费站map
    private Map<String, PlazaEntity> plazaMap;

    // 途经的收费站code数组
    private String[] plazaCodeArgs;

    // 途经高速的List
    private String[] h_codeArgs;

    // 途经收费站对应城市的天气信息
    private static List<WeatherForcastEntity> weatherForcastEntityList;

    // 途经道路管制信息
    private List<RoadControlInfoEntity> roadControlInfoEntityList;

    // 途经立交list
    private List<OverpassDTO> overpassDTOList;

    /**
     * 取不到线路，reset list
     */
    private void resetInfo() {

        // 途经收费站List
        this.plazaDTOList = null;

        // 途经服务区List
        this.serviceAreaEntityList = null;

        // 途经的用户分享信息List
        this.userDeployPicEntityList = null;

        // 途经的收费站map
        this.plazaMap = null;

        // 途经的收费站code数组
        this.plazaCodeArgs = null;

        // 途经的高速List
        this.h_codeArgs = null;

        // 所有天气信息List
        if (weatherForcastEntityList == null) {
            WeatherForcastEntity paramweatherForcastEntity = new WeatherForcastEntity();
            weatherForcastEntityList = weatherForcastDao.findListByCondition(paramweatherForcastEntity);
        }

        // 途经道路管制信息
        this.roadControlInfoEntityList = null;

        // 途经立交list
        this.overpassDTOList = null;
    }

    /**
     * travelScreenDTO Init
     * 
     * @param travelPlanParamDTO
     * @param travelScreenDTO
     */
    private void travelScreenDTOInit(TravelPlanParamDTO travelPlanParamDTO, TravelScreenDTO travelScreenDTO) {

        travelScreenDTO.setScreenType(travelPlanParamDTO.getScreenType());
        // travelScreenDTO.setEntry_plaz_id(travelPlanParamDTO.XXXXX());
        // travelScreenDTO.setExit_plaz_id(travelPlanParamDTO.XXXXX());
        travelScreenDTO.setEntry_plaz_code(travelPlanParamDTO.getEntry_plaz_code());
        travelScreenDTO.setExit_plaz_code(travelPlanParamDTO.getExit_plaz_code());
        String[] _plazaCodeArgs = new String[] { travelPlanParamDTO.getEntry_plaz_code(), travelPlanParamDTO.getExit_plaz_code() };
        Map<String, PlazaEntity> _plazaMap = null;
        if (_plazaCodeArgs != null && _plazaCodeArgs.length > 0) {
            PlazaEntity paramPlazaEntity = new PlazaEntity();
            paramPlazaEntity.setPlazaCodeArgs(plazaCodeArgs);
            _plazaMap = (Map<String, PlazaEntity>) plazaDao.selectMap(paramPlazaEntity, "plaz_code");

            if (_plazaMap != null) {
                if (_plazaMap.containsKey(travelPlanParamDTO.getEntry_plaz_code())) {
                    travelScreenDTO.setEntry_plaz_name(_plazaMap.get(travelPlanParamDTO.getEntry_plaz_code()).getPlaz_name());
                } else {
                    travelScreenDTO.setEntry_plaz_name("");
                }
                if (_plazaMap.containsKey(travelPlanParamDTO.getExit_plaz_code())) {
                    travelScreenDTO.setExit_plaz_name(_plazaMap.get(travelPlanParamDTO.getExit_plaz_code()).getPlaz_name());
                } else {
                    travelScreenDTO.setExit_plaz_name("");
                }
            }
        }
        travelScreenDTO.setRoad_length("0");
        travelScreenDTO.setTravel_time("0");
        travelScreenDTO.setFare(0);
    }

    /**
     * 
     * @param resultTravelPlanEntity
     */
    private void init() {

        if (this.plazaMap == null) {
            return;
        }

        // 途经收费站List
        plazaDTOList = buildPlazaDTOList(plazaCodeArgs, plazaMap);
        // 途经高速的List
        h_codeArgs = getHighwayList(plazaDTOList);

        if (plazaCodeArgs != null && plazaCodeArgs.length > 0) {
            // 途经服务区List
            ServiceAreaEntity paramServiceAreaEntity = new ServiceAreaEntity();
            paramServiceAreaEntity.setH_codeArgs(h_codeArgs);
            serviceAreaEntityList = (List<ServiceAreaEntity>) serviceAreaDao.findListByCondition(paramServiceAreaEntity);

            // 途经网友发布路况EntityList
            UserDeployPicEntity paramUserDeployPicEntity = new UserDeployPicEntity();
            paramUserDeployPicEntity.setPlazaCodeArgs(plazaCodeArgs);
            // 只获取一小时内的分享信息，并且分享的信息类型为拥堵类型
            Date currentDate = getCurrentDate();
            paramUserDeployPicEntity.setUdp_version_time(currentDate);
            // "0"代表拥堵的信息类型
            paramUserDeployPicEntity.setUdp_share_type("0");
            // 获取途经用户分享的信息List
            // userDeployPicEntityList = (List<UserDeployPicEntity>)
            // userDeployPicDao.findListByCondition(paramUserDeployPicEntity);
            userDeployPicEntityList = (List<UserDeployPicEntity>) userDeployPicDao.findOtherList(IUserDeployPicDao.GETUSERDEPLOYPICLISTBYTIMESTAMP,
                            paramUserDeployPicEntity, UserDeployPicEntity.class);
            // 途经道路管制信息List
            RoadControlInfoEntity paramRoadControlInfoEntity = new RoadControlInfoEntity();
            paramRoadControlInfoEntity.setH_codeArgs(h_codeArgs);
            roadControlInfoEntityList = (List<RoadControlInfoEntity>) roadControlInfoDao.findListByCondition(paramRoadControlInfoEntity);

        }

        // 所有天气List
        //        WeatherForcastEntity paramweatherForcastEntity = new WeatherForcastEntity();
        //        if (weatherForcastEntityList == null) {
        //            weatherForcastEntityList = weatherForcastDao.findListByCondition(paramweatherForcastEntity);
        //        }

        // 途经立交List
        OverpassEntity paramOverpassEntity = new OverpassEntity();
        paramOverpassEntity.setPlazaCodeArgs(plazaCodeArgs);
        List<OverpassEntity> overpassEntityList = overpassDao.findListByCondition(paramOverpassEntity);
        // 构造遍历的立交list
        overpassDTOList = buildOverpassDTOList(overpassEntityList);

    }

    // 获取距离当前时间前一小时的时间
    public Date getCurrentDate() {
        Date tempDate = new Date();
        // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // String teDate = sf.format(tempDate);
        // System.out.println("tempDate:--" + teDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDate);
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        Date curDate = calendar.getTime();
        // String cuDate = sf.format(curDate);
        // System.out.println("cuDate:--" + cuDate);
        return curDate;
    }

    /**
     * 
     * @param travelPlanParamDTO
     * @param travelScreenDTO
     * @return
     */
    private void buildScreenHead(TravelPlanParamDTO travelPlanParamDTO, TravelScreenDTO travelScreenDTO) {

        // travelScreenDTO Init
        this.travelScreenDTOInit(travelPlanParamDTO, travelScreenDTO);

        String road_line = null;
        // 1:即时路况页面(主页)
        if (travelScreenDTO.getScreenType() == 1) {
            HighWayEntity paramHighWayEntity = new HighWayEntity();
            CommonUtil.reflectClass(travelPlanParamDTO, paramHighWayEntity);
            HighWayEntity resultHighWayEntity = highWayDao.getObject(paramHighWayEntity);
            if (resultHighWayEntity != null) {
                road_line = resultHighWayEntity.getPlaz_list();
            }

        } else {// 2:行程规划
            TravelPlanEntity paramTravelPlanEntity = new TravelPlanEntity();
            CommonUtil.reflectClass(travelPlanParamDTO, paramTravelPlanEntity);
            TravelPlanEntity resultTravelPlanEntity = travelPlanDao.getObject(paramTravelPlanEntity);
            CommonUtil.reflectClass(resultTravelPlanEntity, travelScreenDTO);
            if (resultTravelPlanEntity != null) {
                // 从行程规划表取得road_line
                road_line = resultTravelPlanEntity.getRoad_line();
            }
        }

        if (road_line != null) {
            plazaCodeArgs = StringUtils.split(road_line, "|");
            if (plazaCodeArgs != null && plazaCodeArgs.length > 0) {
                // 途经的收费站Map
                PlazaEntity paramPlazaEntity = new PlazaEntity();
                paramPlazaEntity.setPlazaCodeArgs(plazaCodeArgs);
                this.plazaMap = (Map<String, PlazaEntity>) plazaDao.selectMap(paramPlazaEntity, "plaz_code");

                if (plazaMap != null) {
                    if (plazaMap.containsKey(plazaCodeArgs[0])) {
                        travelScreenDTO.setEntry_plaz_name(plazaMap.get(plazaCodeArgs[0]).getPlaz_name());
                    } else {
                        travelScreenDTO.setEntry_plaz_name("");
                    }
                    if (plazaMap.containsKey(plazaCodeArgs[plazaCodeArgs.length - 1])) {
                        travelScreenDTO.setExit_plaz_name(plazaMap.get(plazaCodeArgs[plazaCodeArgs.length - 1]).getPlaz_name());
                    } else {
                        travelScreenDTO.setExit_plaz_name("");
                    }
                }
            }
        }

    }

    /**
     * 
     * @param travelNodeDTOList
     */
    private void buildScreenElement(List<TravelNodeDTO> travelNodeDTOList) {

        if (travelNodeDTOList != null) {
            for (TravelNodeDTO travelNode : travelNodeDTOList) {

                if (travelNode.getTravelElementDTOList() != null) {

                    // 路段元素Link
                    DoubleLinkObject travelElementDlink = travelNode.getTravelElementDTODlink();
                    // 遍历路段元素link
                    Link travelElementCurrent = travelElementDlink.first;
                    while (travelElementCurrent != null) {

                        TravelElementDTO travelElement = null;
                        if (travelElementCurrent.data instanceof TravelElementDTO) {
                            travelElement = (TravelElementDTO) travelElementCurrent.data;

                            String[] entryExitStakeId = getEntryExitStakeId(travelElementCurrent);
                            String entry_stake_id = null;
                            String exit_stake_id = null;

                            if (entryExitStakeId != null) {
                                entry_stake_id = entryExitStakeId[0];
                                exit_stake_id = entryExitStakeId[1];
                            }

                            // 1.取出途经的服务区
                            if (serviceAreaEntityList != null) {
                                List<String> saIds = new ArrayList<String>();
                                List<String> saDrs = new ArrayList<String>();
                                for (ServiceAreaEntity saEntity : serviceAreaEntityList) {

                                    if (this.isShowElement(saEntity.getH_code(), travelElement, saEntity.getSa_direction(), saEntity.getStake_id(),
                                                    entry_stake_id, exit_stake_id)) {
                                        saIds.add(saEntity.getSa_id().toString());
                                        saDrs.add(String.valueOf(this.getElementDirection(saEntity.getSa_direction(), entry_stake_id, exit_stake_id)));

                                    }

                                }
                                if (saIds.size() > 0) {
                                    travelElement.setSaIds(saIds);
                                    travelElement.setSaDrs(saDrs);
                                }

                            }

                            // 2.收费站对应城市的天气信息
                            String cityCode = null;
                            if (travelElement.getPlazaDTO() != null) {
                                cityCode = travelElement.getPlazaDTO().getCity_code();
                            } else if (travelElement.getExitPlazaDTO() != null) {
                                cityCode = travelElement.getExitPlazaDTO().getCity_code();
                            }

                            if (cityCode != null) {
                                WeatherForcastDTO resultWeatherForcastDTO = new WeatherForcastDTO();
                                if (weatherForcastEntityList != null) {
                                    for (WeatherForcastEntity resultWeatherForcastEntity : weatherForcastEntityList) {
                                        if (cityCode.equals(resultWeatherForcastEntity.getCitycode())) {
                                            CommonUtil.reflectClass(resultWeatherForcastEntity, resultWeatherForcastDTO);
                                            travelElement.setWeatherForcastDTO(resultWeatherForcastDTO);
                                            break;
                                        }
                                    }
                                }
                            }

                            // 3.两站间网友发布信息
                            PlazaDTO plazaPicDTO = null;
                            if (travelElement.getPlazaDTO() != null) {
                                plazaPicDTO = travelElement.getPlazaDTO();
                            }
                            if (plazaPicDTO != null) {

                                String plazaCode = plazaPicDTO.getPlaz_code();
                                String nextPlazaCode = getNextPlaza(plazaCode, plazaCodeArgs);
                                if (plazaCode != null && nextPlazaCode != null) {
                                    List<String> udpIds = new ArrayList<String>();
                                    List<String> udpDrs = new ArrayList<String>();

                                    for (UserDeployPicEntity userDeployPicEntity : userDeployPicEntityList) {

                                        if (plazaCode.equals(userDeployPicEntity.getPlaz_code_start())
                                                        && nextPlazaCode.equals(userDeployPicEntity.getPlaz_code_end())) {

                                            String start_stake_id = plazaMap.get(plazaCode).getStake_id();
                                            String end_stake_id = plazaMap.get(nextPlazaCode).getStake_id();
                                            if (userDeployPicEntity.getUdp_id() != null) {
                                                udpIds.add(userDeployPicEntity.getUdp_id().toString());
                                                udpDrs.add(String.valueOf(this.getElementDirection(getTravelDirection(start_stake_id, end_stake_id),
                                                                start_stake_id, end_stake_id)));
                                            }

                                        }

                                        // 1:即时路况页面(主页)
                                        if (this.screenType == 1) {
                                            if (plazaCode.equals(userDeployPicEntity.getPlaz_code_end())
                                                            && nextPlazaCode.equals(userDeployPicEntity.getPlaz_code_start())) {

                                                String start_stake_id = plazaMap.get(plazaCode).getStake_id();
                                                String end_stake_id = plazaMap.get(nextPlazaCode).getStake_id();
                                                if (userDeployPicEntity.getUdp_id() != null) {
                                                    udpIds.add(userDeployPicEntity.getUdp_id().toString());
                                                    udpDrs.add(String.valueOf(this.getElementDirection(getTravelDirection(end_stake_id, start_stake_id),
                                                                    start_stake_id, end_stake_id)));
                                                }

                                            }
                                        }

                                    }
                                    if (udpIds.size() > 0) {
                                        travelElement.setUdpIds(udpIds);
                                        travelElement.setUdpDrs(udpDrs);
                                    }
                                }

                            }

                            // 4.取出途经的道路管制信息
                            /*
                             * 1:两桩号间封闭 2:两出入口间封闭 3:仅封闭出入口 4:服务区关闭
                             * 
                             * 1:上行 2:下行 3:双向
                             */

                            if (roadControlInfoEntityList != null) {

                                List<String> rciIds = new ArrayList<String>();
                                List<String> rciDrs = new ArrayList<String>();
                                for (RoadControlInfoEntity rciEntity : roadControlInfoEntityList) {

                                    if (this.isShowRciElement(rciEntity.getH_code(), travelElement, rciEntity.getControl_direction(),
                                                    rciEntity.getStart_stake_id(), rciEntity.getEnd_stake_id(), entry_stake_id, exit_stake_id)) {

                                        rciIds.add(rciEntity.getRci_id().toString());
                                        rciDrs.add(String.valueOf(this.getRciElementDirection(rciEntity.getControl_direction(), entry_stake_id, exit_stake_id)));

                                        PlazaDTO plaza = null;
                                        if (travelElement.getPlazaDTO() != null) {
                                            plaza = travelElement.getPlazaDTO();
                                        } else if (travelElement.getExitPlazaDTO() != null) {
                                            plaza = travelElement.getExitPlazaDTO();
                                        }

                                        if (plaza != null) {
                                            if (plaza.getPlaz_code().equals(rciEntity.getStart_plaz_code())
                                                            || plaza.getPlaz_code().equals(rciEntity.getEnd_plaz_code())
                                                            || (rciEntity.getPlaz_list() != null && rciEntity.getPlaz_list().indexOf(plaza.getPlaz_code()) != 0)) {
                                                // 收费站叹号设置
                                                plaza.setPlaz_flg(1);
                                                travelNode.setH_flg(1);
                                            }
                                        }
                                        travelNode.setH_flg(1);

                                    }

                                }
                                if (rciIds.size() > 0) {
                                    travelElement.setRciIds(rciIds);
                                    travelElement.setRciDrs(rciDrs);
                                }

                            }

                        }

                        travelElementCurrent = travelElementCurrent.next;

                    }
                }

            }
        }
    }

    /**
     * 
     * @param plazaOverpassDlink
     * @return
     */
    private List<TravelNodeDTO> buildScreenNode(DoubleLinkObject plazaOverpassDlink) {

        // 整体画面DTO
        // 高速list
        List<TravelNodeDTO> travelNodeDTOList = new ArrayList<TravelNodeDTO>();
        // 高速DTO
        TravelNodeDTO travelNodeDTO = null;

        // 路段元素list
        List<TravelElementDTO> travelElementDTOList = null;
        DoubleLinkObject travelElementDTODlink = null;
        // 路段DTO
        TravelElementDTO travelElementDTO = null;

        // 遍历收费站立交list
        Link plazaOverpassCurrent = plazaOverpassDlink.first;
        while (plazaOverpassCurrent != null) {
            PlazaDTO plaza = null;
            OverpassDTO overpass = null;

            if (travelElementDTOList == null) {
                // 路段元素list
                travelElementDTOList = new ArrayList<TravelElementDTO>();
                travelElementDTODlink = new DoubleLinkObject();
            }

            if (plazaOverpassCurrent.data instanceof PlazaDTO) {

                plaza = (PlazaDTO) plazaOverpassCurrent.data;

                if (plazaOverpassCurrent.next != null) {
                    travelElementDTO = new TravelElementDTO();
                    travelElementDTOList.add(travelElementDTO);
                    travelElementDTODlink.insertLast(travelElementDTO);
                    travelElementDTO.setPlazaDTO(plaza);
                }

                if (plazaOverpassCurrent.previous == null) {

                    travelNodeDTO = new TravelNodeDTO();

                    travelNodeDTO.setEntry_plaz_id(plaza.getPlaz_id());
                    travelNodeDTO.setEntry_plaz_code(plaza.getPlaz_code());
                    travelNodeDTO.setEntry_plaz_name(plaza.getPlaz_name());
                    travelNodeDTO.setH_name(plaza.getH_name());
                    travelNodeDTO.setH_code(plaza.getH_code());
                    travelNodeDTO.setTravelElementDTODlink(travelElementDTODlink);
                    travelNodeDTO.setTravelElementDTOList(travelElementDTOList);
                    travelNodeDTOList.add(travelNodeDTO);

                } else {
                    if (plazaOverpassCurrent.previous.data instanceof OverpassDTO) {
                        travelNodeDTO.setEntry_plaz_id(plaza.getPlaz_id());
                        travelNodeDTO.setEntry_plaz_code(plaza.getPlaz_code());
                        travelNodeDTO.setEntry_plaz_name(plaza.getPlaz_name());
                        travelNodeDTO.setH_name(plaza.getH_name());
                        travelNodeDTO.setH_code(plaza.getH_code());
                    }

                }

                if (plazaOverpassCurrent.next != null) {
                    if (plazaOverpassCurrent.next.data instanceof OverpassDTO) {

                        // 添加高速上面那条立交纪录
                        overpass = (OverpassDTO) plazaOverpassCurrent.next.data;
                        overpass.setLocationFlg(1);
                        travelElementDTO.setExitOverpass(overpass);
                        travelNodeDTO.setExit_plaz_id(plaza.getPlaz_id());
                        travelNodeDTO.setExit_plaz_code(plaza.getPlaz_code());
                        travelNodeDTO.setExit_plaz_name(plaza.getPlaz_name());
                        travelNodeDTO.setPlaz_count(getPlazaCount(travelElementDTOList) - 1);

                    }
                } else {
                    travelElementDTO.setExitPlazaDTO(plaza);
                    travelNodeDTO.setExit_plaz_id(plaza.getPlaz_id());
                    travelNodeDTO.setExit_plaz_code(plaza.getPlaz_code());
                    travelNodeDTO.setExit_plaz_name(plaza.getPlaz_name());
                    travelNodeDTO.setPlaz_count(getPlazaCount(travelElementDTOList) - 1);
                }

            } else if (plazaOverpassCurrent.data instanceof OverpassDTO) {

                overpass = (OverpassDTO) plazaOverpassCurrent.data;

                if (plazaOverpassCurrent.next != null) {
                    if (plazaOverpassCurrent.next.data instanceof OverpassDTO) {

                        // 创建高速DTO
                        travelNodeDTO = new TravelNodeDTO();
                        // 路段元素list
                        travelElementDTOList = new ArrayList<TravelElementDTO>();
                        travelElementDTODlink = new DoubleLinkObject();
                        // 路段DTO
                        travelElementDTO = new TravelElementDTO();
                        travelElementDTOList.add(travelElementDTO);
                        travelElementDTODlink.insertLast(travelElementDTO);

                        // 添加高速上面那条立交纪录
                        overpass.setLocationFlg(2);
                        travelElementDTO.setEntryOverpass(overpass);

                        // 添加高速下面那条立交纪录
                        OverpassDTO nextOverpass = (OverpassDTO) plazaOverpassCurrent.next.data;
                        nextOverpass.setLocationFlg(1);
                        travelElementDTO.setExitOverpass(nextOverpass);

                        travelNodeDTO.setTravelElementDTOList(travelElementDTOList);
                        travelNodeDTO.setTravelElementDTODlink(travelElementDTODlink);
                        travelNodeDTO.setH_name(overpass.getExit_highway_name());
                        travelNodeDTO.setH_code(overpass.getExit_highway_code());

                        travelNodeDTOList.add(travelNodeDTO);

                    } else if (plazaOverpassCurrent.next.data instanceof PlazaDTO) {

                        // 创建高速DTO
                        travelNodeDTO = new TravelNodeDTO();
                        // 路段元素list
                        travelElementDTOList = new ArrayList<TravelElementDTO>();
                        travelElementDTODlink = new DoubleLinkObject();
                        // 路段DTO
                        travelElementDTO = new TravelElementDTO();
                        travelElementDTOList.add(travelElementDTO);
                        travelElementDTODlink.insertLast(travelElementDTO);

                        // 添加高速下面那条立交纪录
                        overpass.setLocationFlg(2);
                        travelElementDTO.setEntryOverpass(overpass);
                        travelNodeDTO.setH_name(overpass.getExit_highway_name());
                        travelNodeDTO.setH_code(overpass.getExit_highway_code());
                        travelNodeDTO.setTravelElementDTODlink(travelElementDTODlink);
                        travelNodeDTO.setTravelElementDTOList(travelElementDTOList);

                        travelNodeDTOList.add(travelNodeDTO);

                    }
                }

            }
            plazaOverpassCurrent = plazaOverpassCurrent.next;
        }

        return travelNodeDTOList;

    }

    @Override
    public TravelScreenDTO getTravelPlanData(TravelPlanParamDTO travelPlanParamDTO) {

        // reset
        this.resetInfo();
        
        // 初期化
        TravelScreenDTO travelScreenDTO = new TravelScreenDTO();
        this.screenType = travelPlanParamDTO.getScreenType();
        this.buildScreenHead(travelPlanParamDTO, travelScreenDTO);
        this.init();

        // merge 收费站、立交信息
        DoubleLinkObject plazaOverpassDlink = mergePlazaOverpass();
        if (plazaOverpassDlink == null) {
            return travelScreenDTO;
        }

        // 1:即时路况页面(主页)
        if (travelScreenDTO.getScreenType() == 1) {

            if (plazaDTOList != null) {

                List<TravelNodeDTO> travelNodeDTOList = this.buildScreenNode(plazaOverpassDlink);
                buildScreenElement(travelNodeDTOList);

                travelScreenDTO.setTravelNodeDTOList(travelNodeDTOList);

            }

        } else {// 2:行程规划
            if (plazaDTOList != null) {

                List<TravelNodeDTO> travelNodeDTOList = this.buildScreenNode(plazaOverpassDlink);
                buildScreenElement(travelNodeDTOList);

                travelScreenDTO.setTravelNodeDTOList(travelNodeDTOList);

            }
        }

        return travelScreenDTO;
    }

    /**
     * 
     * @param overpassDTOList
     * @param entry_plaz_code
     *            收费站的前一站
     * @param exit_plaz_code
     *            收费站的下一站
     */
    private void sortOverpassDTOList(DoubleLinkObject plazaOverpassDlink, List<OverpassDTO> overpassDTOList, String entry_plaz_code, String exit_plaz_code) {

        if (plazaOverpassDlink == null || overpassDTOList == null) {
            return;
        }

        if (overpassDTOList.size() > 1) {
            if (screenType == 1) {
                float value0 = CommonUtil.toFloat(entry_plaz_code, 0);
                float value1 = CommonUtil.toFloat(exit_plaz_code, 0);

                // 0:升序 1:降序
                int sortType = 0;

                if (value0 < value1) {
                    sortType = 0;
                } else {
                    sortType = 1;
                }

                // 排序
                Collections.sort(overpassDTOList, new OverpassComparator(screenType, sortType));

            } else {// 行程规划

                this.sortOverpassDTOList(overpassDTOList);
                // 排序
                Collections.sort(overpassDTOList, new OverpassComparator(screenType));
            }
        }

        for (OverpassDTO overpassDTO : overpassDTOList) {
            plazaOverpassDlink.insertLast(overpassDTO);
        }
    }

    /**
     * 
     * @param overpassDTOList
     */
    private void sortOverpassDTOList(List<OverpassDTO> overpassDTOList) {

        if (h_codeArgs == null || overpassDTOList == null) {
            return;
        }

        for (int i = 0; i < h_codeArgs.length; i++) {
            for (OverpassDTO overpassDTO : overpassDTOList) {
                if (h_codeArgs.equals(overpassDTO.getEntry_highway_code())) {
                    overpassDTO.setSort(i + 1);
                }
            }
        }
    }

    /**
     * 构造立交list
     * 
     * @param overpassDTO
     * @return
     */
    private List<OverpassDTO> buildOverpassDTOList(List<OverpassEntity> overpassEntityList) {

        List<OverpassDTO> overpassDTOList = null;
        if (overpassEntityList != null) {
            overpassDTOList = new ArrayList<OverpassDTO>();

            for (OverpassEntity overpassEntity : overpassEntityList) {

                OverpassDTO overpassDTO = new OverpassDTO();
                CommonUtil.reflectClass(overpassEntity, overpassDTO);

                List<OverpassDetailEntity> overpassDetailEntitys = overpassEntity.getOverpassDetailEntitys();
                if (overpassDetailEntitys != null) {
                    for (OverpassDetailEntity overpassDetailEntity : overpassDetailEntitys) {

                        if (plazaCodeArgs != null) {
                            String plazCode = null;
                            String nextPlazCode = null;
                            for (int i = 0; i < plazaCodeArgs.length - 1; i++) {
                                plazCode = plazaCodeArgs[i];
                                nextPlazCode = plazaCodeArgs[i + 1];
                                if (plazCode != null && plazCode.equals(overpassDetailEntity.getEntry_plaz_code()) && nextPlazCode != null
                                                && nextPlazCode.equals(overpassDetailEntity.getExit_plaz_code())) {
                                    overpassDTO.setOd_id(overpassDetailEntity.getOd_id());
                                    overpassDTO.setEntry_plaz_code(overpassDetailEntity.getEntry_plaz_code());
                                    overpassDTO.setExit_plaz_code(overpassDetailEntity.getExit_plaz_code());
                                    overpassDTO.setEntry_stake_id(overpassDetailEntity.getEntry_stake_id());
                                    overpassDTO.setExit_stake_id(overpassDetailEntity.getExit_stake_id());
                                    overpassDTO.setEntry_highway_id(overpassDetailEntity.getEntry_highway_id());
                                    overpassDTO.setEntry_highway_code(overpassDetailEntity.getEntry_highway_code());
                                    overpassDTO.setEntry_highway_name(overpassDetailEntity.getEntry_highway_name());
                                    overpassDTO.setExit_highway_id(overpassDetailEntity.getExit_highway_id());
                                    overpassDTO.setExit_highway_code(overpassDetailEntity.getExit_highway_code());
                                    overpassDTO.setExit_highway_name(overpassDetailEntity.getExit_highway_name());
                                }
                            }
                        }
                    }
                }
                overpassDTOList.add(overpassDTO);
            }

        }
        return overpassDTOList;
    }

    /**
     * 
     * @return 收费站、立交信息 DoubleLinkObject
     */
    private DoubleLinkObject mergePlazaOverpass() {

        if (plazaDTOList == null || overpassDTOList == null) {
            return null;
        }

        String entry_plaz_code = null;
        String exit_plaz_code = null;
        // 返回对象
        DoubleLinkObject plazaOverpassDlink = new DoubleLinkObject();
        DoubleLinkObject plazaDlink = new DoubleLinkObject();
        DoubleLinkObject overpassDlink = new DoubleLinkObject();

        for (PlazaDTO plazaDTO : plazaDTOList) {
            plazaDlink.insertLast(plazaDTO);

        }
        for (OverpassDTO overpassDTO : overpassDTOList) {
            overpassDlink.insertLast(overpassDTO);
        }

        // 遍历收费站
        Link plazaCurrent = plazaDlink.first;
        while (plazaCurrent != null) {
            PlazaDTO plaza = null;
            if (plazaCurrent.data instanceof PlazaDTO) {
                plaza = (PlazaDTO) plazaCurrent.data;
                // 返回对象
                plazaOverpassDlink.insertLast(plaza);
            }

            // 遍历立交
            Link overpassCurrent = overpassDlink.first;

            List<OverpassDTO> sortOverpassDTOList = new ArrayList<OverpassDTO>();
            while (overpassCurrent != null) {
                OverpassDTO overpassDTO = null;
                if (overpassCurrent.data instanceof OverpassDTO) {
                    overpassDTO = (OverpassDTO) overpassCurrent.data;

                    if (plaza.getPlaz_code().equals(overpassDTO.getEntry_plaz_code())) {
                        if (plazaCurrent.next != null) {
                            if (plazaCurrent.next.data instanceof PlazaDTO) {
                                PlazaDTO plazaNext = (PlazaDTO) plazaCurrent.next.data;

                                if (this.screenType == 1) {
                                    sortOverpassDTOList.add(overpassDTO);
                                    // 当前收费站
                                    entry_plaz_code = plaza.getPlaz_code();
                                    // 下一收费站
                                    exit_plaz_code = plazaNext.getPlaz_code();
                                } else if (this.screenType == 2) {
                                    if (plazaNext.getPlaz_code().equals(overpassDTO.getExit_plaz_code())) {

                                        sortOverpassDTOList.add(overpassDTO);
                                        // 当前收费站
                                        entry_plaz_code = plaza.getPlaz_code();
                                        // 下一收费站
                                        exit_plaz_code = plazaNext.getPlaz_code();
                                    }
                                }

                            }
                        }
                    }
                }
                overpassCurrent = overpassCurrent.next;
            }

            // 添加立交并排序
            if (sortOverpassDTOList.size() > 0) {
                this.sortOverpassDTOList(plazaOverpassDlink, sortOverpassDTOList, entry_plaz_code, exit_plaz_code);
            }

            plazaCurrent = plazaCurrent.next;
        }

        return plazaOverpassDlink;

    }

    /**
     * 构造遍历的收费站list
     * 
     * @param plazaCodeArgs
     * @param plazaMap
     * @return
     */
    private List<PlazaDTO> buildPlazaDTOList(String[] plazaCodeArgs, Map<String, PlazaEntity> plazaMap) {

        List<PlazaDTO> plazaDTOList = null;
        if (plazaCodeArgs != null && plazaMap != null) {

            plazaDTOList = new ArrayList<PlazaDTO>();

            for (int i = 0; i < plazaCodeArgs.length; i++) {

                // 当前收费站信息
                PlazaDTO plazaDTO = new PlazaDTO();

                PlazaEntity plazaEntity = (PlazaEntity) plazaMap.get(plazaCodeArgs[i]);

                // 2.1取出经过的收费站
                CommonUtil.reflectClass(plazaEntity, plazaDTO);

                plazaDTOList.add(plazaDTO);

            }
        }
        return plazaDTOList;
    }

    /**
     * 取得途经高速list
     * 
     * @param plazaDTOList
     * @return
     */
    private String[] getHighwayList(List<PlazaDTO> plazaDTOList) {
        String[] h_codeArgs = null;
        if (plazaDTOList != null) {
            List<String> highwayList = new ArrayList<String>();

            for (PlazaDTO resultPlazaDTO : plazaDTOList) {
                if (!highwayList.contains(resultPlazaDTO.getH_code())) {
                    highwayList.add(resultPlazaDTO.getH_code());
                }
            }

            h_codeArgs = (String[]) highwayList.toArray(new String[0]);
        }
        return h_codeArgs;
    }

    /**
     * 
     * @param overpassDTO
     * @return
     */
    private String[] getEntryExitStakeId(Link travelElementCurr) {

        if (travelElementCurr == null) {
            return null;
        }

        String[] entryExitStakeId = null;
        TravelElementDTO travelElementDTO = null;
        TravelElementDTO nextTravelElementDTO = null;

        // 收费站
        PlazaDTO pDTO = null;
        // 下一收费站
        PlazaDTO nextPDTO = null;

        // 入口立交
        OverpassDTO entryOverpass = null;
        // 出口立交
        OverpassDTO exitOverpass = null;

        String entry_stake_id = null;
        String exit_stake_id = null;

        if (travelElementCurr.next != null) {
            if (travelElementCurr.next.data instanceof TravelElementDTO) {
                nextTravelElementDTO = (TravelElementDTO) travelElementCurr.next.data;
            }
        }

        if (travelElementCurr.data instanceof TravelElementDTO) {
            travelElementDTO = (TravelElementDTO) travelElementCurr.data;

            // 收费站
            pDTO = travelElementDTO.getPlazaDTO();

            // 入口立交
            entryOverpass = travelElementDTO.getEntryOverpass();
            // 出口立交
            exitOverpass = travelElementDTO.getExitOverpass();

            if (pDTO != null) {

                entry_stake_id = pDTO.getStake_id();
                if (exitOverpass != null) {
                    exit_stake_id = exitOverpass.getEntry_stake_id();
                    entryExitStakeId = new String[] { entry_stake_id, exit_stake_id };
                    return entryExitStakeId;
                } else if (nextTravelElementDTO != null) {
                    // 下一收费站
                    nextPDTO = nextTravelElementDTO.getPlazaDTO();
                    if (nextPDTO != null) {
                        exit_stake_id = nextPDTO.getStake_id();
                    } else if (nextTravelElementDTO.getExitPlazaDTO() != null) {
                        // 下一收费站
                        nextPDTO = nextTravelElementDTO.getExitPlazaDTO();
                        exit_stake_id = nextPDTO.getStake_id();
                    }
                    entryExitStakeId = new String[] { entry_stake_id, exit_stake_id };
                    return entryExitStakeId;
                }
            }
            if (entryOverpass != null) {
                entry_stake_id = entryOverpass.getExit_stake_id();

                if (exitOverpass != null) {
                    exit_stake_id = exitOverpass.getEntry_stake_id();
                    entryExitStakeId = new String[] { entry_stake_id, exit_stake_id };
                    return entryExitStakeId;
                } else if (nextTravelElementDTO != null) {
                    // 下一收费站
                    nextPDTO = nextTravelElementDTO.getPlazaDTO();
                    if (nextPDTO != null) {
                        exit_stake_id = nextPDTO.getStake_id();
                    } else if (nextTravelElementDTO.getExitPlazaDTO() != null) {
                        // 下一收费站
                        nextPDTO = nextTravelElementDTO.getExitPlazaDTO();
                        exit_stake_id = nextPDTO.getStake_id();
                    }
                    entryExitStakeId = new String[] { entry_stake_id, exit_stake_id };
                    return entryExitStakeId;
                }

                entryExitStakeId = new String[] { entry_stake_id, exit_stake_id };
                return entryExitStakeId;
            }
        }

        return entryExitStakeId;
    }

    /**
     * @param travelElementDTOList
     * @return
     */
    private int getPlazaCount(List<TravelElementDTO> travelElementDTOList) {
        int count = 0;

        if (travelElementDTOList != null) {
            for (TravelElementDTO travelElement : travelElementDTOList) {
                if (travelElement.getPlazaDTO() != null) {
                    count++;
                }
                if (travelElement.getExitPlazaDTO() != null) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 
     * @param entry_plaz_code
     * @param plazaCodeArgs
     */
    private String getNextPlaza(String entry_plaz_code, String[] plazaCodeArgs) {
        String nextPlazCode = null;
        if (entry_plaz_code != null && plazaCodeArgs != null)

            for (int i = 0; i < plazaCodeArgs.length; i++) {
                if (entry_plaz_code.equals(plazaCodeArgs[i])) {
                    if (i < plazaCodeArgs.length - 1) {
                        nextPlazCode = plazaCodeArgs[i + 1];
                        return nextPlazCode;
                    }
                }
            }
        return nextPlazCode;
    }

    /**
     * 
     * @param travelElement
     * @return
     */
    private String getHCdoeFromTravelElement(TravelElementDTO travelElement) {

        String hCode = null;

        if (travelElement == null) {
            return hCode;
        }
        if (travelElement.getPlazaDTO() != null) {
            hCode = travelElement.getPlazaDTO().getH_code();
        } else if (travelElement.getExitPlazaDTO() != null) {
            hCode = travelElement.getExitPlazaDTO().getH_code();
        } else if (travelElement.getEntryOverpass() != null) {
            hCode = travelElement.getEntryOverpass().getExit_highway_code();
        } else if (travelElement.getExitOverpass() != null) {
            hCode = travelElement.getExitOverpass().getEntry_highway_code();
        }

        return hCode;
    }

    /**
     * 取得行驶方向
     * 
     * @param entryStakeId
     * @param eixtStakeId
     * @return
     */
    private String getTravelDirection(String entryStakeId, String eixtStakeId) {

        float pStakeId = CommonUtil.toFloat(entryStakeId, 0);
        float pNextStakeId = CommonUtil.toFloat(eixtStakeId, 0);
        return pStakeId < pNextStakeId ? "0" : "1";
    }

    /**
     * 取得管制信息元素位置方向（1：左，2：右，3：两边）
     * 
     * @param direction
     * @param entryStakeId
     * @param eixtStakeId
     * @return
     */
    private int getRciElementDirection(String direction, String entryStakeId, String eixtStakeId) {

        if (direction == null || entryStakeId == null || eixtStakeId == null) {
            return 0;
        }
        int rCIElementDirection = 0;

        String travelDirection = getTravelDirection(entryStakeId, eixtStakeId);

        if ("3".equals(direction)) {
            rCIElementDirection = 3;
        } else {

            if ("0".equals(travelDirection)) {
                if ("1".equals(direction)) {
                    rCIElementDirection = 1;
                } else if ("2".equals(direction)) {
                    rCIElementDirection = 2;
                }
            } else if ("1".equals(travelDirection)) {
                if ("2".equals(direction)) {
                    rCIElementDirection = 1;
                } else if ("1".equals(direction)) {
                    rCIElementDirection = 2;
                }
            }
        }
        return rCIElementDirection;
    }

    /**
     * 取得元素（管制信息除外）位置方向（1：左，2：右，3：两边）
     * 
     * @param direction
     *            元素方向
     * @param entryStakeId
     *            入口收费站或立交桩号
     * @param eixtStakeId
     *            出口收费站或立交桩号
     * @return
     */
    private int getElementDirection(String direction, String entryStakeId, String eixtStakeId) {

        if (direction == null || entryStakeId == null || eixtStakeId == null) {
            return 0;
        }

        int elementDirection = 0;
        String travelDirection = getTravelDirection(entryStakeId, eixtStakeId);

        if ("2".equals(direction)) {
            elementDirection = 3;
        } else {
            if ("0".equals(travelDirection)) {
                if ("0".equals(direction)) {
                    elementDirection = 1;
                } else if ("1".equals(direction)) {
                    elementDirection = 2;
                }
            } else if ("1".equals(travelDirection)) {
                if ("1".equals(direction)) {
                    elementDirection = 1;
                } else if ("0".equals(direction)) {
                    elementDirection = 2;
                }
            }
        }

        return elementDirection;
    }

    /**
     * 判断管制信息是否显示
     * 
     * @param elementHCode
     * @param travelElement
     * @param direction
     *            元素方向
     * @param startStakeId
     *            管制开始桩号
     * @param endStakeId
     *            管制结束桩号
     * @param entryStakeId
     *            入口收费站或立交桩号
     * @param eixtStakeId
     *            出口收费站或立交桩号
     * @return
     */
    // private boolean isShowRciElement(String elementHCode, TravelElementDTO
    // travelElement, String direction, String startStakeId, String endStakeId,
    // String entryStakeId, String eixtStakeId) {
    //
    // String travelElementHCode =
    // this.getHCdoeFromTravelElement(travelElement);
    //
    // if (travelElementHCode == null || elementHCode == null ||
    // !elementHCode.equals(travelElementHCode)) {
    // return false;
    // }
    //
    // boolean isShow = false;
    // // 1:即时路况页面(主页)
    // if (screenType == 1) {
    // isShow = this.isShowRciElement1(direction, startStakeId, endStakeId,
    // entryStakeId, eixtStakeId)
    // || this.isShowRciElement2(direction, startStakeId, endStakeId,
    // entryStakeId, eixtStakeId);
    // } else if (screenType == 2) {// 2:行程规划
    // isShow = this.isShowRciElement2(direction, startStakeId, endStakeId,
    // entryStakeId, eixtStakeId);
    // } else {
    // isShow = false;
    // }
    // return isShow;
    // }
    /**
     * 判断管制信息是否显示
     * 
     * @param elementHCode
     * @param travelElement
     * @param direction
     *            元素方向
     * @param startStakeId
     *            管制开始桩号
     * @param endStakeId
     *            管制结束桩号
     * @param entryStakeId
     *            入口收费站或立交桩号
     * @param eixtStakeId
     *            出口收费站或立交桩号
     * @return
     */
    private boolean isShowRciElement(String elementHCode, TravelElementDTO travelElement, String direction, String startStakeId, String endStakeId,
                    String entryStakeId, String eixtStakeId) {

        String rciHCode = this.getHCdoeFromTravelElement(travelElement);
        boolean isShow = RoadInfoUtil.isShowRciElement(elementHCode, rciHCode, direction, startStakeId, endStakeId, entryStakeId, eixtStakeId);
        return isShow;
    }

    /**
     * 判断元素（管制信息除外）是否显示
     * 
     * @param elementHCode
     * @param travelElement
     * @param direction
     *            元素方向
     * @param stakeId
     *            元素桩号
     * @param entryStakeId
     *            入口收费站或立交桩号
     * @param eixtStakeId
     *            出口收费站或立交桩号
     * @return
     */
    private boolean isShowElement(String elementHCode, TravelElementDTO travelElement, String direction, String stakeId, String entryStakeId, String eixtStakeId) {

        String travelElementHCode = this.getHCdoeFromTravelElement(travelElement);

        if (travelElementHCode == null || elementHCode == null || !elementHCode.equals(travelElementHCode)) {
            return false;
        }

        boolean isShow = false;
        // 1:即时路况页面(主页)
        if (screenType == 1) {
            isShow = this.isShowElement1(direction, stakeId, entryStakeId, eixtStakeId) || this.isShowElement2(direction, stakeId, entryStakeId, eixtStakeId);
        } else if (screenType == 2) {// 2:行程规划
            isShow = this.isShowElement2(direction, stakeId, entryStakeId, eixtStakeId);
        } else {
            isShow = false;
        }
        return isShow;
    }

    /**
     * screenType值为2的显示判断
     * 
     * 判断管制信息是否显示
     * 
     * @param direction
     *            元素方向
     * @param startStakeId
     *            管制开始桩号
     * @param endStakeId
     *            管制结束桩号
     * @param entryStakeId
     *            入口收费站或立交桩号
     * @param eixtStakeId
     *            出口收费站或立交桩号
     * @return
     */
    private boolean isShowRciElement2(String direction, String startStakeId, String endStakeId, String entryStakeId, String eixtStakeId) {

        if (startStakeId == null || endStakeId == null || entryStakeId == null || eixtStakeId == null) {
            return false;
        }
        // if (direction == null) {
        //
        // }
        boolean isShow = false;

        float fStartStakeId = CommonUtil.toFloat(startStakeId, 0);
        float fEndStakeId = CommonUtil.toFloat(endStakeId, 0);
        float entry_stake_id = CommonUtil.toFloat(entryStakeId, 0);
        float exit_stake_id = CommonUtil.toFloat(eixtStakeId, 0);

        if (!"3".equals(direction)) {
            if ("1".equals(direction)) {
                isShow = RoadInfoUtil.judgeCrossOrder(fStartStakeId, fEndStakeId, entry_stake_id, exit_stake_id);
            } else if ("2".equals(direction)) {
                isShow = RoadInfoUtil.judgeCrossOrder(fStartStakeId, fEndStakeId, exit_stake_id, entry_stake_id);
            }
        } else if ("3".equals(direction)) {
            isShow = RoadInfoUtil.judgeCrossOrder(fStartStakeId, fEndStakeId, entry_stake_id, exit_stake_id)
                            || RoadInfoUtil.judgeCrossOrder(fStartStakeId, fEndStakeId, exit_stake_id, entry_stake_id);
        }

        return isShow;
    }

    /**
     * 
     * 判断管制信息是否显示
     * 
     * @param direction
     *            元素方向
     * @param startStakeId
     *            管制开始桩号
     * @param endStakeId
     *            管制结束桩号
     * @param entryStakeId
     *            入口收费站或立交桩号
     * @param eixtStakeId
     *            出口收费站或立交桩号
     * @return
     */
    private boolean isShowRciElement1(String direction, String startStakeId, String endStakeId, String entryStakeId, String eixtStakeId) {
        return this.isShowRciElement2(direction, startStakeId, endStakeId, eixtStakeId, entryStakeId);
    }

    /**
     * screenType值为2的显示判断
     * 
     * 判断元素（管制信息除外）是否显示
     * 
     * @param direction
     *            元素方向
     * @param stakeId
     *            元素桩号
     * @param entryStakeId
     *            入口收费站或立交桩号
     * @param eixtStakeId
     *            出口收费站或立交桩号
     * @return
     */
    private boolean isShowElement2(String direction, String stakeId, String entryStakeId, String eixtStakeId) {

        if (stakeId == null || entryStakeId == null || eixtStakeId == null) {
            return false;
        }

        // if (direction == null) {
        //
        // }

        boolean isShow = false;
        String travelDirection = getTravelDirection(entryStakeId, eixtStakeId);

        float stake_id = CommonUtil.toFloat(stakeId, 0);
        float entry_stake_id = CommonUtil.toFloat(entryStakeId, 0);
        float exit_stake_id = CommonUtil.toFloat(eixtStakeId, 0);

        if (direction.equals(travelDirection)) {
            if ("0".equals(direction)) {
                isShow = stake_id <= exit_stake_id && stake_id >= entry_stake_id;
            } else if ("1".equals(direction)) {
                isShow = stake_id >= exit_stake_id && stake_id <= entry_stake_id;
            }
        } else if ("2".equals(direction)) {
            isShow = (stake_id <= exit_stake_id && stake_id >= entry_stake_id) || (stake_id >= exit_stake_id && stake_id <= entry_stake_id);
        }
        return isShow;

    }

    /**
     * 
     * 判断元素（管制信息除外）是否显示
     * 
     * @param direction
     *            元素方向
     * @param stakeId
     *            元素桩号
     * @param entryStakeId
     *            入口收费站或立交桩号
     * @param eixtStakeId
     *            出口收费站或立交桩号
     * @return
     */
    private boolean isShowElement1(String direction, String stakeId, String entryStakeId, String eixtStakeId) {
        return this.isShowElement2(direction, stakeId, eixtStakeId, entryStakeId);
    }

}
