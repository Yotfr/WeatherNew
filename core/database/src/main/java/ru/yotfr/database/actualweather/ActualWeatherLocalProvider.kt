package ru.yotfr.database.actualweather

import android.util.Log
import ru.yotfr.database.actualweather.dao.ActualWeatherDao
import ru.yotfr.database.actualweather.model.TodayActualWeatherEntity
import ru.yotfr.database.actualweather.model.WeeklyActualWeatherEntity
import javax.inject.Inject

class ActualWeatherLocalProvider @Inject constructor(
    private val actualWeatherDao: ActualWeatherDao
) {
    suspend fun updateTodayWeather(actualWeatherEntity: TodayActualWeatherEntity) {

    }
        logger {
            actualWeatherDao.upsertTodayWeather(
                actualWeatherEntity
            )
        }


    suspend fun deleteTodayWeather(actualWeatherEntity: TodayActualWeatherEntity) =
        actualWeatherDao.deleteTodayWeather(actualWeatherEntity)

    suspend fun getTodayWeather(placeId: Long): TodayActualWeatherEntity =
        actualWeatherDao.getTodayWeather(placeId)

    suspend fun updateWeeklyWeather(actualWeatherEntity: WeeklyActualWeatherEntity) =
        actualWeatherDao.upsertWeeklyWeather(
            actualWeatherEntity
        )

    suspend fun deleteWeeklyWeather(actualWeatherEntity: WeeklyActualWeatherEntity) =
        actualWeatherDao.deleteWeeklyWeather(actualWeatherEntity)

    suspend fun getWeeklyWeather(placeId: Long): WeeklyActualWeatherEntity =
        actualWeatherDao.getWeeklyWeather(placeId)
}