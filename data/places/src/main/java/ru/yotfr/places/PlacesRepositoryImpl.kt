package ru.yotfr.places

import ru.yotfr.database.places.PlacesLocalProvider
import ru.yotfr.network.places.PlacesRemoteProvider
import ru.yotfr.places.mapper.mapToPlaceModel
import ru.yotfr.places.repository.PlacesRepository
import ru.yotfr.common.DataState
import ru.yotfr.shared.model.PlaceModel
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val placesRemoteProvider: PlacesRemoteProvider,
    private val placesLocalProvider: PlacesLocalProvider
) : PlacesRepository {
    override suspend fun getPlace(placeId: Long): DataState<PlaceModel> {
        val localCache = placesLocalProvider.getPlace(id = placeId).mapToPlaceModel()
        return DataState.Success(data = localCache)
    }
}