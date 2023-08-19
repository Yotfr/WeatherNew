package ru.yotfr.network.weather.model

import com.google.gson.annotations.SerializedName

data class ThreeHourDTO(
    val dt: Int,
    val clouds: CloudsDTO,
    @SerializedName("main")
    val mainDTO: MainDTO,
    @SerializedName("pop")
    val precipitationProbability: Double,
    @SerializedName("sys")
    val sysDTO: DailySysDTO,
    val visibility: Int,
    @SerializedName("weather")
    val weatherDTO: List<WeatherDTO>,
    val wind: WindDTO
)