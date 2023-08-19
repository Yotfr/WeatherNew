package ru.yotfr.weather.repository

import ru.yotfr.common.DataState
import ru.yotfr.shared.model.FullWeatherModel

interface WeatherRepository {
    suspend fun getWeather(
        placeId: Long,
        latitude: Float,
        longitude: Float
    ): DataState<FullWeatherModel>
}