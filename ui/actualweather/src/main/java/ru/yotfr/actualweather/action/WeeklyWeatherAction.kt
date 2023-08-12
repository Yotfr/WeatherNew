package ru.yotfr.actualweather.action

import ru.yotfr.common.ExceptionCause
import ru.yotfr.shared.model.WeeklyWeatherModel
import ru.yotfr.shared.redux.Action

sealed class WeeklyWeatherAction: Action {
    // Reducer
    data class SelectedDayChanged(val newIndex: Int) : WeeklyWeatherAction()
    data class LoadingDataFailed(val cause: ExceptionCause) : WeeklyWeatherAction()
    data class LoadingDataSucceed(val weatherModel: WeeklyWeatherModel) : WeeklyWeatherAction()
    object LoadingDataStarted : WeeklyWeatherAction()
    // Middlewares
    object OpenedScreen : WeeklyWeatherAction()
}