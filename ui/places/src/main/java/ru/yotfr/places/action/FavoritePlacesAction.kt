package ru.yotfr.places.action

import ru.yotfr.shared.model.PlaceModel
import ru.yotfr.shared.redux.Action
sealed class FavoritePlacesAction : Action {

    // Middlewares
    object OpenedScreen : FavoritePlacesAction()
    data class PlaceSetAsHome(val placeId: Long): FavoritePlacesAction()

    // Reducer
    data class PlacesLoaded(val places: List<PlaceModel>): FavoritePlacesAction()

}