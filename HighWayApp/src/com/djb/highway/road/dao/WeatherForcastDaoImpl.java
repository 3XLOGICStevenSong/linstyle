package com.djb.highway.road.dao;

import org.springframework.stereotype.Repository;

import com.djb.highway.framework.dao.BaseDAOImpl;
import com.djb.highway.road.entity.WeatherForcastEntity;

@Repository("weatherForcastDao")
public class WeatherForcastDaoImpl extends BaseDAOImpl<WeatherForcastEntity> implements
		IWeatherForcastDao {
}
