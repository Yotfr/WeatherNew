package ru.yotfr.actualweather.middleware

import kotlinx.coroutines.flow.collectLatest
import ru.yotfr.actualweather.action.WeeklyWeatherAction
import ru.yotfr.actualweather.state.WeeklyWeatherState
import ru.yotfr.actualweather.usecase.GetWeeklyWeatherUseCase
import ru.yotfr.common.DataState
import ru.yotfr.shared.redux.Middleware
import ru.yotfr.shared.redux.Store

class WeeklyWeatherNetworkingMiddleware(
    private val getWeeklyWeatherUseCase: GetWeeklyWeatherUseCase
) : Middleware<WeeklyWeatherState, WeeklyWeatherAction> {
    override suspend fun process(
        action: WeeklyWeatherAction,
        currentState: WeeklyWeatherState,
        store: Store<WeeklyWeatherState, WeeklyWeatherAction>
    ) {
        when(action) {
            WeeklyWeatherAction.OpenedScreen -> {
                getWeeklyWeatherUseCase().collectLatest { dataState ->
                    when(dataState) {
                        is DataState.Exception -> {
                            store.dispatch(WeeklyWeatherAction.LoadingDataFailed(dataState.cause))
                        }
                        is DataState.Loading -> {
                            store.dispatch(WeeklyWeatherAction.LoadingDataStarted)
                        }
                        is DataState.Success -> {
                            store.dispatch(WeeklyWeatherAction.LoadingDataSucceed(dataState.data))
                        }
                    }
                }
            }
            else -> {}
        }
    }
}