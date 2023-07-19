package ru.yotfr.network.actualweather.model

data class DailyDTO(
    val temperature_2m_max: List<Double>?,
    val temperature_2m_min: List<Double>?,
    val time: List<String>?,
    val weathercode: List<Int>?
)