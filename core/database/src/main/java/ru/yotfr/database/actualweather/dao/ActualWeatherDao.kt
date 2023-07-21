package ru.yotfr.database.actualweather.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import ru.yotfr.database.actualweather.model.ActualWeatherEntity

@Dao
interface ActualWeatherDao {

    @Upsert(entity = ActualWeatherEntity::class)
    suspend fun upsertWeather(actualWeatherEntity: ActualWeatherEntity)

    @Delete(entity = ActualWeatherEntity::class)
    suspend fun deleteWeather(actualWeatherEntity: ActualWeatherEntity)

    @Query("SELECT * FROM actual_weather WHERE placeId = :placeId")
    suspend fun getWeather(placeId: Long) : ActualWeatherEntity
}