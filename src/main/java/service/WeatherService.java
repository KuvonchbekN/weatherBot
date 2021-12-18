package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.WeatherData;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeatherService {
    ObjectMapper objectMapper = new ObjectMapper();


    public Map<String, String> getCurrentWeather(String cityName){
        Map<String, String> currentWeatherInfo = new LinkedHashMap<>();
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=cafc0c79a1175305cf0ee28019c4b726");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            WeatherData data = objectMapper.readValue(inputStream, new TypeReference<>() {});
            currentWeatherInfo.put("COUNTRY", data.getCityName());
            currentWeatherInfo.put("\uD83C\uDF21 temperature", String.valueOf(Math.round (data.getMainTemp().getTemperature()-273)) + " Celcius");
            currentWeatherInfo.put("\uD83D\uDCA8 windSpeed", String.valueOf(data.getWind().getSpeed()));
            currentWeatherInfo.put("\uD83D\uDCA7 humidity", String.valueOf(data.getMainTemp().getHumidity()));
            currentWeatherInfo.put(" pressure", String.valueOf(data.getMainTemp().getPressure()));
            currentWeatherInfo.put("feels like", String.valueOf(data.getMainTemp().getFeelsLike()));
            return currentWeatherInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
