package ru.yotfr.database.weather.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.yotfr.database.weather.model.CurrentWeatherEntity
import ru.yotfr.database.weather.model.ThreeHourWeatherEntity

@Dao
interface WeatherDao {

    @Insert(entity = CurrentWeatherEntity::class)
    suspend fun insertCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)

    @Insert(entity = ThreeHourWeatherEntity::class)
    suspend fun insertThreeHoursWeather(threeHourWeatherList: List<ThreeHourWeatherEntity>)

    @Query("SELECT * FROM current_weather WHERE placeId = :placeId")
    suspend fun getCurrentWeather(placeId: Long): CurrentWeatherEntity

    @Query("SELECT * FROM three_hour_weather WHERE placeId = :placeId")
    suspend fun getThreeHoursWeather(placeId: Long): List<ThreeHourWeatherEntity>

    @Query("DELETE FROM current_weather WHERE placeId = :placeId")
    suspend fun deleteAllFromCurrentWeather(placeId: Long)

    @Query("DELETE FROM three_hour_weather WHERE placeId = :placeId")
    suspend fun deleteAllThreeHoursWeather(placeId: Long)

    @Transaction
    suspend fun updateWeatherCache(placeId: Long, currentWeatherEntity: CurrentWeatherEntity, threeHourWeatherList: List<ThreeHourWeatherEntity>) {
        deleteAllFromCurrentWeather(placeId)
        deleteAllThreeHoursWeather(placeId)
        insertCurrentWeather(currentWeatherEntity)
        insertThreeHoursWeather(threeHourWeatherList)
    }
}