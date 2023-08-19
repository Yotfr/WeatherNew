package ru.yotfr.actualweather.mapper

import ru.yotfr.database.actualweather.model.TodayActualWeatherEntity
import ru.yotfr.database.actualweather.model.WeeklyActualWeatherEntity
import ru.yotfr.shared.model.DailyModel
import ru.yotfr.shared.model.HourlyModel
import ru.yotfr.shared.model.TodayWeatherModel
import ru.yotfr.shared.model.WeeklyWeatherModel
import ru.yotfr.shared.model.toWeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.roundToInt

/**
 * Returns null if cannot be mapped
 */
fun TodayActualWeatherEntity.mapToTodayWeatherModel(): TodayWeatherModel? {
    hourlyDTO.apply {
        val hourlyForecast = time_hourly?.mapIndexed { index, time ->
            val hourlyModel = HourlyModel(
                time = LocalDateTime.parse(time),
                temperature = temperature_2m?.get(index)?.roundToInt() ?: return null,
                apparentTemperature = apparent_temperature?.get(index)?.roundToInt() ?: return null,
                isDay = is_day?.get(index)?.toBoolean() ?: return null,
                precipitation = precipitation?.get(index)?.toPrecipitation() ?: false,
                rain = rain?.get(index)?.toPrecipitation() ?: return null,
                showers = showers?.get(index)?.toPrecipitation() ?: false,
                snowfall = snowfall?.get(index)?.toPrecipitation() ?:false,
                uvIndex = uv_index?.get(index) ?: return null,
                visibility = visibility?.get(index) ?: return null,
                weatherType = weather_code?.get(index)?.toWeatherType() ?: return null
            )
            hourlyModel
        } ?: return null
        return TodayWeatherModel(hourlyForecast = hourlyForecast)
    }
}

/**
 * Returns null if cannot be mapped
 */
fun WeeklyActualWeatherEntity.mapToWeeklyWeatherModel(): WeeklyWeatherModel? {
    dailyDTO.apply {
        val dailyForecast =  time?.mapIndexed { dailyIndex, dailyTime ->
            DailyModel(
                time = LocalDate.parse(dailyTime),
                maxTemperature = temperature_2m_max?.get(dailyIndex)?.roundToInt() ?: return null,
                minTemperature = temperature_2m_min?.get(dailyIndex)?.roundToInt() ?: return null,
                weatherType = weathercode?.get(dailyIndex)?.toWeatherType() ?: return null,
                hourlyForecast = hourlyDTO.time_hourly?.mapIndexed { hourlyIndex, hourlyTime ->
                    HourlyModel(
                        time = LocalDateTime.parse(hourlyTime),
                        temperature = hourlyDTO.temperature_2m?.get(hourlyIndex)?.roundToInt() ?: return null,
                        apparentTemperature = hourlyDTO.apparent_temperature?.get(hourlyIndex)?.roundToInt() ?: return null,
                        isDay = hourlyDTO.is_day?.get(hourlyIndex)?.toBoolean() ?: return null,
                        precipitation = hourlyDTO.precipitation?.get(hourlyIndex)?.toPrecipitation() ?: false,
                        rain = hourlyDTO.rain?.get(hourlyIndex)?.toPrecipitation() ?: false,
                        showers = hourlyDTO.showers?.get(hourlyIndex)?.toPrecipitation() ?: false,
                        snowfall = hourlyDTO.snowfall?.get(hourlyIndex)?.toPrecipitation() ?: false,
                        uvIndex = hourlyDTO.uv_index?.get(hourlyIndex) ?: return null,
                        visibility = hourlyDTO.visibility?.get(hourlyIndex) ?: return null,
                        weatherType = hourlyDTO.weather_code?.get(hourlyIndex)?.toWeatherType() ?: return null
                    )
                }?.filter { it.time.dayOfMonth == LocalDate.parse(dailyTime).dayOfMonth } ?: return null
            )
        } ?: return null
        return WeeklyWeatherModel(dailyForecast = dailyForecast)
    }
}

fun Int.toBoolean(): Boolean = this == 1
fun Double.toPrecipitation(): Boolean = this > 0.0