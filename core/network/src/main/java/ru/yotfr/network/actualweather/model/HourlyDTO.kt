package ru.yotfr.network.actualweather.model
data class HourlyDTO(
    val apparent_temperature: List<Double>?,
    val is_day: List<Int>?,
    val precipitation: List<Double>?,
    val precipitation_probability: List<Int>?,
    val pressure_msl: List<Double>?,
    val rain: List<Double>?,
    val showers: List<Double>?,
    val snowfall: List<Double>?,
    val temperature_2m: List<Double>?,
    val time: List<String>?,
    val uv_index: List<Double>?,
    val visibility: List<Double>?,
    val weathercode: List<Int>?
)