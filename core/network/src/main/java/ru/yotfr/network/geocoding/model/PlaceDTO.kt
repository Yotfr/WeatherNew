package ru.yotfr.network.geocoding.model

import com.google.gson.annotations.SerializedName

data class PlaceDTO(
    val name: String,
    @SerializedName("local_names")
    val localNames: Map<String, String>?,
    val lat: Float,
    val lon: Float,
    val country: String,
    val state: String?
)