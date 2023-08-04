package ru.yotfr.network.actualweather.model

import com.google.gson.annotations.SerializedName

data class ActualWeatherDTO(
    @SerializedName("daily")
    val dailyDTO: DailyDTO?,
    @SerializedName("hourly")
    val hourlyDTO: HourlyDTO?
)