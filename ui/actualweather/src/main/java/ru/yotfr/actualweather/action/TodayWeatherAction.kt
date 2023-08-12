package ru.yotfr.actualweather.action

import ru.yotfr.common.ExceptionCause
import ru.yotfr.shared.model.TodayWeatherModel
import ru.yotfr.shared.redux.Action

sealed class TodayWeatherAction: Action {
    // Reducer
    data class SelectedHourChanged(val newIndex: Int) : TodayWeatherAction()
    data class LoadingDataFailed(val cause: ExceptionCause) : TodayWeatherAction()
    data class LoadingDataSucceed(val weatherModel: TodayWeatherModel) : TodayWeatherAction()
    object LoadingDataStarted : TodayWeatherAction()
    // Middlewares
    object OpenedScreen : TodayWeatherAction()
}