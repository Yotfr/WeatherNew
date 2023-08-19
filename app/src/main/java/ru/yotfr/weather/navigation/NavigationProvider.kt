package ru.yotfr.weather.navigation

import ru.yotfr.places.navigation.PlacesNavigationApi

data class NavigationProvider(
    val actualWeatherNavigationApi: ActualWeatherNavigationApi,
    val placesNavigationApi: PlacesNavigationApi
)
