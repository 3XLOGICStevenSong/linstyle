package com.djb.highway.road.dao;

import com.djb.highway.framework.dao.BaseDAO;
import com.djb.highway.road.entity.WeatherForcastEntity;

public interface IWeatherForcastDao extends BaseDAO<WeatherForcastEntity> {
    public static final String GETCURDATEOBJECT = "getCurDateObject";
}
