package ru.yotfr.database.actualweather.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import ru.yotfr.database.actualweather.model.TodayActualWeatherEntity
import ru.yotfr.database.actualweather.model.WeeklyActualWeatherEntity

@Dao
interface ActualWeatherDao {

    @Upsert(entity = TodayActualWeatherEntity::class)
    suspend fun upsertTodayWeather(actualWeatherEntity: TodayActualWeatherEntity)

    @Delete(entity = TodayActualWeatherEntity::class)
    suspend fun deleteTodayWeather(actualWeatherEntity: TodayActualWeatherEntity)

    @Query("SELECT * FROM today_actual_weather WHERE placeId = :placeId")
    suspend fun getTodayWeather(placeId: Long) : TodayActualWeatherEntity

    @Upsert(entity = WeeklyActualWeatherEntity::class)
    suspend fun upsertWeeklyWeather(actualWeatherEntity: WeeklyActualWeatherEntity)

    @Delete(entity = WeeklyActualWeatherEntity::class)
    suspend fun deleteWeeklyWeather(actualWeatherEntity: WeeklyActualWeatherEntity)

    @Query("SELECT * FROM weekly_actual_weather WHERE placeId = :placeId")
    suspend fun getWeeklyWeather(placeId: Long) : WeeklyActualWeatherEntity
}