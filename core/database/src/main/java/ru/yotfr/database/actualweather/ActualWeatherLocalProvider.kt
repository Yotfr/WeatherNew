package ru.yotfr.database.actualweather

import ru.yotfr.database.actualweather.dao.ActualWeatherDao
import ru.yotfr.database.actualweather.model.ActualWeatherEntity
import javax.inject.Inject

class ActualWeatherLocalProvider @Inject constructor(
    private val actualWeatherDao: ActualWeatherDao
) {
    suspend fun updateWeather(actualWeatherEntity: ActualWeatherEntity) =
        actualWeatherDao.upsertWeather(
            actualWeatherEntity
        )

    suspend fun deleteWeather(actualWeatherEntity: ActualWeatherEntity) =
        actualWeatherDao.deleteWeather(actualWeatherEntity)

    suspend fun getWeather(placeId: Long): ActualWeatherEntity =
        actualWeatherDao.getWeather(placeId)
}