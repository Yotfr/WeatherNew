package ru.yotfr.places

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.places.PlacesLocalProvider
import ru.yotfr.network.places.PlacesRemoteProvider
import ru.yotfr.places.repository.PlacesRepository

@InstallIn(SingletonComponent::class)
@Module
object PlacesDataModule {

    @Provides
    fun providePlacesRepository(
        placesRemoteProvider: PlacesRemoteProvider,
        placesLocalProvider: PlacesLocalProvider
    ) : PlacesRepository {
        return PlacesRepositoryImpl(
            placesRemoteProvider,
            placesLocalProvider
        )
    }
}