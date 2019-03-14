package com.djb.highway.road.action;

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
import com.djb.highway.common.util.ResourceLocator;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.road.dto.MenuPageDTO;
import com.djb.highway.road.dto.RoadControlInfoDTO;
import com.djb.highway.road.dto.WeatherForcastDTO;
import com.djb.highway.road.dtoclient.HomeListDTO;
import com.djb.highway.road.dtoclient.RoadDTO;
import com.djb.highway.road.dtoclient.UrlDTO;
import com.djb.highway.road.entity.RoadControlInfoEntity;
import com.djb.highway.road.entity.WeatherForcastEntity;
import com.djb.highway.road.service.IRoadControlInfoService;
import com.djb.highway.road.service.IWeatherForcastService;

@Controller("/MenuPage")
public class MenuPageAction extends BaseAction {

	@Autowired
	@Qualifier("iRoadControlInfoService")
	private IRoadControlInfoService iRoadControlInfoService;

	@Autowired
	@Qualifier("iWeatherForcastService")
	private IWeatherForcastService iWeatherForcastService;

	public MenuPageAction() {
		super();
	}

	public ActionForward doGetMenuPageLiveList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 参数DTO
		MenuPageDTO paramDTO = (MenuPageDTO) form;
		// paramDTO.setCity_code("SHENYANG");
		// 结果DTO
		MenuPageDTO resultDTO = new MenuPageDTO();
		HomeListDTO resultHomeListDTO = new HomeListDTO();
		try {
			HomeListDTO resultWeatherDTO = GetWeatherForcastByCityName(paramDTO);
			resultHomeListDTO = doGetRoadControlInfoLiveList(paramDTO);

			resultHomeListDTO.setCityCode(resultWeatherDTO.getCityCode());
			resultHomeListDTO.setCityName(resultWeatherDTO.getCityName());
			resultHomeListDTO.setDay_pic_name(resultWeatherDTO
					.getDay_pic_name());
			resultHomeListDTO.setNight_pic_name(resultWeatherDTO
					.getNight_pic_name());
			resultHomeListDTO.setTempReal(resultWeatherDTO.getTempReal());
			HomeListDTO resultAdDTO = doGetAdPicList(paramDTO);
			resultHomeListDTO.setUrlList(resultAdDTO.getUrlList());
		} catch (Exception e) {
			resultHomeListDTO.setReturnCode("-2700");
		}
		resultHomeListDTO.setReturnCode("0");
		toJson(response, resultHomeListDTO);
		// ;
		return null;
	}

	// 获取天气信息的方法
	private HomeListDTO GetWeatherForcastByCityName(MenuPageDTO paramDTO) {
		WeatherForcastEntity paramEntity = new WeatherForcastEntity();
		paramEntity.setCitycode(paramDTO.getCity_code());
		WeatherForcastEntity resultEntity = new WeatherForcastEntity();
		// 结果DTO
		WeatherForcastDTO resultWetherDTO = new WeatherForcastDTO();
		// 页面DTO
		HomeListDTO resultDTO = new HomeListDTO();
		try {
			resultEntity = iWeatherForcastService.getCurObject(paramEntity);
			resultDTO.setCityCode(resultEntity.getCitycode());
			resultDTO.setCityName(resultEntity.getCityname());
			resultDTO.setDay_pic_name(resultEntity.getDay_pic_name());
			resultDTO.setNight_pic_name(resultEntity.getNight_pic_name());
			resultDTO.setTempReal(resultEntity.getCurr_temperature());
		} catch (Exception e) {

			resultWetherDTO.setErrFlg(true);

		}
		return resultDTO;
	}

	private HomeListDTO doGetRoadControlInfoLiveList(MenuPageDTO paramDTO) {
		// 参数Entity
		RoadControlInfoEntity paramEntity = new RoadControlInfoEntity();
		paramEntity.setH_code(paramDTO.getH_code());
		// 结果DTO
		RoadControlInfoDTO resultDTO = new RoadControlInfoDTO();
		RoadControlInfoDTO resultRoadDTO = new RoadControlInfoDTO();
		// 结果Entity
		List<RoadControlInfoEntity> resultEntityList = null;
		HomeListDTO resultHomeDTO = new HomeListDTO();
		// 结果DTO
		List<RoadControlInfoDTO> resultDTOList = new ArrayList<RoadControlInfoDTO>();
		// DB操作

		try {
			// 日期格式化处理
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//
			// paramEntity.setDeploy_time(sdf.parse(sdf.format(new Date())));

			resultEntityList = iRoadControlInfoService
					.getRoadControlInfoList(paramEntity);
			if (resultEntityList != null) {
				for (RoadControlInfoEntity r : resultEntityList) {
					RoadControlInfoDTO _resultDTO = new RoadControlInfoDTO();
					CommonUtil.reflectClass(r, _resultDTO);
					resultDTOList.add(_resultDTO);
				}
				resultDTO.setList(resultDTOList);
			}

			// 给页面DTO赋值
			// 结果DTO
			List<RoadDTO> resultClearDTO = new ArrayList<RoadDTO>();
			for (RoadControlInfoDTO section : resultDTO.getList()) {
				RoadDTO _resultDTO = new RoadDTO();

				_resultDTO.setHighway_name(section.getH_name());
				_resultDTO.setEvent_id(section.getRci_id());
				// 管制范围：暂时为：起点桩号-终点桩号
				String control_scope = section.getStart_stake_id() + "公里至"
						+ section.getEnd_stake_id() + "公里";
				_resultDTO.setControl_scope(control_scope);
				_resultDTO.setHigh_code(section.getH_code());
				_resultDTO.setRoad_type(section.getControl_mode());
				_resultDTO.setRoad_reason(section.getRci_type());
				// 开始时间
				_resultDTO.setStart_time(CommonUtil.dateToString(
						section.getStart_time(), "yyyy年MM月dd日 HH时"));
				// 预计结束时间
				_resultDTO.setEnd_time(CommonUtil.dateToString(
						section.getPlan_end_time(), "yyyy年MM月dd日 HH时"));
				resultClearDTO.add(_resultDTO);
			}
			resultHomeDTO.setRoadList(resultClearDTO);

		} catch (Exception e) {
			resultDTO.setErrFlg(true);
		}

		return resultHomeDTO;

	}

	private HomeListDTO doGetAdPicList(MenuPageDTO paramDTO) {
		// UrlDTO urlDTO=new UrlDTO();
		UrlDTO resultDTO = new UrlDTO();

		List<UrlDTO> resultClearDTO = new ArrayList<UrlDTO>();
		HomeListDTO resultHomeDTO = new HomeListDTO();
		String hostIp = ResourceLocator.getI18NMessage(this,
				Constants.MSG_KEY_HOST_ADDRESS);
		for (int i = 1; i <= 4; i++) {
			UrlDTO urlDTO = new UrlDTO();
			urlDTO.setUrl(hostIp + "img_ad/" + "pic" + i + ".jpg");
			resultClearDTO.add(urlDTO);
			resultHomeDTO.setUrlList(resultClearDTO);
		}

		return resultHomeDTO;
	}

}
