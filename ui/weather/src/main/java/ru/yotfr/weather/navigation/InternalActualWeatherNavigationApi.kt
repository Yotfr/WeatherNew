package ru.yotfr.weather.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.yotfr.shared.navigation.ActualWeatherNavigationConstant
import ru.yotfr.shared.navigation.NavigationApi
import ru.yotfr.shared.navigation.PlacesNavigationConstant
import ru.yotfr.weather.screen.WeatherScreen

internal object InternalActualWeatherNavigationApi : NavigationApi {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = ActualWeatherNavigationConstant.mainScreenRoute,
            route = ActualWeatherNavigationConstant.nestedRoute
        ) {
            composable(ActualWeatherNavigationConstant.mainScreenRoute) {
                WeatherScreen(
                    toPlaces = { navController.navigate(PlacesNavigationConstant.nestedRoute) }
                )
            }
        }
    }
}