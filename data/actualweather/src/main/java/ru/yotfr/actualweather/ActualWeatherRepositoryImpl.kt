package ru.yotfr.actualweather

import android.util.Log
import ru.yotfr.actualweather.mapper.mapToTodayWeatherModel
import ru.yotfr.actualweather.mapper.mapToWeeklyWeatherModel
import ru.yotfr.actualweather.repository.ActualWeatherRepository
import ru.yotfr.database.actualweather.ActualWeatherLocalProvider
import ru.yotfr.database.actualweather.model.Daily
import ru.yotfr.database.actualweather.model.Hourly
import ru.yotfr.database.actualweather.model.TodayActualWeatherEntity
import ru.yotfr.database.actualweather.model.WeeklyActualWeatherEntity
import ru.yotfr.network.actualweather.ActualWeatherRemoteProvider
import ru.yotfr.network.actualweather.DailyParameters
import ru.yotfr.network.actualweather.HourlyParameters
import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.common.model.DataState
import ru.yotfr.common.model.ExceptionCause
import ru.yotfr.shared.model.TodayWeatherModel
import ru.yotfr.shared.model.WeeklyWeatherModel
import java.util.TimeZone
import javax.inject.Inject

class ActualWeatherRepositoryImpl @Inject constructor(
    private val actualWeatherLocalProvider: ActualWeatherLocalProvider,
    private val actualWeaRemoteProvider: ActualWeatherRemoteProvider
) : ActualWeatherRepository {
    override suspend fun getTodayForecast(
        latitude: Float,
        longitude: Float,
        placeId: Long
    ): DataState<TodayWeatherModel> {
        val networkResponse = actualWeaRemoteProvider.getWeather(
            latitude = latitude,
            longitude = longitude,
            hourlyParameters = HourlyParameters(
                temperature = true,
                apparentTemperature = true,
                precipitationProbability = true,
                rain = true,
                weatherCode = true,
                pressure = true,
                visibility = true,
                uvIndex = true,
                isDay = true
            ),
            dailyParameters = null,
            forecastDays = 2,
            timeZone = getCurrentTimeZoneId()
        )
        Log.d("TEST","NetworkResponse = $networkResponse")
        return when(networkResponse) {
            is NetworkResponse.Success -> {
                // Updates cache or writes new
                Log.d("TEST","Success response = ${networkResponse.data}")
                actualWeatherLocalProvider.updateTodayWeather(
                    TodayActualWeatherEntity(
                        placeId = placeId,
                        hourlyDTO = Hourly(
                            apparent_temperature = networkResponse.data.hourlyDTO?.apparent_temperature,
                            is_day = networkResponse.data.hourlyDTO?.is_day,
                            precipitation = networkResponse.data.hourlyDTO?.precipitation,
                            precipitation_probability = networkResponse.data.hourlyDTO?.precipitation_probability,
                            pressure_msl = networkResponse.data.hourlyDTO?.pressure_msl,
                            rain = networkResponse.data.hourlyDTO?.rain,
                            showers = networkResponse.data.hourlyDTO?.showers,
                            snowfall = networkResponse.data.hourlyDTO?.snowfall,
                            temperature_2m = networkResponse.data.hourlyDTO?.temperature_2m,
                            time_hourly = networkResponse.data.hourlyDTO?.time,
                            uv_index = networkResponse.data.hourlyDTO?.uv_index,
                            visibility = networkResponse.data.hourlyDTO?.visibility,
                            weather_code = networkResponse.data.hourlyDTO?.weathercode
                        )
                    )
                )
                Log.d("TEST","Local cache ${actualWeatherLocalProvider.getTodayWeather(
                    placeId = placeId
                )}")
                val mappedLocalCache = actualWeatherLocalProvider.getTodayWeather(
                    placeId = placeId
                ).mapToTodayWeatherModel()
                Log.d("TEST","Mapped Local cache $mappedLocalCache placeId $placeId")
                if (mappedLocalCache != null) DataState.Success(data = mappedLocalCache)
                else DataState.Exception(ExceptionCause.Unknown)
            }
            is NetworkResponse.Exception -> { DataState.Exception(ExceptionCause.Unknown) }
            is NetworkResponse.Error -> { DataState.Exception(ExceptionCause.Unknown) }
        }
    }

    override suspend fun getWeeklyForecast(
        latitude: Float,
        longitude: Float,
        placeId: Long
    ): DataState<WeeklyWeatherModel> {
        val networkResponse = actualWeaRemoteProvider.getWeather(
            latitude = latitude,
            longitude = longitude,
            hourlyParameters = HourlyParameters(
                temperature = true,
                apparentTemperature = true,
                precipitationProbability = true,
                rain = true,
                weatherCode = true,
                pressure = true,
                visibility = true,
                uvIndex = true,
                isDay = true
            ),
            dailyParameters = DailyParameters(
                maxTemperature = true,
                minTemperature = true,
                weatherCode = true
            ),
            forecastDays = 16,
            timeZone = getCurrentTimeZoneId()
        )
        return when(networkResponse) {
            is NetworkResponse.Success -> {
                // Updates cache or writes new
                actualWeatherLocalProvider.updateWeeklyWeather(
                    WeeklyActualWeatherEntity(
                        placeId = placeId,
                        hourlyDTO = Hourly(
                            apparent_temperature = networkResponse.data.hourlyDTO?.apparent_temperature,
                            is_day = networkResponse.data.hourlyDTO?.is_day,
                            precipitation = networkResponse.data.hourlyDTO?.precipitation,
                            precipitation_probability = networkResponse.data.hourlyDTO?.precipitation_probability,
                            pressure_msl = networkResponse.data.hourlyDTO?.pressure_msl,
                            rain = networkResponse.data.hourlyDTO?.rain,
                            showers = networkResponse.data.hourlyDTO?.showers,
                            snowfall = networkResponse.data.hourlyDTO?.snowfall,
                            temperature_2m = networkResponse.data.hourlyDTO?.temperature_2m,
                            time_hourly = networkResponse.data.hourlyDTO?.time,
                            uv_index = networkResponse.data.hourlyDTO?.uv_index,
                            visibility = networkResponse.data.hourlyDTO?.visibility,
                            weather_code = networkResponse.data.hourlyDTO?.weathercode
                        ),
                        dailyDTO = Daily(
                            temperature_2m_max = networkResponse.data.dailyDTO?.temperature_2m_max,
                            temperature_2m_min = networkResponse.data.dailyDTO?.temperature_2m_min,
                            time = networkResponse.data.dailyDTO?.time,
                            weathercode = networkResponse.data.dailyDTO?.weathercode
                        )
                    )
                )
                val mappedLocalCache = actualWeatherLocalProvider.getWeeklyWeather(
                    placeId = placeId
                ).mapToWeeklyWeatherModel()
                if (mappedLocalCache != null) DataState.Success(data = mappedLocalCache)
                else DataState.Exception(ExceptionCause.Unknown)
            }
            is NetworkResponse.Exception -> { DataState.Exception(ExceptionCause.Unknown) }
            is NetworkResponse.Error -> { DataState.Exception(ExceptionCause.Unknown) }
        }
    }


    private fun getCurrentTimeZoneId(): String {
        return TimeZone.getDefault().id
    }
}