package edu.xfolex.weatherapp.data.repository

import edu.xfolex.weatherapp.data.mappers.mapToWeatherInfo
import edu.xfolex.weatherapp.data.remote.api.WeatherApi
import edu.xfolex.weatherapp.domain.repository.WeatherRepository
import edu.xfolex.weatherapp.domain.util.Resource
import edu.xfolex.weatherapp.domain.weather.info.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    latitude= latitude,
                    longitude = longitude
                ).mapToWeatherInfo()
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            Resource.Error(ex.message ?: "An unknown error occurred.")
        }
    }
}