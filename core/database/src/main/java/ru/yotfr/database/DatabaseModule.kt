package ru.yotfr.database

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.yotfr.database.actualweather.ActualWeatherLocalProvider
import ru.yotfr.database.actualweather.dao.ActualWeatherDao
import ru.yotfr.database.places.PlacesLocalProvider
import ru.yotfr.database.places.dao.PlacesDao

@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): MDatabase {
        return Database(context)
    }

    @Provides
    fun provideActualWeatherDao(database: MDatabase): ActualWeatherDao {
        return database.actualWeatherDao
    }

    @Provides
    fun providePlacesDao(database: MDatabase): PlacesDao {
        return database.placesDao
    }

    @Provides
    fun provideActualWeatherLocalProvider(actualWeatherDao: ActualWeatherDao): ActualWeatherLocalProvider {
        return ActualWeatherLocalProvider(actualWeatherDao)
    }

    @Provides
    fun providePlacesLocalProvider(placesDao: PlacesDao): PlacesLocalProvider {
        return PlacesLocalProvider(placesDao)
    }
}