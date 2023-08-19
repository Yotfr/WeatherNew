package ru.yotfr.network.geocoding

import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.network.geocoding.model.PlaceDTO
import javax.inject.Inject

class GeocodingRemoteProvider @Inject internal constructor(
    private val directGeocodingService: DirectGeocodingService,
    private val reverseGeocodingService: ReverseGeocodingService
) {
    suspend fun getPlacesByQuery(
        query: String
    ): NetworkResponse<List<PlaceDTO>> = directGeocodingService.searchPlaces(
        query = query
    )

    suspend fun getPlacesByCoordinates(
        latitude: Float,
        longitude: Float
    ): NetworkResponse<List<PlaceDTO>> = reverseGeocodingService.searchPlaces(
        latitude = latitude,
        longitude = longitude
    )
}