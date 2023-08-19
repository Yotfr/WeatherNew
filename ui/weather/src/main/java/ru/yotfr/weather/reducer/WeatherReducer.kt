package ru.yotfr.weather.reducer

import ru.yotfr.shared.redux.Reducer
import ru.yotfr.shared.redux.Store
import ru.yotfr.weather.action.WeatherAction
import ru.yotfr.weather.event.WeatherEvent
import ru.yotfr.weather.state.WeatherState

class WeatherReducer : Reducer<WeatherState, WeatherAction, WeatherEvent> {
    override suspend fun reduce(
        currentState: WeatherState,
        action: WeatherAction,
        store: Store<WeatherState, WeatherAction, WeatherEvent>
    ): WeatherState {
        return when(action) {
            is WeatherAction.LoadingDataFailed -> {
                currentState.copy(
                    showProgressBar = false,
                    errorMessage = "Что-то пошло не так"
                )
            }
            WeatherAction.LoadingDataStarted -> {
                currentState.copy(
                    showProgressBar = true,
                    errorMessage = ""
                )
            }
            is WeatherAction.LoadingWeatherSucceed -> {
                currentState.copy(
                    showProgressBar = false,
                    weatherModel = action.fullWeatherModel,
                    placeModel = action.placeModel
                )
            }
            else -> {
                currentState
            }
        }
    }
}