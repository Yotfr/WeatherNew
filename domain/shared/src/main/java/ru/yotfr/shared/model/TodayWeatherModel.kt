package ru.yotfr.shared.model

data class TodayWeatherModel(
    val hourlyForecast: List<HourlyModel>
)