package ru.yotfr.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.geocoding.GeocodingLocalProvider
import ru.yotfr.database.geocoding.dao.PlacesDao
import ru.yotfr.database.weather.WeatherLocalProvider
import ru.yotfr.database.weather.dao.WeatherDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MDatabase {
        return Database(context)
    }

    @Provides
    fun provideWeatherDao(database: MDatabase): WeatherDao {
        return database.weatherDao
    }

    @Provides
    fun providePlacesDao(database: MDatabase): PlacesDao {
        return database.placesDao
    }

    @Provides
    fun provideWeatherLocalProvider(weatherDao: WeatherDao): WeatherLocalProvider {
        return WeatherLocalProvider(weatherDao)
    }

    @Provides
    fun providePlacesLocalProvider(placesDao: PlacesDao): GeocodingLocalProvider {
        return GeocodingLocalProvider(placesDao)
    }
}