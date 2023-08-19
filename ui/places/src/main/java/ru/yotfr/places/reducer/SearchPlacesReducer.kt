package ru.yotfr.places.reducer

import ru.yotfr.places.action.SearchPlacesAction
import ru.yotfr.places.events.SearchPlacesEvent
import ru.yotfr.places.state.SearchPlacesState
import ru.yotfr.shared.redux.Reducer
import ru.yotfr.shared.redux.Store

class SearchPlacesReducer : Reducer<SearchPlacesState, SearchPlacesAction, SearchPlacesEvent> {
    override suspend fun reduce(
        currentState: SearchPlacesState,
        action: SearchPlacesAction,
        store: Store<SearchPlacesState, SearchPlacesAction, SearchPlacesEvent>
    ): SearchPlacesState {
        return when (action) {
            SearchPlacesAction.LoadingDataStarted -> {
                currentState.copy(
                    isLoading = true
                )
            }

            is SearchPlacesAction.LoadingSearchedPlaceFailed -> {
                currentState.copy(
                    isLoading = false
                )
            }

            is SearchPlacesAction.LoadingSearchedPlaceSucceed -> {
                currentState.copy(
                    isLoading = false,
                    places = action.places
                )
            }

            SearchPlacesAction.PlaceAddedSuccessfully -> {
                store.send(SearchPlacesEvent.NavigateBack)
                currentState
            }

            else -> {
                currentState
            }
        }
    }
}