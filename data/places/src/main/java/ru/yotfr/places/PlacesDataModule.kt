package ru.yotfr.places

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.places.PlacesLocalProvider
import ru.yotfr.network.geocoding.GeocodingRemoteProvider
import ru.yotfr.places.repository.PlacesRepository

@InstallIn(SingletonComponent::class)
@Module
object PlacesDataModule {

    @Provides
    fun providePlacesRepository(
        geocodingRemoteProvider: GeocodingRemoteProvider,
        placesLocalProvider: PlacesLocalProvider
    ) : PlacesRepository {
        return PlacesRepositoryImpl(
            geocodingRemoteProvider,
            placesLocalProvider
        )
    }
}