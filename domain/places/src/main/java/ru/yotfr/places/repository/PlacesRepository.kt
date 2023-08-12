package ru.yotfr.places.repository

import ru.yotfr.common.DataState
import ru.yotfr.shared.model.PlaceModel

interface PlacesRepository {
    suspend fun getPlace(placeId: Long): DataState<PlaceModel>
}