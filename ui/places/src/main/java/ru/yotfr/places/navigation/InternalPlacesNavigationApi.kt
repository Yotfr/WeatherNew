package ru.yotfr.places.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.yotfr.places.screen.FavoritePlacesScreen
import ru.yotfr.places.screen.SearchPlaceScreen
import ru.yotfr.shared.navigation.NavigationApi
import ru.yotfr.shared.navigation.PlacesNavigationConstant

internal object InternalPlacesNavigationApi : NavigationApi {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = PlacesNavigationConstant.favoritePlacesScreenRoute,
            route = PlacesNavigationConstant.nestedRoute
        ) {
            composable(PlacesNavigationConstant.favoritePlacesScreenRoute) {
                FavoritePlacesScreen() {
                    navController.navigate(PlacesNavigationConstant.searchPlacesScreenRoute)
                }
            }
            composable(PlacesNavigationConstant.searchPlacesScreenRoute) {
                SearchPlaceScreen() {
                    navController.popBackStack()
                }
            }
        }
    }
}