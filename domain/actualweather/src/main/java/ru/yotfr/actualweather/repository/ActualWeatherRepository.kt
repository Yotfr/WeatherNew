package ru.yotfr.actualweather.repository

import ru.yotfr.common.DataState
import ru.yotfr.shared.model.TodayWeatherModel
import ru.yotfr.shared.model.WeeklyWeatherModel

interface ActualWeatherRepository {

    suspend fun getTodayForecast(
        latitude: Float,
        longitude: Float,
        placeId: Long
    ): DataState<TodayWeatherModel>

    suspend fun getWeeklyForecast(
        latitude: Float,
        longitude: Float,
        placeId: Long
    ): DataState<WeeklyWeatherModel>
}