package ru.yotfr.shared.model

data class FullWeatherModel(
    val currentWeather: CurrentWeatherModel,
    val hourlyForecast: List<ThreeHourWeatherModel>
)