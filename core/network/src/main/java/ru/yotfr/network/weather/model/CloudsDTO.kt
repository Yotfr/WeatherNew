package ru.yotfr.network.weather.model

import com.google.gson.annotations.SerializedName

data class CloudsDTO(
    @SerializedName("all")
    val percent: Int
)