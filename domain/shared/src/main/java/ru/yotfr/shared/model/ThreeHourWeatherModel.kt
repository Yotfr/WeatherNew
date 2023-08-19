package ru.yotfr.shared.model

import java.time.LocalDateTime
data class ThreeHourWeatherModel(
    val time: LocalDateTime,
    val cloudCover: Int,
    val temperature: Double,
    val apparentTemperature: Double,
    val humidity: Int,
    val pressure: Int,
    val precipitationProbability: Double,
    val partOfTheDay: PartOfTheDay,
    val weather: WeatherTypeModel,
    val wind: WindModel
)