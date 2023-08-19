package ru.yotfr.database.geocoding.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import ru.yotfr.database.geocoding.model.PlaceEntity

@Dao
interface PlacesDao {
    @Query("SELECT * FROM place WHERE isLocation = 1")
    suspend fun getLocationPlace(): PlaceEntity?

    @Query("SELECT * FROM place WHERE isHome = 1")
    suspend fun getHomePlace(): PlaceEntity?

    @Query("SELECT * FROM place")
    suspend fun getPlaces(): List<PlaceEntity>

    @Query("SELECT * FROM place WHERE id = :placeId")
    suspend fun getPlaceById(placeId: Long): PlaceEntity

    @Upsert(entity = PlaceEntity::class)
    suspend fun insertPlace(placeEntity: PlaceEntity)

    @Query("DELETE FROM place WHERE isLocation = 1")
    suspend fun deleteLocationPlace()
    @Transaction
    suspend fun updateLocationPlace(placeEntity: PlaceEntity) {
        deleteLocationPlace()
        insertPlace(placeEntity)
    }
}