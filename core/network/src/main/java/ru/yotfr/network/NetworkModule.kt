package ru.yotfr.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.network.actualweather.ActualWeatherRemoteProvider
import ru.yotfr.network.actualweather.ActualWeatherService
import ru.yotfr.network.places.PlacesRemoteProvider
import ru.yotfr.network.places.PlacesService

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideActualWeatherService() : ActualWeatherService {
        return ActualWeatherService()
    }

    @Provides
    fun provideWeatherProvider(actualWeatherService: ActualWeatherService): ActualWeatherRemoteProvider {
        return ActualWeatherRemoteProvider(actualWeatherService)
    }

    @Provides
    fun providePlacesService() : PlacesService {
        return PlacesService()
    }

    @Provides
    fun providePlacesProvider(placesService: PlacesService): PlacesRemoteProvider {
        return PlacesRemoteProvider(placesService)
    }

}