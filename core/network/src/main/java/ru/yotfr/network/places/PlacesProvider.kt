package ru.yotfr.network.places

import ru.yotfr.network.calladapter.NetworkResponse
import ru.yotfr.network.places.model.PlacesDTO
import javax.inject.Inject

class PlacesProvider @Inject internal constructor(
    private val placesService: PlacesService
) {
    suspend fun getPlaces(
        name: String,
        count: Int,
        lang: String
    ): NetworkResponse<PlacesDTO> = placesService.searchPlaces(
        name = name,
        count = count,
        lang = lang,
    )
}