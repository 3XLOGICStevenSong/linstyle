package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.WeatherForcastEntity;

public interface IWeatherForcastService {
	public void addWeatherForcast(WeatherForcastEntity weatherForcast);

	public void deleteWeatherForcast(WeatherForcastEntity weatherForcast);

	public void deleteWeatherForcastBatch(List<WeatherForcastEntity> list);

	public void updateWeatherForcast(WeatherForcastEntity weatherForcast);

	public WeatherForcastEntity getObject(WeatherForcastEntity weatherForcast);

	public List<WeatherForcastEntity> getWeatherForcastList();

	public List<WeatherForcastEntity> getWeatherForcastList(
			WeatherForcastEntity weatherForcast);

	public WeatherForcastEntity getCurObject(WeatherForcastEntity weatherForcast);

}
