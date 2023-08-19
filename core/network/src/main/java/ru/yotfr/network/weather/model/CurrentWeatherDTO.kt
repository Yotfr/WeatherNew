package ru.yotfr.network.weather.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDTO(
    @SerializedName("weather")
    val weatherDTO: List<WeatherDTO>,
    val visibility: Int,
    val wind: WindDTO,
    val clouds: CloudsDTO,
    @SerializedName("main")
    val mainDTO: MainDTO,
    @SerializedName("sys")
    val sysDTO: SysDTO,
    val timezone: Int
)