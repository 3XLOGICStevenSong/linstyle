package com.djb.highway.road.dto.weather;

import java.util.List;

public class WeatherResult {
    private String currentCity;
    private List<WeatherData> weather_data;

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public List<WeatherData> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<WeatherData> weather_data) {
        this.weather_data = weather_data;
    }

}
