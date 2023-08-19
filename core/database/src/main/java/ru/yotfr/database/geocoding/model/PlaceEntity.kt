package ru.yotfr.database.geocoding.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place")
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val localNames: Map<String, String>?,
    val lat: Float,
    val lon: Float,
    val country: String,
    val state: String?,
    val isHome: Boolean,
    val isLocation: Boolean
)