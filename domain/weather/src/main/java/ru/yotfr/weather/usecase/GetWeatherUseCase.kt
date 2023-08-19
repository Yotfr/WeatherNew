package ru.yotfr.weather.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.common.DataState
import ru.yotfr.shared.model.PlaceModel
import ru.yotfr.weather.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(placeModel: PlaceModel) = flow {
        emit(DataState.Loading())
        emit(
            weatherRepository.getWeather(
                placeId = placeModel.id,
                latitude = placeModel.lat,
                longitude = placeModel.lon
            )
        )
    }.flowOn(Dispatchers.IO)
}