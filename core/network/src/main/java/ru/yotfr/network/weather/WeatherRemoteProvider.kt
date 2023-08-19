package ru.yotfr.network.weather

import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.network.weather.model.CurrentWeatherDTO
import ru.yotfr.network.weather.model.DailyWeatherDTO
import javax.inject.Inject

class WeatherRemoteProvider @Inject internal constructor(
    private val currentWeatherService: CurrentWeatherService,
    private val dailyWeatherService: DailyWeatherService
) {
    suspend fun getCurrentWeather(
        latitude: Float,
        longitude: Float
    ): NetworkResponse<CurrentWeatherDTO> = currentWeatherService.getWeather(
        latitude = latitude,
        longitude = longitude
    )

    suspend fun getThreeHourWeather(
        latitude: Float,
        longitude: Float
    ): NetworkResponse<DailyWeatherDTO> = dailyWeatherService.getWeather(
        latitude = latitude,
        longitude = longitude
    )

}