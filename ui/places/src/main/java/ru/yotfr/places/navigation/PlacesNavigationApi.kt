package ru.yotfr.places.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import ru.yotfr.shared.navigation.NavigationApi

interface PlacesNavigationApi : NavigationApi

class PlacesNavigationApiImpl : PlacesNavigationApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalPlacesNavigationApi.registerGraph(
            navController, navGraphBuilder
        )
    }
}