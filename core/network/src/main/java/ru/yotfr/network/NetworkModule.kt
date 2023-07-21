package ru.yotfr.network

import dagger.Module
import dagger.Provides
import ru.yotfr.network.actualweather.ActualWeatherService
import ru.yotfr.network.actualweather.WeatherProvider
import ru.yotfr.network.places.PlacesProvider
import ru.yotfr.network.places.PlacesService
import javax.inject.Singleton

@Module
@Singleton
object NetworkModule {

    @Provides
    fun provideActualWeatherService() : ActualWeatherService {
        return ActualWeatherService()
    }

    @Provides
    fun provideWeatherProvider(actualWeatherService: ActualWeatherService): WeatherProvider {
        return WeatherProvider(actualWeatherService)
    }

    @Provides
    fun providePlacesService() : PlacesService {
        return PlacesService()
    }

    @Provides
    fun providePlacesProvider(placesService: PlacesService): PlacesProvider {
        return PlacesProvider(placesService)
    }

}