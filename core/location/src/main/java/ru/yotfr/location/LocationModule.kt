package ru.yotfr.location

import android.app.Application
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object LocationModule {

    @Provides
    fun provideFusedLocationProviderClient(@ApplicationContext appContext: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(appContext)
    }

    @Provides
    fun provideLocationTracker(@ApplicationContext appContext: Context, fusedLocationProviderClient: FusedLocationProviderClient): LocationTracker {
        return LocationTracker(fusedLocationProviderClient, appContext)
    }

    @Provides
    fun provideLocationProvider(locationTracker: LocationTracker): LocationProvider {
        return LocationProvider(locationTracker)
    }
}