package ru.yotfr.geocoding.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.common.DataState
import ru.yotfr.geocoding.repository.GeocodingRepository
import javax.inject.Inject

class SearchPlacesUseCase @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) {
    suspend operator fun invoke(query: String) = flow {
        emit(DataState.Loading())
        emit(
            geocodingRepository.searchPlaces(
                query = query
            )
        )
    }.flowOn(Dispatchers.IO)
}