package ru.yotfr.network

import dagger.Module
import dagger.Provides
import ru.yotfr.network.actualweather.ActualWeatherService
import ru.yotfr.network.actualweather.WeatherProvider
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

}