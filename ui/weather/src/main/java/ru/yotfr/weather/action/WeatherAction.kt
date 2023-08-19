package ru.yotfr.weather.action

import ru.yotfr.common.ExceptionCause
import ru.yotfr.shared.model.FullWeatherModel
import ru.yotfr.shared.model.PlaceModel
import ru.yotfr.shared.redux.Action

sealed class WeatherAction: Action {
    // Reducer
    object LoadingDataStarted : WeatherAction()
    data class LoadingDataFailed(val cause: ExceptionCause) : WeatherAction()
    data class LoadingWeatherSucceed(val fullWeatherModel: FullWeatherModel, val placeModel: PlaceModel) : WeatherAction()
    // Middlewares
    object OpenedScreen : WeatherAction()
}