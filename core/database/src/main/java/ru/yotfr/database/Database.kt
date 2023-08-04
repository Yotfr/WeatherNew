package ru.yotfr.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.yotfr.database.actualweather.dao.ActualWeatherDao
import ru.yotfr.database.actualweather.model.TodayActualWeatherEntity
import ru.yotfr.database.actualweather.model.WeeklyActualWeatherEntity
import ru.yotfr.database.converter.Converters
import ru.yotfr.database.places.dao.PlacesDao
import ru.yotfr.database.places.model.PlaceEntity

@Database(
    entities = [
        TodayActualWeatherEntity::class,
        WeeklyActualWeatherEntity::class,
        PlaceEntity::class
    ], version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MDatabase : RoomDatabase() {
    abstract val actualWeatherDao: ActualWeatherDao
    abstract val placesDao: PlacesDao
}

internal fun Database(context: Context): MDatabase {
    return Room.databaseBuilder(
        context,
        MDatabase::class.java,
        "weather_db"
    ).build()
}