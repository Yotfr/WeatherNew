package ru.yotfr.database.places

import ru.yotfr.database.places.dao.PlacesDao
import ru.yotfr.database.places.model.PlaceEntity
import javax.inject.Inject

class PlacesLocalProvider @Inject constructor(
    private val placesDao: PlacesDao
) {
    suspend fun upsertPlace(placeEntity: PlaceEntity) =
        placesDao.upsertPlace(placeEntity)

    suspend fun deletePlace(placeEntity: PlaceEntity) =
        placesDao.deletePlace(placeEntity)

    suspend fun getPlace(id: Long): PlaceEntity =
        placesDao.getPlace(id)
}