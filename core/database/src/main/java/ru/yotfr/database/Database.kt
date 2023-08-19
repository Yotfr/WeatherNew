package ru.yotfr.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.yotfr.database.geocoding.dao.PlacesDao
import ru.yotfr.database.geocoding.model.PlaceEntity
import ru.yotfr.database.weather.dao.WeatherDao
import ru.yotfr.database.weather.model.CurrentWeatherEntity
import ru.yotfr.database.weather.model.ThreeHourWeatherEntity

@Database(
    entities = [
        CurrentWeatherEntity::class,
        ThreeHourWeatherEntity::class,
        PlaceEntity::class
    ], version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
    abstract val placesDao: PlacesDao
}

internal fun Database(context: Context): MDatabase {
    return Room.databaseBuilder(
        context,
        MDatabase::class.java,
        "weather_db"
    ).build()
}