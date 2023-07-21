package ru.yotfr.database.actualweather.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actual_weather")
data class ActualWeatherEntity(
    @PrimaryKey val placeId: Long,
    @Embedded val dailyDTO: Daily?,
    @Embedded val hourlyDTO: Hourly?
)