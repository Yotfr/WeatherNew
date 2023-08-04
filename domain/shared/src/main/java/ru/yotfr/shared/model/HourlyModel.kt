package ru.yotfr.shared.model

import java.time.LocalDateTime

data class HourlyModel(
    val time: LocalDateTime,
    val temperature: Int,
    val apparentTemperature: Int,
    val isDay: Boolean,
    val precipitation: Boolean,
    val rain: Boolean,
    val showers: Boolean,
    val snowfall: Boolean,
    val uvIndex: Double,
    val visibility: Double,
    val weatherType: WeatherType
)