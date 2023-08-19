package ru.yotfr.geocoding.mapper

import ru.yotfr.database.geocoding.model.PlaceEntity
import ru.yotfr.shared.model.PlaceModel
import ru.yotfr.network.geocoding.model.PlaceDTO

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

fun PlaceDTO.mapToPlaceModel(isLocation: Boolean): PlaceModel {
    return PlaceModel(
        name = name,
        lat = lat,
        lon = lon,
        country = country,
        state = state,
        isHome = false,
        isLocation = isLocation,
        localNames = localNames,
        id = 0L
    )
}

fun PlaceModel.mapToPlaceEntity(): PlaceEntity {
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