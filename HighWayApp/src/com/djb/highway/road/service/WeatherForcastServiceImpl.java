package com.djb.highway.road.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.djb.highway.framework.exception.dao.DataNotFoundException;
import com.djb.highway.framework.exception.dao.KeyExistException;
import com.djb.highway.framework.service.BaseService;
import com.djb.highway.road.dao.IWeatherForcastDao;
import com.djb.highway.road.entity.WeatherForcastEntity;

@Service("iWeatherForcastService")
public class WeatherForcastServiceImpl extends BaseService implements
		IWeatherForcastService {

	@Autowired
	@Qualifier("weatherForcastDao")
	private IWeatherForcastDao weatherForcastDao;

	@Override
	public void addWeatherForcast(WeatherForcastEntity WeatherForcast) {
		try {
			weatherForcastDao.insert(WeatherForcast);
		} catch (KeyExistException e) {
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteWeatherForcast(WeatherForcastEntity WeatherForcast) {
		try {
			weatherForcastDao.delete(WeatherForcast);
		} catch (DataNotFoundException e) {
			// e.printStackTrace();
		}
	}

	@Override
	public void deleteWeatherForcastBatch(List<WeatherForcastEntity> list) {
		try {
			weatherForcastDao.deleteBatch(list);
		} catch (DataNotFoundException e) {
			// e.printStackTrace();
		}
	}

	@Override
	public void updateWeatherForcast(WeatherForcastEntity WeatherForcast) {
		try {
			weatherForcastDao.update(WeatherForcast);
		} catch (DataNotFoundException e) {
			// e.printStackTrace();
		}
	}

	@Override
	public WeatherForcastEntity getObject(WeatherForcastEntity WeatherForcast) {
		return weatherForcastDao.getObject(WeatherForcast);
	}

	@Override
	public List<WeatherForcastEntity> getWeatherForcastList() {
		return (List<WeatherForcastEntity>) weatherForcastDao.findList();
	}

	@Override
	public List<WeatherForcastEntity> getWeatherForcastList(
			WeatherForcastEntity WeatherForcast) {
		List<WeatherForcastEntity> list = (List<WeatherForcastEntity>) weatherForcastDao
				.findListByCondition(WeatherForcast);
		return list;
	}

	@Override
	public WeatherForcastEntity getCurObject(WeatherForcastEntity weatherForcast) {
		return weatherForcastDao.getObject(IWeatherForcastDao.GETCURDATEOBJECT,
				weatherForcast);
	}

}
