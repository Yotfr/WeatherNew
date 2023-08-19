package ru.yotfr.places.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.yotfr.geocoding.repository.GeocodingRepository
import ru.yotfr.places.action.FavoritePlacesAction
import ru.yotfr.places.middleware.FavoritePlacesNetworkingMiddleware
import ru.yotfr.places.reducer.FavoritePlacesReducer
import ru.yotfr.places.state.FavoritePlacesState
import ru.yotfr.shared.redux.LoggingMiddleware
import ru.yotfr.shared.redux.Store
import javax.inject.Inject

@HiltViewModel
class FavoritePlacesViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) : ViewModel() {
    private val store = Store(
        initialState = FavoritePlacesState(),
        reducer = FavoritePlacesReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
            FavoritePlacesNetworkingMiddleware(
                geocodingRepository = geocodingRepository
            )
        )
    )

    val screenState = store.state
    val screenEvents = store.events

    fun openedScreen() {
        viewModelScope.launch {
            store.dispatch(FavoritePlacesAction.OpenedScreen)
        }
    }

    fun homeClicked(placeId: Long) {
        viewModelScope.launch {
            store.dispatch(FavoritePlacesAction.PlaceSetAsHome(placeId))
        }
    }
}