package ru.yotfr.places.reducer

import ru.yotfr.places.action.FavoritePlacesAction
import ru.yotfr.places.events.FavoritePlacesEvent
import ru.yotfr.places.state.FavoritePlacesState
import ru.yotfr.shared.redux.Reducer
import ru.yotfr.shared.redux.Store

class FavoritePlacesReducer : Reducer<FavoritePlacesState, FavoritePlacesAction, FavoritePlacesEvent> {
    override suspend fun reduce(
        currentState: FavoritePlacesState,
        action: FavoritePlacesAction,
        store: Store<FavoritePlacesState, FavoritePlacesAction, FavoritePlacesEvent>
    ): FavoritePlacesState {
        return when(action) {
            is FavoritePlacesAction.PlacesLoaded -> {
                currentState.copy(
                    places = action.places
                )
            }
            else -> {
                currentState
            }
        }
    }
}