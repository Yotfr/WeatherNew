package ru.yotfr.places

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.places.navigation.PlacesNavigationApi
import ru.yotfr.places.navigation.PlacesNavigationApiImpl

@InstallIn(SingletonComponent::class)
@Module
object PlacesUiModule {

    @Provides
    fun providePlacesNavigationApi(): PlacesNavigationApi {
        return PlacesNavigationApiImpl()
    }
}