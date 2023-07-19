package ru.yotfr.network.actualweather.model

data class ActualWeatherDTO(
    val dailyDTO: DailyDTO?,
    val hourlyDTO: HourlyDTO?
)