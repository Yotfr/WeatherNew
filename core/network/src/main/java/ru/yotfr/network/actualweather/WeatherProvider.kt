package ru.yotfr.network.actualweather

import ru.yotfr.network.actualweather.model.ActualWeatherDTO
import ru.yotfr.network.calladapter.NetworkResponse
import javax.inject.Inject

class WeatherProvider @Inject internal constructor(
    private val actualWeatherService: ActualWeatherService
) {
    suspend fun getWeather(
        latitude: Float,
        longitude: Float,
        hourlyParameters: HourlyParameters?,
        dailyParameters: DailyParameters?,
        forecastDays: Int,
        timeZone: String
    ): NetworkResponse<ActualWeatherDTO> = actualWeatherService.getWeather(
        latitude = latitude,
        longitude = longitude,
        hourly = hourlyParameters?.query,
        daily = dailyParameters?.query,
        timezone = timeZone,
        forecastDays = forecastDays
    )
}


/**
 * @param[temperature] Air temperature at 2 meters above ground
 * @param[apparentTemperature] Apparent temperature is the perceived feels-like temperature combining wind chill factor, relative humidity and solar radiation
 * @param[precipitationProbability] Probability of precipitation with more than 0.1 mm of the preceding hour.
 * @param[rain] Rain from large scale weather systems of the preceding hour in millimeter
 * @param[weatherCode] Weather condition as a numeric code.
 * @param[pressure] Atmospheric air pressure reduced to mean sea level (msl)
 * @param[visibility] Viewing distance in meters. Influenced by low clouds, humidity and aerosols.
 * @param[uvIndex]  UV Index
 * @param[isDay] Is the current time step has daylight
 */
class HourlyParameters(
    temperature: Boolean = true,
    apparentTemperature: Boolean,
    precipitationProbability: Boolean,
    rain: Boolean,
    weatherCode: Boolean = true,
    pressure: Boolean,
    visibility: Boolean,
    uvIndex: Boolean,
    isDay: Boolean = true
) {
    private val temperatureString = if (temperature) "temperature_2m" else null
    private val apparentTemperatureString =
        if (apparentTemperature) "apparent_temperature" else null
    private val precipitationProbabilityString =
        if (precipitationProbability) "precipitation_probability" else null
    private val rainString = if (rain) "rain" else null
    private val weatherCodeString = if (weatherCode) "weathercode" else null
    private val pressureString = if (pressure) "pressure_msl" else null
    private val visibilityString = if (visibility) "visibility" else null
    private val uvIndexString = if (uvIndex) "uv_index" else null
    private val isDayString = if (isDay) "is_day" else null

    private val list = listOf(
        temperatureString,
        apparentTemperatureString,
        precipitationProbabilityString,
        rainString,
        weatherCodeString,
        pressureString,
        visibilityString,
        uvIndexString,
        isDayString
    )

    val query get() = list.filterNotNull().joinToString(separator = ",")
}

/**
 * @param[weatherCode] Weather condition as a numeric code.
 * @param[maxTemperature] Maximum daily air temperature at 2 meters above ground
 * @param[minTemperature] Minimum daily air temperature at 2 meters above ground
 */
class DailyParameters(
    maxTemperature: Boolean = true,
    minTemperature: Boolean = true,
    weatherCode: Boolean = true
) {
    private val maxTemperatureString = if (maxTemperature) "temperature_2m_max" else null
    private val minTemperatureString = if (minTemperature) "temperature_2m_min" else null
    private val weatherCodeString = if (weatherCode) "weathercode" else null

    private val list = listOf(
        maxTemperatureString,
        minTemperatureString,
        weatherCodeString
    )

    val query get() = list.filterNotNull().joinToString(separator = ",")
}