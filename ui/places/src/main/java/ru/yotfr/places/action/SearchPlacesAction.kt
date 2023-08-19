package ru.yotfr.places.action

import ru.yotfr.common.ExceptionCause
import ru.yotfr.shared.model.PlaceModel
import ru.yotfr.shared.redux.Action

sealed class SearchPlacesAction : Action {

    // Middlewares
    data class SearchPlacesQueryChanged(val newQuery: String) : SearchPlacesAction()
    data class PlaceAdded(val placeModel: PlaceModel) : SearchPlacesAction()

    // Reducer

    object LoadingDataStarted : SearchPlacesAction()
    data class LoadingSearchedPlaceSucceed(val places: List<PlaceModel>) : SearchPlacesAction()
    data class LoadingSearchedPlaceFailed(val cause: ExceptionCause) : SearchPlacesAction()
    object PlaceAddedSuccessfully : SearchPlacesAction()

}