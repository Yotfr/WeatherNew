package ru.yotfr.geocoding

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.geocoding.GeocodingLocalProvider
import ru.yotfr.geocoding.repository.GeocodingRepository
import ru.yotfr.geocoding.repository.GeocodingRepositoryImpl
import ru.yotfr.location.LocationProvider
import ru.yotfr.network.geocoding.GeocodingRemoteProvider

@InstallIn(SingletonComponent::class)
@Module
object GeocodingModule {
    @Provides
    fun provideGeocodingRepository(
        geocodingRemoteProvider: GeocodingRemoteProvider,
        geocodingLocalProvider: GeocodingLocalProvider,
        locationProvider: LocationProvider
    ) : GeocodingRepository {
        return GeocodingRepositoryImpl(
            geocodingRemoteProvider,
            geocodingLocalProvider,
            locationProvider
        )
    }
}