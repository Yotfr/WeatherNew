package ru.yotfr.database.places.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import ru.yotfr.database.places.model.PlaceEntity

@Dao
interface PlacesDao {
    @Upsert(entity = PlaceEntity::class)
    suspend fun upsertPlace(placeEntity: PlaceEntity)

    @Delete(entity = PlaceEntity::class)
    suspend fun deletePlace(placeEntity: PlaceEntity)

    @Query("SELECT * FROM places WHERE id = :id")
    suspend fun getPlaceById(id: Long): PlaceEntity

    @Query("SELECT * FROM places WHERE isSelected = 1")
    suspend fun getSelectedPlace(): PlaceEntity?

    @Query("SELECT * FROM places WHERE isHome = 1")
    suspend fun getHomePlace(): PlaceEntity?

}