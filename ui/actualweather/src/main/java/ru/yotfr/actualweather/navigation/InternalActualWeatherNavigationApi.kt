package ru.yotfr.actualweather.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.yotfr.actualweather.screen.TodayWeatherScreen
import ru.yotfr.actualweather.screen.WeeklyWeatherScreen
import ru.yotfr.actualweather.viewmodel.TodayWeatherViewModel
import ru.yotfr.actualweather.viewmodel.WeeklyWeatherViewModel
import ru.yotfr.shared.navigation.ActualWeatherNavigationConstant
import ru.yotfr.shared.navigation.NavigationApi

internal object InternalActualWeatherNavigationApi : NavigationApi {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = ActualWeatherNavigationConstant.todayScreenRoute,
            route = ActualWeatherNavigationConstant.nestedRoute
        ) {
            composable(ActualWeatherNavigationConstant.todayScreenRoute) {
                val todayWeatherViewModel = hiltViewModel<TodayWeatherViewModel>()
                TodayWeatherScreen(
                    vm = todayWeatherViewModel,
                    navigateToWeeklyForecast = {
                        navController.navigate(
                            ActualWeatherNavigationConstant.weeklyScreenRoute
                        )
                    }
                )
            }
            composable(ActualWeatherNavigationConstant.weeklyScreenRoute) {
                val weeklyWeatherViewModel = hiltViewModel<WeeklyWeatherViewModel>()
                WeeklyWeatherScreen(
                    vm = weeklyWeatherViewModel,
                    navigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}