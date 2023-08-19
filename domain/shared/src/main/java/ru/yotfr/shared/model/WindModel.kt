package ru.yotfr.shared.model

data class WindModel(
    val direction: WindDirection,
    val gustSpeed: Double,
    val speed: Double
)