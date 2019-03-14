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
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.road.dto.WeatherForcastDTO;
import com.djb.highway.road.dto.weather.WeatherDTO;
import com.djb.highway.road.dto.weather.WeatherDto2Entity;
import com.djb.highway.road.dto.weather.WeatherJson2DTO;
import com.djb.highway.road.dtoclient.CityWeatherBean;
import com.djb.highway.road.dtoclient.CityWeatherBeanBaseDTO;
import com.djb.highway.road.dtoclient.CityWeatherBeanDTO;
import com.djb.highway.road.entity.WeatherForcastEntity;
import com.djb.highway.road.service.IWeatherForcastService;
import com.google.gson.reflect.TypeToken;

@Controller("/WeatherForcast")
public class WeatherForcastAction extends BaseAction {

    @Autowired
    @Qualifier("iWeatherForcastService")
    private IWeatherForcastService iWeatherForcastService;

    private String DateStamp;

    public WeatherForcastAction() {
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
    public ActionForward doGetWeatherForcastList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        WeatherForcastDTO paramDTO = (WeatherForcastDTO) form;
        // 结果DTO
        WeatherForcastDTO resultDTO = new WeatherForcastDTO();
        // 参数Entity
        WeatherForcastEntity paramEntity = new WeatherForcastEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);

        List<List<WeatherForcastDTO>> cityList = null;
        try {
            // DB操作
            List<WeatherForcastEntity> weatherForcastList = iWeatherForcastService.getWeatherForcastList();
            cityList = new ArrayList<List<WeatherForcastDTO>>();
            List<WeatherForcastDTO> weartherList = new ArrayList<WeatherForcastDTO>();
            int cityID = 1;
            int count = 1;

            for (WeatherForcastEntity w : weatherForcastList) {
                if ((cityID == w.getCity_id()) && (count < 5)) {
                    WeatherForcastDTO wDTO = new WeatherForcastDTO();
                    CommonUtil.reflectClass(w, wDTO);
                    weartherList.add(wDTO);
                    count++;
                }
                if (count == 5) {
                    count = 1;
                    cityID++;
                    cityList.add(weartherList);
                    weartherList = new ArrayList<WeatherForcastDTO>();
                }

            }
        } catch (Exception e) {

            resultDTO.setErrFlg(true);
            formMessages(resultDTO, Constants.MSG_KEY_WEATHER_ERROR, null);
        }

        // 结果Entity -> 结果DTO
        resultDTO.setCitylist(cityList);
        // android
        toJson(response, resultDTO);
        // ;
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
    public ActionForward doGetWeatherForcast(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 参数DTO
        WeatherForcastDTO paramDTO = (WeatherForcastDTO) form;
        // paramDTO.setCity_code("SHENYANG");
        // 结果DTO
        WeatherForcastDTO resultDTO = new WeatherForcastDTO();
        // 参数Entity
        WeatherForcastEntity paramEntity = new WeatherForcastEntity();

        // 结果Entity
        WeatherForcastEntity resultEntity = new WeatherForcastEntity();
        // 参数DTO -> 参数Entity
        CommonUtil.reflectClass(paramDTO, paramEntity);

        try {
            // DB操作
            resultEntity = iWeatherForcastService.getCurObject(paramEntity);
            // 结果Entity -> 结果DTO
            CommonUtil.reflectClass(resultEntity, resultDTO);

        } catch (Exception e) {

            resultDTO.setErrFlg(true);
            formMessages(resultDTO, Constants.MSG_KEY_WEATHERFORCAST_ERROR, null);
        }

        // android
        toJson(response, resultDTO);
        // ;
        return null;
    }

    // /**
    // *
    // * @param mapping
    // * The ActionMapping used to select this instance
    // * @param form
    // * The optional ActionForm bean for this request
    // * @param request
    // * The servlet request we are processing
    // * @param response
    // * The servlet response we are creating
    // *
    // * @exception Exception
    // * if business logic throws an exception
    // */
    // @SuppressWarnings("unchecked")
    // public ActionForward doGetWeatherForcastByCityName(ActionMapping mapping,
    // ActionForm form, HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // // 参数DTO
    // WeatherForcastDTO paramDTO = (WeatherForcastDTO) form;
    // // paramDTO.setCity_code("SHENYANG");
    // // 结果DTO
    // WeatherForcastDTO resultDTO = new WeatherForcastDTO();
    // // 页面DTO
    // WeatherCityDTO cityWeatherBeanDTO = new WeatherCityDTO();
    //
    // // 参数Entity
    // WeatherForcastEntity paramEntity = new WeatherForcastEntity();
    //
    // // 结果Entity
    // List<WeatherForcastEntity> resultEntityList = new
    // ArrayList<WeatherForcastEntity>();
    // // 参数DTO -> 参数Entity
    // paramEntity.setCitycode(paramDTO.getCity_code());
    //
    // try {
    // // DB操作
    // resultEntityList = iWeatherForcastService
    // .getWeatherForcastList(paramEntity);
    // // 结果Entity -> 结果DTO
    // List<WeatherForcastDTO> resultDTOList = new
    // ArrayList<WeatherForcastDTO>();
    // for (WeatherForcastEntity wf : resultEntityList) {
    // WeatherForcastDTO weatherForcastDTO = new WeatherForcastDTO();
    // CommonUtil.reflectClass(wf, weatherForcastDTO);
    // resultDTOList.add(weatherForcastDTO);
    // }
    // resultDTO.setList(resultDTOList);
    // resultDTO.setCity_name(resultEntityList.get(0).getCityname());
    // resultDTO.setCity_code(String.valueOf(resultEntityList.get(0)
    // .getCitycode()));
    // } catch (Exception e) {
    //
    // resultDTO.setErrFlg(true);
    // formMessages(resultDTO, Constants.MSG_KEY_WEATHERFORCAST_ERROR,
    // null);
    // }
    //
    // // 处理页面DTO
    // List<WeatherInfoItemDTO> cityWeatherList = new
    // ArrayList<WeatherInfoItemDTO>();
    //
    // for (WeatherForcastDTO wfd : resultDTO.getList()) {
    //
    // WeatherInfoItemDTO _cityWeatherBean = new WeatherInfoItemDTO();
    // // 天气信息赋值：
    // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    // _cityWeatherBean.setmDate(sf.format(wfd.getWf_date()));
    // // 星期:
    // _cityWeatherBean.setmWeekDay(wfd.getWeekday());
    // // 真实天气温度
    // _cityWeatherBean.setmTempReal(wfd.getCurr_temperature());
    // // 温度范围：
    // _cityWeatherBean.setmTempRegion(wfd.getTemperature());
    // // 天气图片：白天
    // _cityWeatherBean.setmPicDay(wfd.getDay_pic_name());
    // // 天气图片：黑夜
    // _cityWeatherBean.setmPicNight(wfd.getNight_pic_name());
    // cityWeatherList.add(_cityWeatherBean);
    // }
    //
    // cityWeatherBeanDTO.setmWeatherInfo(cityWeatherList);
    // cityWeatherBeanDTO.setmCityID(resultDTO.getCity_code());
    // cityWeatherBeanDTO.setmCityName(resultDTO.getCity_name());
    //
    // if (resultDTO.isErrFlg() == true) {
    // cityWeatherBeanDTO.setReturnCode("-1701");
    // } else {
    // cityWeatherBeanDTO.setReturnCode("0");
    // }
    //
    // // android
    // toJson(response, cityWeatherBeanDTO);
    // // ;
    // return null;
    // }

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
    public ActionForward doGetWeatherForcastByCityName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        WeatherForcastDTO paramDTO = (WeatherForcastDTO) form;
        // paramDTO.setCity_code("SHENYANG");
        // 结果DTO
        WeatherForcastDTO resultDTO = new WeatherForcastDTO();
        // 页面DTO
        CityWeatherBeanDTO cityWeatherBeanDTO = new CityWeatherBeanDTO();

        // 参数Entity
        WeatherForcastEntity paramEntity = new WeatherForcastEntity();

        // 结果Entity
        List<WeatherForcastEntity> resultEntityList = new ArrayList<WeatherForcastEntity>();
        // 参数DTO -> 参数Entity
        // CommonUtil.reflectClass(paramDTO, paramEntity);
        paramEntity.setCitycode(paramDTO.getCity_code());
        try {
            // DB操作
            resultEntityList = iWeatherForcastService.getWeatherForcastList(paramEntity);
            // 结果Entity -> 结果DTO

            if (resultEntityList != null) {
                List<WeatherForcastDTO> resultDTOList = new ArrayList<WeatherForcastDTO>();
                for (WeatherForcastEntity wf : resultEntityList) {
                    WeatherForcastDTO weatherForcastDTO = new WeatherForcastDTO();
                    CommonUtil.reflectClass(wf, weatherForcastDTO);
                    resultDTOList.add(weatherForcastDTO);
                }
                resultDTO.setList(resultDTOList);
                resultDTO.setCity_code(resultEntityList.get(0).getCitycode());
                resultDTO.setCity_name(resultEntityList.get(0).getCityname());
                resultDTO.setWf_date(resultEntityList.get(0).getWf_date());
                resultDTO.setWeekday(resultEntityList.get(0).getWeekday());
            }

        } catch (Exception e) {

            resultDTO.setErrFlg(true);
            formMessages(resultDTO, Constants.MSG_KEY_WEATHERFORCAST_ERROR, null);
        }

        // 处理页面DTO
        // 赋值操作
        cityWeatherBeanDTO = doBackDTOtoClientDTO(cityWeatherBeanDTO, resultDTO);

        // android
        toJson(response, cityWeatherBeanDTO);
        // ;
        return null;
    }

    // 天气新接口：获取对应的多个城市的天气信息

    @SuppressWarnings("unchecked")
    public ActionForward doGetWeatherForcastListByCityName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {

        // 参数DTO
        WeatherForcastDTO paramDTO = (WeatherForcastDTO) form;

        // test
        // 格式化时间戳
        // paramDTO.setWeather_version_time("2014-07-24 13:33");
        // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // paramDTO.setWeather_version_time(paramDTO.getWeather_version_time());

        // paramDTO.setCity_code("SHENYANG,DALIAN");
        // 结果DTO
        WeatherForcastDTO resultDTO = new WeatherForcastDTO();
        CityWeatherBeanBaseDTO cityWeatherBeanBaseDTO = null;
        // 页面DTO
        CityWeatherBeanDTO cityWeatherBeanDTO = null;

        // 参数Entity
        WeatherForcastEntity paramEntity = null;

        // 结果Entity
        List<WeatherForcastEntity> resultEntityList = null;
        // 参数DTO -> 参数Entity
        // CommonUtil.reflectClass(paramDTO, paramEntity);

        try {

            // 获取更新时间戳
            String dateStamp = paramDTO.getWeather_version_time();
            // 如果需要更新，从数据库获取数据

            if (weatherUpdateMethod(dateStamp)) {

                // DB操作
                cityWeatherBeanBaseDTO = new CityWeatherBeanBaseDTO();
                List<CityWeatherBeanDTO> cityWeatherBeanDTOList = new ArrayList<CityWeatherBeanDTO>();
                String[] strArray = paramDTO.getCity_code().split(",");

                for (String str : strArray) {
                    paramEntity = new WeatherForcastEntity();
                    resultEntityList = new ArrayList<WeatherForcastEntity>();
                    cityWeatherBeanDTO = new CityWeatherBeanDTO();
                    paramEntity.setCitycode(str);
                    resultEntityList = iWeatherForcastService.getWeatherForcastList(paramEntity);

                    // 结果Entity -> 结果DTO

                    if (resultEntityList != null) {
                        List<WeatherForcastDTO> resultDTOList = new ArrayList<WeatherForcastDTO>();
                        for (WeatherForcastEntity wf : resultEntityList) {
                            WeatherForcastDTO weatherForcastDTO = new WeatherForcastDTO();
                            CommonUtil.reflectClass(wf, weatherForcastDTO);
                            weatherForcastDTO.setCity_code(wf.getCitycode());
                            weatherForcastDTO.setCity_name(wf.getCityname());
                            resultDTOList.add(weatherForcastDTO);
                        }
                        resultDTO.setList(resultDTOList);
                    }

                    // 处理页面DTO
                    // 赋值操作
                    cityWeatherBeanDTO = doBackDTOtoClientDTO(cityWeatherBeanDTO, resultDTO);

                    cityWeatherBeanDTOList.add(cityWeatherBeanDTO);
                }

                cityWeatherBeanBaseDTO.setCitylist(cityWeatherBeanDTOList);

            } else {
                cityWeatherBeanBaseDTO = new CityWeatherBeanBaseDTO();
                cityWeatherBeanBaseDTO.setCitylist(new ArrayList<CityWeatherBeanDTO>());

            }

        } catch (Exception e) {

            resultDTO.setErrFlg(true);
            cityWeatherBeanBaseDTO.setReturnCode("-1701");
            formMessages(resultDTO, Constants.MSG_KEY_WEATHERFORCAST_ERROR, null);
        }

        if (!resultDTO.isErrFlg()) {

            if (cityWeatherBeanBaseDTO.getCitylist() != null && cityWeatherBeanBaseDTO.getCitylist().size() != 0) {

                cityWeatherBeanBaseDTO.setReturnCode("0");
                cityWeatherBeanBaseDTO.setWeather_version_time(DateStamp);
            } else {
                cityWeatherBeanBaseDTO.setReturnCode("1");
                cityWeatherBeanBaseDTO.setWeather_version_time(DateStamp);
            }

        }
        // android
        toJson(response, cityWeatherBeanBaseDTO);
        // ;
        return null;
    }

    // 天气字段赋值：

    public CityWeatherBeanDTO doBackDTOtoClientDTO(CityWeatherBeanDTO cityWeatherBeanDTO, WeatherForcastDTO resultDTO) {

        // 处理页面DTO
        if (resultDTO != null) {

            List<CityWeatherBean> cityWeatherList = new ArrayList<CityWeatherBean>();
            // String tempRegion = null;
            // String tempRegionResult = null;
            for (WeatherForcastDTO wfd : resultDTO.getList()) {

                CityWeatherBean _cityWeatherBean = new CityWeatherBean();
                _cityWeatherBean.setCityCode(wfd.getCity_code());
                _cityWeatherBean.setCityName(wfd.getCity_name());
                _cityWeatherBean.setDay_pic_name(wfd.getDay_pic_name());
                _cityWeatherBean.setNight_pic_name(wfd.getNight_pic_name());
                _cityWeatherBean.setTempReal(wfd.getCurr_temperature());

                // tempRegion = wfd.getTemperature();
                // tempRegionResult = tempRegion.replace('℃', ' ');
                // tempRegionResult = (tempRegion.replaceAll("℃",
                // "")).replaceAll(
                // "~", ",");
                // tempRegionResult= "5";
                _cityWeatherBean.setTempRegion(wfd.getTemperature());
                // _cityWeatherBean.setWeather(wfd.getWeather());
                _cityWeatherBean.setWeekDay(wfd.getWeekday());
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                _cityWeatherBean.setWf_date(sf.format(wfd.getWf_date()));
                cityWeatherList.add(_cityWeatherBean);
            }

            cityWeatherBeanDTO.setList(cityWeatherList);
            // cityWeatherBeanDTO.setCityCode(resultDTO.getCity_code());
            // cityWeatherBeanDTO.setCityName(resultDTO.getCity_name());
            // cityWeatherBeanDTO.setWeekDay(resultDTO.getWeekday());
            // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            // cityWeatherBeanDTO.setWf_date(sf.format(resultDTO.getWf_date()));

            // if (resultDTO.isErrFlg() == true) {
            // cityWeatherBeanDTO.setReturnCode(1);
            // } else {
            // cityWeatherBeanDTO.setReturnCode(0);
            // }

        }

        return cityWeatherBeanDTO;
    }

    // 是否需要更新判断
    public boolean weatherUpdateMethod(String dateStamp) throws Exception {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date paramDate = sf.parse(dateStamp);
        Date tempDate = null;

        WeatherForcastEntity weatherForcastEntity = new WeatherForcastEntity();
        weatherForcastEntity.setCitycode("SHENYANG");
        WeatherForcastEntity tempObj = iWeatherForcastService.getCurObject(weatherForcastEntity);
        // 获取最新的时间戳
        if (tempObj != null) {
            tempDate = tempObj.getWeather_deploy_time();
        } else {
            tempDate = new Date();
        }

        // 更新时间戳
        Date curTempDate = sf.parse(sf.format(tempDate));

        DateStamp = sf.format(tempDate);

        // 如果paramDate 比 tempDate 时间大，返回true,访问数据库，获取数据
        if (paramDate.after(curTempDate) || paramDate.equals(curTempDate)) {
            return false;
        } else {
            return true;
        }

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
	public ActionForward doInsertWeather(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String wStr = request.getParameter("wStr");
		if (wStr == null) {
			return null;
		}

		try {

			WeatherDTO dto = WeatherJson2DTO.getWeatherJsonObject(
					new TypeToken<WeatherDTO>() {
					}, wStr);

			// 如果返回的json字符窜有错误
			if (dto == null || dto.getError() != 0) {
				return null;
			}
			
			// test
			//iWeatherForcastService.deleteWeatherForcast(new WeatherForcastEntity());
			//;
			
			// DTO → Entity
			List<WeatherForcastEntity> list = WeatherDto2Entity
					.WeatherDTOToWeatherEntity(dto);

			// do update
			if (list != null) {

				for (WeatherForcastEntity wfe : list) {
					// do insert
					// 添加时间戳字段数据
					wfe.setWeather_deploy_time(new Date());

					// do search
					WeatherForcastEntity param = new WeatherForcastEntity();
					param.setWf_id(wfe.getWf_id());

					try {
						WeatherForcastEntity result = iWeatherForcastService
								.getObject(param);

						if (result != null) {
							// do update
							iWeatherForcastService.updateWeatherForcast(wfe);
						} else {
							// dao insert
							iWeatherForcastService.addWeatherForcast(wfe);
						}
						//test
						//iWeatherForcastService.addWeatherForcast(wfe);
						//;
					} catch (Exception e) {
						// e.printStackTrace();
					}

				}

			}

		} catch (Exception e) {
			// e.printStackTrace();
			throw e;
		} finally {
		}

		return null;
	}

}
