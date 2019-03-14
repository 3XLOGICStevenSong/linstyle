package com.djb.highway.road.dto.weather;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.common.util.Constants;
import com.djb.highway.road.entity.WeatherForcastEntity;

public class WeatherDto2Entity {

    public static List<WeatherForcastEntity> WeatherDTOToWeatherEntity(
            WeatherDTO weatherDTO) {
        List<WeatherForcastEntity> list = new ArrayList<WeatherForcastEntity>();
        try {
            if (weatherDTO != null && weatherDTO.getError() == 0) {
				int wf_id = 0;
                for (WeatherResult wr : weatherDTO.getResults()) {

                    String curr_temperature = "0";
                    Date CurrentDate = null;
                    for (WeatherData wd : wr.getWeather_data()) {

                        WeatherForcastEntity entity = new WeatherForcastEntity();
                        if (CurrentDate == null) {
							CurrentDate = CommonUtil.stringToDate(
									weatherDTO.getDate(),
									Constants.DATE_PATTERN_YYMMDD);
                        } else {
                            CurrentDate = CommonUtil.addOneDay(CurrentDate);
                        }
                        entity.setWf_date(CurrentDate);

                        entity.setCityname(wr.getCurrentCity());
                        setCityId(wr.getCurrentCity(), entity);

                        entity.setTemperature(wd.getTemperature());

                        entity.setDay_pic_name(wd.getDayPictureUrl().substring(
                                wd.getDayPictureUrl().lastIndexOf("/") + 1,
                                wd.getDayPictureUrl().lastIndexOf(".png")));

                        entity.setNight_pic_name(wd.getNightPictureUrl()
                                .substring(
                                        wd.getNightPictureUrl()
                                                .lastIndexOf("/") + 1,
                                        wd.getNightPictureUrl().lastIndexOf(
                                                ".png")));

                        entity.setWeather(wd.getWeather());

                        entity.setWind(wd.getWind());

                        if (wd.getDate().length() > 2) {

                            entity.setWeekday(wd.getDate().substring(0, 2));

                            curr_temperature = wd.getDate().substring(
                                    wd.getDate().lastIndexOf("：") + 1,
                                    wd.getDate().lastIndexOf(")"));

                        } else {
                            entity.setWeekday(wd.getDate());
						}
						entity.setCurr_temperature(curr_temperature);
						// 设置检索主键
						wf_id++;
						entity.setWf_id(wf_id);
						list.add(entity);
                    }

                }

            }
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return list;
    }

    private static void setCityId(String cityName, WeatherForcastEntity entity) {
        if ("沈阳".equals(cityName)) {
            entity.setCity_id(1);
            entity.setCitycode("SHENYANG");
        } else if ("大连".equals(cityName)) {
            entity.setCity_id(2);
            entity.setCitycode("DALIAN");
        } else if ("鞍山".equals(cityName)) {
            entity.setCity_id(3);
            entity.setCitycode("ANSHAN");
        } else if ("抚顺".equals(cityName)) {
            entity.setCity_id(4);
            entity.setCitycode("FUSHUN");
        } else if ("本溪".equals(cityName)) {
            entity.setCity_id(5);
            entity.setCitycode("BENXI");
        } else if ("丹东".equals(cityName)) {
            entity.setCity_id(6);
            entity.setCitycode("DANDONG");
        } else if ("锦州".equals(cityName)) {
            entity.setCity_id(7);
            entity.setCitycode("JINZHOU");
        } else if ("营口".equals(cityName)) {
            entity.setCity_id(8);
            entity.setCitycode("YINGKOU");
        } else if ("阜新".equals(cityName)) {
            entity.setCity_id(9);
            entity.setCitycode("FUXIN");
        } else if ("辽阳".equals(cityName)) {
            entity.setCity_id(10);
            entity.setCitycode("LIAOYANG");
        } else if ("盘锦".equals(cityName)) {
            entity.setCity_id(11);
            entity.setCitycode("PANJIN");
        } else if ("铁岭".equals(cityName)) {
            entity.setCity_id(12);
            entity.setCitycode("TIELING");
        } else if ("朝阳".equals(cityName)) {
            entity.setCity_id(13);
            entity.setCitycode("CHAOYANG");
        } else if ("葫芦岛".equals(cityName)) {
            entity.setCity_id(14);
            entity.setCitycode("HULUDAO");
        } else {
            entity.setCity_id(999);
        }
    }
}
