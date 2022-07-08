package edu.xfolex.weatherapp.data.mappers

import edu.xfolex.weatherapp.data.remote.dto.WeatherDataDto
import edu.xfolex.weatherapp.data.remote.dto.WeatherDto
import edu.xfolex.weatherapp.domain.weather.data.WeatherData
import edu.xfolex.weatherapp.domain.weather.info.WeatherInfo
import edu.xfolex.weatherapp.domain.weather.type.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)


fun WeatherDataDto.mapToWeatherData(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy { indexedWeatherData ->
        indexedWeatherData.index / 24
    }.mapValues { entryOfData ->
        entryOfData.value.map { value ->
            value.data
        }
    }
}

fun WeatherDto.mapToWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.mapToWeatherData()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}