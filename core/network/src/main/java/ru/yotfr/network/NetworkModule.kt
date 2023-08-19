package ru.yotfr.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.network.weather.CurrentWeatherService
import ru.yotfr.network.weather.DailyWeatherService
import ru.yotfr.network.weather.WeatherRemoteProvider
import ru.yotfr.network.geocoding.DirectGeocodingService
import ru.yotfr.network.geocoding.GeocodingRemoteProvider
import ru.yotfr.network.geocoding.ReverseGeocodingService

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideCurrentWeatherService() : CurrentWeatherService {
        return CurrentWeatherService()
    }

    @Provides
    fun provideDailyWeatherService() : DailyWeatherService {
        return DailyWeatherService()
    }

    @Provides
    fun provideWeatherRemoteProvider(
        currentWeatherService: CurrentWeatherService,
        dailyWeatherService: DailyWeatherService
    ): WeatherRemoteProvider {
        return WeatherRemoteProvider(currentWeatherService, dailyWeatherService)
    }

    @Provides
    fun provideDirectGeocodingService() : DirectGeocodingService {
        return DirectGeocodingService()
    }

    @Provides
    fun provideReverseGeocodingService() : ReverseGeocodingService {
        return ReverseGeocodingService()
    }

    @Provides
    fun provideGeocodingRemoteProvider(
        directGeocodingService: DirectGeocodingService,
        reverseGeocodingService: ReverseGeocodingService
    ): GeocodingRemoteProvider {
        return GeocodingRemoteProvider(directGeocodingService, reverseGeocodingService)
    }

}