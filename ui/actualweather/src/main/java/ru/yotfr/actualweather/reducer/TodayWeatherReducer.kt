package ru.yotfr.actualweather.reducer

import ru.yotfr.actualweather.action.TodayWeatherAction
import ru.yotfr.actualweather.state.TodayWeatherState
import ru.yotfr.shared.redux.Reducer

class TodayWeatherReducer : Reducer<TodayWeatherState, TodayWeatherAction> {
    override fun reduce(
        currentState: TodayWeatherState,
        action: TodayWeatherAction
    ): TodayWeatherState {
        return when(action) {
            is TodayWeatherAction.SelectedHourChanged -> {
                currentState.copy(
                    selectedWeatherModel = currentState.hourlyForecast[action.newIndex]
                )
            }
            is TodayWeatherAction.LoadingDataFailed -> {
                currentState.copy(
                    showProgressBar = false,
                    errorMessage = "Что-то пошло не так"
                )
            }
            TodayWeatherAction.LoadingDataStarted -> {
                currentState.copy(
                    showProgressBar = true,
                    errorMessage = ""
                )
            }
            is TodayWeatherAction.LoadingDataSucceed -> {
                currentState.copy(
                    showProgressBar = false,
                    selectedWeatherModel = action.weatherModel.hourlyForecast[0],
                    hourlyForecast = action.weatherModel.hourlyForecast,
                    errorMessage = ""
                )
            }
            else -> currentState
        }
    }
}