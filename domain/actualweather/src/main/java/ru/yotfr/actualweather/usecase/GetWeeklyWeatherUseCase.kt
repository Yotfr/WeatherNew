package ru.yotfr.actualweather.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.actualweather.repository.ActualWeatherRepository
import ru.yotfr.common.DataState
import ru.yotfr.common.on
import ru.yotfr.shared.model.WeeklyWeatherModel
import ru.yotfr.shared.repository.SharedRepository
import javax.inject.Inject

class GetWeeklyWeatherUseCase @Inject constructor(
    private val actualWeatherRepository: ActualWeatherRepository,
    private val sharedRepository: SharedRepository
) {
    suspend operator fun invoke() = flow<DataState<WeeklyWeatherModel>> {
        emit(DataState.Loading())
        sharedRepository.getSelectedPlace().on(
            success = { place ->
                emit(
                    actualWeatherRepository.getWeeklyForecast(
                        latitude = place.latitude.toFloat(),
                        longitude = place.longitude.toFloat(),
                        placeId = place.id.toLong()
                    )
                )
            },
            exception = {
                emit(DataState.Exception(it))
            },
            loading = {}
        )
    }.flowOn(Dispatchers.IO)
}