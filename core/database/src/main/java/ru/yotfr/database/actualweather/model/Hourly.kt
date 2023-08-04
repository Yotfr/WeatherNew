package ru.yotfr.database.actualweather.model

data class Hourly(
    val apparent_temperature: List<Double>?,
    val is_day: List<Int>?,
    val precipitation: List<Double>?,
    val precipitation_probability: List<Int>?,
    val pressure_msl: List<Double>?,
    val rain: List<Double>?,
    val showers: List<Double>?,
    val snowfall: List<Double>?,
    val temperature_2m: List<Double>?,
    val time_hourly: List<String>?,
    val uv_index: List<Double>?,
    val visibility: List<Double>?,
    val weather_code: List<Int>?
)