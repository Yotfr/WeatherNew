package ru.yotfr.places.middleware

import ru.yotfr.geocoding.repository.GeocodingRepository
import ru.yotfr.places.action.FavoritePlacesAction
import ru.yotfr.places.events.FavoritePlacesEvent
import ru.yotfr.places.state.FavoritePlacesState
import ru.yotfr.shared.redux.Middleware
import ru.yotfr.shared.redux.Store

class FavoritePlacesNetworkingMiddleware(
    private val geocodingRepository: GeocodingRepository
) : Middleware<FavoritePlacesState, FavoritePlacesAction, FavoritePlacesEvent> {
    override suspend fun process(
        action: FavoritePlacesAction,
        currentState: FavoritePlacesState,
        store: Store<FavoritePlacesState, FavoritePlacesAction, FavoritePlacesEvent>
    ) {
        when(action) {
            FavoritePlacesAction.OpenedScreen -> {
                val places = geocodingRepository.getAllPlaces()
                store.dispatch(FavoritePlacesAction.PlacesLoaded(places = places))
            }
            else -> {}
        }
    }
}