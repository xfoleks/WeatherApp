package edu.xfolex.weatherapp.presentation.screen_state

import edu.xfolex.weatherapp.domain.weather.info.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
