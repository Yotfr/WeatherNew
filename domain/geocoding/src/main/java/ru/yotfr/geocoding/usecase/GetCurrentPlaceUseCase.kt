package ru.yotfr.geocoding.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.common.DataState
import ru.yotfr.geocoding.repository.GeocodingRepository
import javax.inject.Inject

class GetCurrentPlaceUseCase @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) {
    suspend operator fun invoke(placeId: Long?) = flow {
        emit(DataState.Loading())
        placeId?.let { id ->
            val placeModel = geocodingRepository.getPlaceById(
                placeId = id
            )
            emit(
                DataState.Success(
                    data = placeModel
                )
            )
        } ?: kotlin.run {
            val homePlace = geocodingRepository.getHomePlace()
            homePlace?.let { place ->
                emit(
                    DataState.Success(
                    data = place
                    )
                )
            } ?: kotlin.run {
                emit(
                    geocodingRepository.getLocationPlace()
                )
            }
        }
    }.flowOn(Dispatchers.IO)
}