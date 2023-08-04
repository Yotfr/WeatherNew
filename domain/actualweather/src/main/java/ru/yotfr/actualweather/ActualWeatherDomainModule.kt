package ru.yotfr.actualweather

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.actualweather.repository.ActualWeatherRepository
import ru.yotfr.actualweather.usecase.GetTodayWeatherUseCase
import ru.yotfr.actualweather.usecase.GetWeeklyWeatherUseCase
import ru.yotfr.shared.repository.SharedRepository

@InstallIn(SingletonComponent::class)
@Module
object ActualWeatherDomainModule {

    @Provides
    fun provideGetTodayWeatherUseCase(
        actualWeatherRepository: ActualWeatherRepository,
        sharedRepository: SharedRepository
    ): GetTodayWeatherUseCase {
        return GetTodayWeatherUseCase(
            actualWeatherRepository,
            sharedRepository
        )
    }

    @Provides
    fun provideGetWeeklyWeatherUseCase(
        actualWeatherRepository: ActualWeatherRepository,
        sharedRepository: SharedRepository
    ): GetWeeklyWeatherUseCase {
        return GetWeeklyWeatherUseCase(
            actualWeatherRepository,
            sharedRepository
        )
    }
}