package ru.yotfr.network.weather.model

import com.google.gson.annotations.SerializedName

data class DailySysDTO(
    @SerializedName("pod")
    val partOfTheDay: String
)