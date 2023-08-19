package ru.yotfr.geocoding.repository

import ru.yotfr.common.DataState
import ru.yotfr.shared.model.PlaceModel

interface GeocodingRepository {
    suspend fun getPlaceById(placeId: Long): PlaceModel
    suspend fun getHomePlace(): PlaceModel?
    suspend fun getLocationPlace(): DataState<PlaceModel>
    suspend fun getAllPlaces(): List<PlaceModel>
    suspend fun searchPlaces(query: String): DataState<List<PlaceModel>>

    suspend fun addPlace(placeModel: PlaceModel)
}