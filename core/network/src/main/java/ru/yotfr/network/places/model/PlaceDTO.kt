package ru.yotfr.network.places.model

data class PlaceDTO(
    val admin1: String,
    val country: String,
    val country_code: String,
    val elevation: Double,
    val feature_code: String,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val timezone: String
)