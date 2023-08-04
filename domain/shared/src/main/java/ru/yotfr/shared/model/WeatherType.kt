package ru.yotfr.shared.model

enum class WeatherType {
    CLEAR_SKY,
    MAINLY_CLEAR,
    PARTLY_CLOUDY,
    OVERCAST,
    FOG,
    DRIZZLE,
    FREEZING_DRIZZLE,
    RAIN,
    FREEZING_RAIN,
    SNOWFALL,
    SNOW_GRAINS,
    RAIN_SHOWER,
    SNOW_SHOWER,
    THUNDERSTORM,
    THUNDERSTORM_WITH_HAIL,
    UNKNOWN
}

fun Int.toWeatherType(): WeatherType =
    when (this) {
        0 -> WeatherType.CLEAR_SKY
        1 -> WeatherType.MAINLY_CLEAR
        2 -> WeatherType.PARTLY_CLOUDY
        3 -> WeatherType.OVERCAST
        45, 48 -> WeatherType.FOG
        51, 53, 55 -> WeatherType.DRIZZLE
        56, 57 -> WeatherType.FREEZING_DRIZZLE
        61, 63, 65 -> WeatherType.RAIN
        66, 67 -> WeatherType.FREEZING_RAIN
        71, 73, 75 -> WeatherType.SNOWFALL
        77 -> WeatherType.SNOW_GRAINS
        80, 81, 82 -> WeatherType.RAIN_SHOWER
        85, 86 -> WeatherType.SNOW_SHOWER
        95 -> WeatherType.THUNDERSTORM
        96, 99 -> WeatherType.THUNDERSTORM_WITH_HAIL
        else -> WeatherType.UNKNOWN
    }