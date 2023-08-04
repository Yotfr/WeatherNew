package ru.yotfr.shared.mapper

import ru.yotfr.database.places.model.PlaceEntity
import ru.yotfr.shared.model.PlaceModel

fun PlaceEntity.mapToPlaceModel(): PlaceModel {
    return PlaceModel(
        name = "$country, $admin1, $name",
        id = id,
        latitude = latitude,
        longitude = longitude
    )
}