package ru.yotfr.database.actualweather.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "today_actual_weather")
data class TodayActualWeatherEntity(
    @PrimaryKey val placeId: Long,
    @Embedded val hourlyDTO: Hourly
)