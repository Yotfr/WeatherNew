package ru.yotfr.shared.model

data class PlaceModel(
    val id: Long,
    val name: String,
    val localNames: Map<String, String>?,
    val lat: Float,
    val lon: Float,
    val country: String,
    val state: String?,
    val isHome: Boolean,
    val isLocation: Boolean
)