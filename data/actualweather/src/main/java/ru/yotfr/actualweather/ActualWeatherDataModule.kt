package ru.yotfr.actualweather

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.actualweather.repository.ActualWeatherRepository
import ru.yotfr.database.actualweather.ActualWeatherLocalProvider
import ru.yotfr.network.actualweather.ActualWeatherRemoteProvider

@InstallIn(SingletonComponent::class)
@Module
object ActualWeatherDataModule {

    @Provides
    fun provideActualWeatherRepository(
        actualWeatherLocalProvider: ActualWeatherLocalProvider,
        actualWeatherRemoteProvider: ActualWeatherRemoteProvider
    ) : ActualWeatherRepository {
        return ActualWeatherRepositoryImpl(
            actualWeatherLocalProvider,
            actualWeatherRemoteProvider
        )
    }
}