package ru.yotfr.database.geocoding

import ru.yotfr.database.geocoding.dao.PlacesDao
import ru.yotfr.database.geocoding.model.PlaceEntity
import javax.inject.Inject

class GeocodingLocalProvider @Inject constructor(
    private val placesDao: PlacesDao
) {
    suspend fun getLocationPlace(): PlaceEntity? =
        placesDao.getLocationPlace()
    suspend fun getHomePlace(): PlaceEntity? =
        placesDao.getHomePlace()

    suspend fun getPlaces(): List<PlaceEntity> =
        placesDao.getPlaces()

    suspend fun addPlace(placeEntity: PlaceEntity) {
        placesDao.insertPlace(placeEntity = placeEntity)
    }

    suspend fun getPlaceById(placeId: Long): PlaceEntity =
        placesDao.getPlaceById(placeId = placeId)

    suspend fun updateLocationPlace(placeEntity: PlaceEntity) =
        placesDao.updateLocationPlace(placeEntity = placeEntity)
}