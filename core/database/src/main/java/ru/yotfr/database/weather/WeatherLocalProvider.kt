package ru.yotfr.database.weather

import ru.yotfr.database.weather.dao.WeatherDao
import ru.yotfr.database.weather.model.CurrentWeatherEntity
import ru.yotfr.database.weather.model.ThreeHourWeatherEntity
import javax.inject.Inject

class WeatherLocalProvider @Inject constructor(
    private val weatherDao: WeatherDao
) {
    suspend fun updateWeatherCache(
        placeId: Long,
        currentWeatherEntity: CurrentWeatherEntity,
        threeHourWeatherList: List<ThreeHourWeatherEntity>
    ) {
        weatherDao.updateWeatherCache(
            placeId = placeId,
            currentWeatherEntity = currentWeatherEntity,
            threeHourWeatherList = threeHourWeatherList
        )
    }

    suspend fun getCurrentWeather(placeId: Long): CurrentWeatherEntity =
        weatherDao.getCurrentWeather(placeId)

    suspend fun getThreeHourWeather(placeId: Long): List<ThreeHourWeatherEntity> =
        weatherDao.getThreeHoursWeather(placeId)
}
