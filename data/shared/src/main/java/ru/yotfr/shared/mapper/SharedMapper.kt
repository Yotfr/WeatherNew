package ru.yotfr.shared.mapper

import ru.yotfr.database.geocoding.model.PlaceEntity
import ru.yotfr.network.geocoding.model.PlaceDTO
import ru.yotfr.shared.model.PlaceModel

fun PlaceEntity.mapToPlaceModel(): PlaceModel {
    return PlaceModel(
        id = id,
        name = name,
        lat = lat,
        lon = lon,
        country = country,
        state = state,
        isHome = isHome,
        isLocation = isLocation,
        localNames = localNames
    )
}

fun PlaceDTO.mapToPlaceEntity(isLocation: Boolean): PlaceEntity {
    return PlaceEntity(
        name = name,
        lat = lat,
        lon = lon,
        country = country,
        state = state,
        isHome = false,
        isLocation = isLocation,
        localNames = localNames
    )
}