package ru.yotfr.actualweather.middleware

import android.util.Log
import kotlinx.coroutines.flow.collectLatest
import ru.yotfr.actualweather.action.TodayWeatherAction
import ru.yotfr.actualweather.state.TodayWeatherState
import ru.yotfr.actualweather.usecase.GetTodayWeatherUseCase
import ru.yotfr.common.model.DataState
import ru.yotfr.shared.redux.Middleware
import ru.yotfr.shared.redux.Store

class TodayWeatherNetworkingMiddleware(
    private val getTodayWeatherUseCase: GetTodayWeatherUseCase
) : Middleware<TodayWeatherState, TodayWeatherAction> {
    override suspend fun process(
        action: TodayWeatherAction,
        currentState: TodayWeatherState,
        store: Store<TodayWeatherState, TodayWeatherAction>
    ) {
        when(action) {
            TodayWeatherAction.OpenedScreen -> {
                Log.d("TEST","SCREEN OPENED")
                getTodayWeatherUseCase().collectLatest { dataState ->
                    when(dataState) {
                        is DataState.Exception -> {
                            store.dispatch(TodayWeatherAction.LoadingDataFailed(dataState.cause))
                        }
                        is DataState.Loading -> {
                            store.dispatch(TodayWeatherAction.LoadingDataStarted)
                        }
                        is DataState.Success -> {
                            store.dispatch(TodayWeatherAction.LoadingDataSucceed(dataState.data))
                        }
                    }
                }
            }
            else -> {}
        }
    }
}