package ru.yotfr.network.weather.model

import com.google.gson.annotations.SerializedName

data class WindDTO(
    @SerializedName("deg")
    val direction: Int,
    val gust: Double,
    val speed: Double
)