package ru.yotfr.places.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.yotfr.geocoding.usecase.AddPlaceUseCase
import ru.yotfr.geocoding.usecase.SearchPlacesUseCase
import ru.yotfr.places.action.SearchPlacesAction
import ru.yotfr.places.middleware.SearchPlacesNetworkingMiddleware
import ru.yotfr.places.reducer.SearchPlacesReducer
import ru.yotfr.places.state.SearchPlacesState
import ru.yotfr.shared.model.PlaceModel
import ru.yotfr.shared.redux.LoggingMiddleware
import ru.yotfr.shared.redux.Store
import javax.inject.Inject
@HiltViewModel
class SearchPlacesViewModel @Inject constructor(
    private val searchPlacesUseCase: SearchPlacesUseCase,
    private val addPlaceUseCase: AddPlaceUseCase
) : ViewModel() {
    private val store = Store(
        initialState = SearchPlacesState(),
        reducer = SearchPlacesReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
            SearchPlacesNetworkingMiddleware(
                searchPlacesUseCase = searchPlacesUseCase,
                addPlaceUseCase = addPlaceUseCase
            )
        )
    )

    val screenState = store.state
    val screenEvents = store.events

    fun searchedPlaceClicked(placeModel: PlaceModel) {
        viewModelScope.launch {
            store.dispatch(SearchPlacesAction.PlaceAdded(placeModel))
        }
    }

    fun queryChanged(newQuery: String) {
        viewModelScope.launch {
            store.dispatch(SearchPlacesAction.SearchPlacesQueryChanged(newQuery))
        }
    }
}