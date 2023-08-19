package ru.yotfr.database.weather.model

data class WindEntity(
    val direction: Int,
    val gust: Double,
    val speed: Double
)