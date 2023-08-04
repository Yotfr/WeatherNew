package ru.yotfr.actualweather.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import ru.yotfr.shared.navigation.NavigationApi

interface ActualWeatherNavigationApi : NavigationApi

class ActualWeatherNavigationApiImpl : ActualWeatherNavigationApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalActualWeatherNavigationApi.registerGraph(
            navController, navGraphBuilder
        )
    }
}