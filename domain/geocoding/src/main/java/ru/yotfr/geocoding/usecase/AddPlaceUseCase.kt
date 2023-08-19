package ru.yotfr.geocoding.usecase

import ru.yotfr.geocoding.repository.GeocodingRepository
import ru.yotfr.shared.model.PlaceModel
import javax.inject.Inject

class AddPlaceUseCase @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) {
    suspend operator fun invoke(placeModel: PlaceModel) {
        geocodingRepository.addPlace(
            placeModel = placeModel
        )
    }
}