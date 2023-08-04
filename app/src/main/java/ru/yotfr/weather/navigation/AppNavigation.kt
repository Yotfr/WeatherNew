package ru.yotfr.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.yotfr.shared.navigation.ActualWeatherNavigationConstant

@Composable
fun AppNavGraph(
    navController: NavHostController,
    navigationProvider: NavigationProvider
) {
    NavHost(navController = navController, startDestination = ActualWeatherNavigationConstant.nestedRoute) {
        navigationProvider.actualWeatherNavigationApi.registerGraph(
            navController = navController,
            navGraphBuilder = this
        )
    }
}