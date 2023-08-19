package ru.yotfr.weather

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.weather.navigation.ActualWeatherNavigationApi
import ru.yotfr.weather.navigation.ActualWeatherNavigationApiImpl

@InstallIn(SingletonComponent::class)
@Module
object ActualWeatherUiModule {

    @Provides
    fun provideActualWeatherNavigationApi(): ActualWeatherNavigationApi {
        return ActualWeatherNavigationApiImpl()
    }
}