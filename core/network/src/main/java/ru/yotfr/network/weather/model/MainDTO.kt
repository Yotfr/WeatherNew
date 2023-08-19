package ru.yotfr.network.weather.model

import com.google.gson.annotations.SerializedName

data class MainDTO(
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double
)