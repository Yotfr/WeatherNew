package ru.yotfr.shared.model

import java.time.LocalDateTime

/**
 * @param[visibility] в процентах
 * @param[cloudCover] в процентах
 */
data class CurrentWeatherModel(
    val weather: WeatherTypeModel,
    val visibility: Int,
    val wind: WindModel,
    val cloudCover: Int,
    val temperature: Double,
    val apparentTemperature: Double,
    val humidity: Int,
    val pressure: Int,
    val sunriseTime: LocalDateTime,
    val sunsetTime: LocalDateTime,
    val timezone: Int
)