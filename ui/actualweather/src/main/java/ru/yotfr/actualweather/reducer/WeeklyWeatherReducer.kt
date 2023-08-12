package ru.yotfr.actualweather.reducer

import ru.yotfr.actualweather.action.WeeklyWeatherAction
import ru.yotfr.actualweather.state.WeeklyWeatherState
import ru.yotfr.shared.redux.Reducer

class WeeklyWeatherReducer : Reducer<WeeklyWeatherState, WeeklyWeatherAction> {
    override fun reduce(
        currentState: WeeklyWeatherState,
        action: WeeklyWeatherAction
    ): WeeklyWeatherState {
        return when(action) {
            is WeeklyWeatherAction.SelectedDayChanged -> {
                currentState.copy(
                    selectedWeatherModel = currentState.dailyForecast[action.newIndex]
                )
            }
            is WeeklyWeatherAction.LoadingDataFailed -> {
                currentState.copy(
                    showProgressBar = false,
                    errorMessage = "Что-то пошло не так"
                )
            }
            WeeklyWeatherAction.LoadingDataStarted -> {
                currentState.copy(
                    showProgressBar = true,
                    errorMessage = ""
                )
            }
            is WeeklyWeatherAction.LoadingDataSucceed -> {
                currentState.copy(
                    showProgressBar = false,
                    selectedWeatherModel = action.weatherModel.dailyForecast[0],
                    dailyForecast = action.weatherModel.dailyForecast,
                    errorMessage = ""
                )
            }
            else -> currentState
        }
    }
}