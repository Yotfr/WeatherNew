package ru.yotfr.weather.repository

import ru.yotfr.common.DataState
import ru.yotfr.common.ExceptionCause
import ru.yotfr.common.log.log
import ru.yotfr.database.weather.WeatherLocalProvider
import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.network.weather.WeatherRemoteProvider
import ru.yotfr.weather.mapper.mapToCurrentWeatherEntity
import ru.yotfr.weather.mapper.mapToCurrentWeatherModel
import ru.yotfr.weather.mapper.mapToThreeHourEntity
import ru.yotfr.weather.mapper.mapToThreeHourWeatherModel
import ru.yotfr.shared.model.FullWeatherModel
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteProvider: WeatherRemoteProvider,
    private val weatherLocalProvider: WeatherLocalProvider
) : WeatherRepository {
    override suspend fun getWeather(
        placeId: Long,
        latitude: Float,
        longitude: Float
    ): DataState<FullWeatherModel> {
        val currentWeatherNetworkResponse = weatherRemoteProvider.getCurrentWeather(
            latitude = latitude,
            longitude = longitude
        )
        log.d("current weather response $currentWeatherNetworkResponse")
        when(currentWeatherNetworkResponse) {
            is NetworkResponse.Error -> { return DataState.Exception(ExceptionCause.Unknown) }
            is NetworkResponse.Exception -> { return DataState.Exception(ExceptionCause.Unknown) }
            is NetworkResponse.Success -> {
                log.d("current weather response ${currentWeatherNetworkResponse.data}")
                val threeHourWeatherNetworkResponse = weatherRemoteProvider.getThreeHourWeather(
                    latitude = latitude,
                    longitude = longitude
                )
                log.d("threeeHour weather response $threeHourWeatherNetworkResponse")
                when(threeHourWeatherNetworkResponse) {
                    is NetworkResponse.Error -> { return DataState.Exception(ExceptionCause.Unknown) }
                    is NetworkResponse.Exception -> { return DataState.Exception(ExceptionCause.Unknown) }
                    is NetworkResponse.Success -> {
                        log.d("threeeHour weather response ${threeHourWeatherNetworkResponse.data}")
                        weatherLocalProvider.updateWeatherCache(
                            placeId = placeId,
                            currentWeatherEntity = currentWeatherNetworkResponse.data.mapToCurrentWeatherEntity(
                                placeId = placeId
                            ),
                            threeHourWeatherList = threeHourWeatherNetworkResponse.data.mapToThreeHourEntity(
                                placeId = placeId
                            )
                        )
                        val currentWeatherCache = weatherLocalProvider.getCurrentWeather(
                            placeId = placeId
                        )
                        val threeHourWeatherCache = weatherLocalProvider.getThreeHourWeather(
                            placeId = placeId
                        )
                        return DataState.Success(
                            data = FullWeatherModel(
                                currentWeather = currentWeatherCache.mapToCurrentWeatherModel(),
                                hourlyForecast = threeHourWeatherCache.map { it.mapToThreeHourWeatherModel() }
                            )
                        )
                    }
                }
            }
        }
    }
}