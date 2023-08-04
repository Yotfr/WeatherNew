package ru.yotfr.weather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.actualweather.navigation.ActualWeatherNavigationApi
import ru.yotfr.weather.navigation.NavigationProvider

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideNavigationProvider(actualWeatherNavigationApi: ActualWeatherNavigationApi): NavigationProvider {
        return NavigationProvider(actualWeatherNavigationApi)
    }
}