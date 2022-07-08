package edu.xfolex.weatherapp.domain.repository

import edu.xfolex.weatherapp.domain.util.Resource
import edu.xfolex.weatherapp.domain.weather.info.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherInfo>
}