package ru.yotfr.shared

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.places.PlacesLocalProvider
import ru.yotfr.location.LocationProvider
import ru.yotfr.shared.repository.SharedRepository
import ru.yotfr.shared.repository.SharedRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
object SharedDataModule {

    @Provides
    fun provideSharedRepository(
        placesLocalProvider: PlacesLocalProvider,
        locationProvider: LocationProvider
    ) : SharedRepository {
        return SharedRepositoryImpl(
            placesLocalProvider, locationProvider
        )
    }
}