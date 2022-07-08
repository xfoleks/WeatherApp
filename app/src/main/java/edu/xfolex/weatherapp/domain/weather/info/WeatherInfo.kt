package edu.xfolex.weatherapp.domain.weather.info

import edu.xfolex.weatherapp.domain.weather.data.WeatherData

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
