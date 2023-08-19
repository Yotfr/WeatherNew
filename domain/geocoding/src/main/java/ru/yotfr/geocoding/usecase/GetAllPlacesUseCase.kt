package ru.yotfr.geocoding.usecase

import ru.yotfr.geocoding.repository.GeocodingRepository
import ru.yotfr.shared.model.PlaceModel
import javax.inject.Inject

class GetAllPlacesUseCase @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) {
    suspend operator fun invoke(): List<PlaceModel> {
        return geocodingRepository.getAllPlaces()
    }
}