package ru.yotfr.database.weather.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val placeId: Long,
    @Embedded val weatherDTO: WeatherEntity,
    val visibility: Int,
    @Embedded val wind: WindEntity,
    val cloudCover: Int,
    val temperature: Double,
    val apparentTemperature: Double,
    val humidity: Int,
    val pressure: Int,
    val sunriseTime: Int,
    val sunsetTime: Int,
    val timezone: Int
)
