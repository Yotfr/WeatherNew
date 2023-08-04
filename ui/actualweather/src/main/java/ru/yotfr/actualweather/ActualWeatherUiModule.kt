package ru.yotfr.actualweather

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.actualweather.navigation.ActualWeatherNavigationApi
import ru.yotfr.actualweather.navigation.ActualWeatherNavigationApiImpl

@InstallIn(SingletonComponent::class)
@Module
object ActualWeatherUiModule {

    @Provides
    fun provideActualWeatherNavigationApi(): ActualWeatherNavigationApi {
        return ActualWeatherNavigationApiImpl()
    }
}