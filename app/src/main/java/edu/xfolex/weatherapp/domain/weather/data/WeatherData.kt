package edu.xfolex.weatherapp.domain.weather.data

import edu.xfolex.weatherapp.domain.weather.type.WeatherType
import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
