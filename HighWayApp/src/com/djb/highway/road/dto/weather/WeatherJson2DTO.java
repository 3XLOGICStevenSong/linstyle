package com.djb.highway.road.dto.weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WeatherJson2DTO {
    public static <T> WeatherDTO getWeatherJsonObject(TypeToken<T> typeToken, String jsonStr) {
        return getJsonObjectByUrl(new TypeToken<WeatherDTO>() {
        }, jsonStr);
    }

    public static <T> T getJsonObjectByUrl(TypeToken<T> typeToken, String jsonStr) {
        T DTO = null;

        try {
            Gson gson = new Gson();
            DTO = gson.fromJson(jsonStr, typeToken.getType());
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return (T) DTO;
    }
}
