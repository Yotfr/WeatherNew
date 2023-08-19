package ru.yotfr.places.middleware

import kotlinx.coroutines.flow.collectLatest
import ru.yotfr.common.DataState
import ru.yotfr.geocoding.usecase.AddPlaceUseCase
import ru.yotfr.geocoding.usecase.SearchPlacesUseCase
import ru.yotfr.places.action.SearchPlacesAction
import ru.yotfr.places.events.SearchPlacesEvent
import ru.yotfr.places.state.SearchPlacesState
import ru.yotfr.shared.redux.Middleware
import ru.yotfr.shared.redux.Store

class SearchPlacesNetworkingMiddleware(
    private val searchPlacesUseCase: SearchPlacesUseCase,
    private val addPlaceUseCase: AddPlaceUseCase
) : Middleware<SearchPlacesState, SearchPlacesAction, SearchPlacesEvent> {
    override suspend fun process(
        action: SearchPlacesAction,
        currentState: SearchPlacesState,
        store: Store<SearchPlacesState, SearchPlacesAction, SearchPlacesEvent>
    ) {
        when(action) {
            is SearchPlacesAction.SearchPlacesQueryChanged -> {
                action.newQuery.takeIf { it.isNotBlank() }?.let { query ->
                    searchPlacesUseCase(query).collectLatest {
                        when(it) {
                            is DataState.Loading -> {
                                store.dispatch(SearchPlacesAction.LoadingDataStarted)
                            }
                            is DataState.Success -> {
                                store.dispatch(SearchPlacesAction.LoadingSearchedPlaceSucceed(it.data))
                            }
                            is DataState.Exception -> {
                                store.dispatch(SearchPlacesAction.LoadingSearchedPlaceFailed(it.cause))
                            }
                        }
                    }
                }
            }
            is SearchPlacesAction.PlaceAdded -> {
                addPlaceUseCase(action.placeModel)
                store.dispatch(SearchPlacesAction.PlaceAddedSuccessfully)
            }
            else -> {}
        }
    }
}