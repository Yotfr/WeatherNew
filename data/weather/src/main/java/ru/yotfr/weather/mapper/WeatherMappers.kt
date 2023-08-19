package ru.yotfr.weather.mapper

import ru.yotfr.database.weather.model.CurrentWeatherEntity
import ru.yotfr.database.weather.model.ThreeHourWeatherEntity
import ru.yotfr.database.weather.model.WeatherEntity
import ru.yotfr.database.weather.model.WindEntity
import ru.yotfr.network.weather.model.CurrentWeatherDTO
import ru.yotfr.network.weather.model.DailyWeatherDTO
import ru.yotfr.network.weather.model.WeatherDTO
import ru.yotfr.network.weather.model.WindDTO
import ru.yotfr.shared.model.CurrentWeatherModel
import ru.yotfr.shared.model.PartOfTheDay
import ru.yotfr.shared.model.ThreeHourWeatherModel
import ru.yotfr.shared.model.WeatherType
import ru.yotfr.shared.model.WeatherTypeModel
import ru.yotfr.shared.model.WindDirection
import ru.yotfr.shared.model.WindModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun DailyWeatherDTO.mapToThreeHourForecast(): List<ThreeHourWeatherModel> {
    return list.map { threeHourDTO ->
        ThreeHourWeatherModel(
            cloudCover = threeHourDTO.clouds.percent,
            temperature = threeHourDTO.mainDTO.temp,
            apparentTemperature = threeHourDTO.mainDTO.feelsLike,
            humidity = threeHourDTO.mainDTO.humidity,
            pressure = threeHourDTO.mainDTO.pressure,
            precipitationProbability = threeHourDTO.precipitationProbability,
            partOfTheDay = threeHourDTO.sysDTO.partOfTheDay.toPartOfTheDay(),
            weather = threeHourDTO.weatherDTO.first().mapToWeatherTypeModel(),
            wind = threeHourDTO.wind.mapToWindModel(),
            time = LocalDateTime.ofInstant(Instant.ofEpochSecond(threeHourDTO.dt.toLong()), ZoneId.systemDefault())
        )
    }
}

fun String.toPartOfTheDay(): PartOfTheDay {
    return if (this == "n") PartOfTheDay.NIGHT else PartOfTheDay.DAY
}

fun ThreeHourWeatherEntity.mapToThreeHourWeatherModel(): ThreeHourWeatherModel {
    return ThreeHourWeatherModel(
        cloudCover = cloudCover,
        temperature = temperature,
        apparentTemperature = apparentTemperature,
        humidity = humidity,
        pressure = pressure,
        precipitationProbability = precipitationProbability,
        partOfTheDay = partOfTheDay.toPartOfTheDay(),
        weather = weather.mapToWeatherTypeModel(),
        wind = wind.mapToWindModel(),
        time = LocalDateTime.ofInstant(Instant.ofEpochSecond(dt.toLong()), ZoneId.systemDefault())
    )
}

fun CurrentWeatherEntity.mapToCurrentWeatherModel(): CurrentWeatherModel {
    return CurrentWeatherModel(
        weather = weatherDTO.mapToWeatherTypeModel(),
        visibility = visibility,
        wind = wind.mapToWindModel(),
        cloudCover = cloudCover,
        temperature = temperature,
        apparentTemperature = apparentTemperature,
        humidity = humidity,
        pressure = pressure,
        sunriseTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunriseTime.toLong()), ZoneId.systemDefault()),
        sunsetTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunsetTime.toLong()), ZoneId.systemDefault()),
        timezone = timezone
    )
}

fun DailyWeatherDTO.mapToThreeHourEntity(placeId: Long): List<ThreeHourWeatherEntity> {
    return list.map { threeHourDTO ->
        ThreeHourWeatherEntity(
            cloudCover = threeHourDTO.clouds.percent,
            temperature = threeHourDTO.mainDTO.temp,
            apparentTemperature = threeHourDTO.mainDTO.feelsLike,
            humidity = threeHourDTO.mainDTO.humidity,
            pressure = threeHourDTO.mainDTO.pressure,
            precipitationProbability = threeHourDTO.precipitationProbability,
            partOfTheDay = threeHourDTO.sysDTO.partOfTheDay,
            weather = threeHourDTO.weatherDTO.first().mapToWeatherEntity(),
            wind = threeHourDTO.wind.mapToWindEntity(),
            placeId = placeId,
            dt = threeHourDTO.dt
        )
    }
}

fun CurrentWeatherDTO.mapToCurrentWeatherModel(): CurrentWeatherModel {
    return CurrentWeatherModel(
        weather = weatherDTO.first().mapToWeatherTypeModel(),
        visibility = visibility,
        wind = wind.mapToWindModel(),
        cloudCover = clouds.percent,
        temperature = mainDTO.temp,
        apparentTemperature = mainDTO.feelsLike,
        humidity = mainDTO.humidity,
        pressure = mainDTO.pressure,
        sunriseTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sysDTO.sunrise.toLong()), ZoneId.systemDefault()),
        sunsetTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sysDTO.sunset.toLong()), ZoneId.systemDefault()),
        timezone = timezone
    )
}

fun CurrentWeatherDTO.mapToCurrentWeatherEntity(placeId: Long): CurrentWeatherEntity {
    return CurrentWeatherEntity(
        placeId = placeId,
        weatherDTO = weatherDTO.first().mapToWeatherEntity(),
        visibility = visibility,
        wind = wind.mapToWindEntity(),
        cloudCover = clouds.percent,
        temperature = mainDTO.temp,
        apparentTemperature = mainDTO.feelsLike,
        humidity = mainDTO.humidity,
        pressure = mainDTO.pressure,
        sunriseTime = sysDTO.sunrise,
        sunsetTime = sysDTO.sunset,
        timezone = timezone
    )
}

fun WeatherDTO.mapToWeatherTypeModel(): WeatherTypeModel {
    return WeatherTypeModel(
        weatherType = id.mapToWeatherType(),
        description = description
    )
}

fun WeatherEntity.mapToWeatherTypeModel(): WeatherTypeModel {
    return WeatherTypeModel(
        weatherType = weatherId.mapToWeatherType(),
        description = description
    )
}

fun WeatherDTO.mapToWeatherEntity(): WeatherEntity {
    return WeatherEntity(
        weatherId = id,
        description = description
    )
}

fun WindDTO.mapToWindModel(): WindModel {
    return WindModel(
        direction = direction.mapToWindDirection(),
        gustSpeed = gust,
        speed = speed
    )
}

fun WindEntity.mapToWindModel(): WindModel {
    return WindModel(
        direction = direction.mapToWindDirection(),
        gustSpeed = gust,
        speed = speed
    )
}

fun WindDTO.mapToWindEntity(): WindEntity {
    return WindEntity(
        direction = direction,
        gust = gust,
        speed = speed
    )
}

fun Int.mapToWindDirection(): WindDirection {
    return when {
        this in 335..360 || this in 0..24 -> WindDirection.N
        this in 25..64 -> WindDirection.NE
        this in 65..114 -> WindDirection.E
        this in 115..154 -> WindDirection.SE
        this in 155..204 -> WindDirection.S
        this in 205..244 -> WindDirection.SW
        this in 245..294 -> WindDirection.W
        this in 295..334 -> WindDirection.NW
        else -> throw IllegalArgumentException("Wind direction angle is greater than 360")
    }
}
fun Int.mapToWeatherType(): WeatherType {
    return WeatherType.values().firstOrNull { it.code == this } ?: throw IllegalArgumentException("Illegal weather code")
}