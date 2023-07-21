package ru.yotfr.database.actualweather.model

data class Daily(
    val temperature_2m_max: List<Double>?,
    val temperature_2m_min: List<Double>?,
    val time: List<String>?,
    val weathercode: List<Int>?
)