package ru.yotfr.places.mapper

import ru.yotfr.database.places.model.PlaceEntity
import ru.yotfr.network.geocoding.model.PlaceDTO
import ru.yotfr.shared.model.PlaceModel

fun PlaceEntity.mapToPlaceModel(): PlaceModel {
    return PlaceModel(
        name = name,
        id = id,
        latitude = latitude,
        longitude = longitude,
        admin1 = admin1,
        country = country,
        country_code = country_code,
        elevation = elevation,
        feature_code = feature_code,
        timezone = timezone,
        isSelected = isSelected,
        isHome = isHome
    )
}

fun PlaceDTO.mapToPlaceModel(selected: Boolean, home: Boolean): PlaceModel {
    return PlaceModel(
        name = name,
        id = id,
        latitude = latitude,
        longitude = longitude,
        admin1 = admin1,
        country = country,
        country_code = country_code,
        elevation = elevation,
        feature_code = feature_code,
        timezone = timezone,
        isSelected = selected,
        isHome = home
    )
}

fun PlaceModel.mapToPlaceEntity(): PlaceEntity {
    return PlaceEntity(
        name = name,
        id = id,
        latitude = latitude,
        longitude = longitude,
        admin1 = admin1,
        country = country,
        country_code = country_code,
        elevation = elevation,
        feature_code = feature_code,
        timezone = timezone,
        isSelected = isSelected,
        isHome = isHome
    )
}