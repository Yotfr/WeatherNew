package ru.yotfr.weather

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.weather.WeatherLocalProvider
import ru.yotfr.network.weather.WeatherRemoteProvider
import ru.yotfr.weather.repository.WeatherRepository
import ru.yotfr.weather.repository.WeatherRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
object WeatherModule {
    @Provides
    fun provideWeatherRepository(
        weatherRemoteProvider: WeatherRemoteProvider,
        weatherLocalProvider: WeatherLocalProvider
    ) : WeatherRepository {
        return WeatherRepositoryImpl(
            weatherRemoteProvider, weatherLocalProvider
        )
    }
}