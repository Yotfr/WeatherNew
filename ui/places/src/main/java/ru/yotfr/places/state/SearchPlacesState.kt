package ru.yotfr.places.state

import ru.yotfr.shared.model.PlaceModel
import ru.yotfr.shared.redux.State

data class SearchPlacesState(
    val isLoading: Boolean = false,
    val places: List<PlaceModel> = emptyList()
) : State