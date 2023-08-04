package ru.yotfr.shared.model

import java.time.LocalDateTime

data class DailyModel(
    val time: LocalDateTime,
    val maxTemperature: Int,
    val minTemperature: Int,
    val weatherType: WeatherType,
    val hourlyForecast: List<HourlyModel>
)