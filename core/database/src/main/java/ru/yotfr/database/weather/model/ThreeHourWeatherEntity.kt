package ru.yotfr.database.weather.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "three_hour_weather")
data class ThreeHourWeatherEntity(
    val dt: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cloudCover: Int,
    val temperature: Double,
    val apparentTemperature: Double,
    val humidity: Int,
    val pressure: Int,
    val precipitationProbability: Double,
    val partOfTheDay: String,
    @Embedded val weather: WeatherEntity,
    @Embedded val wind: WindEntity,
    val placeId: Long
)