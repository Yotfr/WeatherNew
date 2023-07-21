package ru.yotfr.database.places.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey val id: Int,
    val admin1: String,
    val country: String,
    val country_code: String,
    val elevation: Double,
    val feature_code: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val timezone: String
)