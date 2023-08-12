package ru.yotfr.actualweather.usecase

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.actualweather.repository.ActualWeatherRepository
import ru.yotfr.common.DataState
import ru.yotfr.common.on
import ru.yotfr.shared.model.TodayWeatherModel
import ru.yotfr.shared.repository.SharedRepository
import javax.inject.Inject

class GetTodayWeatherUseCase @Inject constructor(
    private val actualWeatherRepository: ActualWeatherRepository,
    private val sharedRepository: SharedRepository
) {
    suspend operator fun invoke() = flow<DataState<TodayWeatherModel>> {
        emit(DataState.Loading())
        Log.d("TEST","Emit loading")
        sharedRepository.getSelectedPlace().on(
            success = { place ->
                emit(
                    actualWeatherRepository.getTodayForecast(
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