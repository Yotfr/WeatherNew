package ru.yotfr.weather.middleware

import kotlinx.coroutines.flow.collectLatest
import ru.yotfr.common.DataState
import ru.yotfr.common.log.log
import ru.yotfr.geocoding.usecase.GetCurrentPlaceUseCase
import ru.yotfr.shared.redux.Middleware
import ru.yotfr.shared.redux.Store
import ru.yotfr.weather.action.WeatherAction
import ru.yotfr.weather.event.WeatherEvent
import ru.yotfr.weather.state.WeatherState
import ru.yotfr.weather.usecase.GetWeatherUseCase

class WeatherNetworkingMiddleware(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getCurrentPlaceUseCase: GetCurrentPlaceUseCase
) : Middleware<WeatherState, WeatherAction, WeatherEvent> {
    override suspend fun process(
        action: WeatherAction,
        currentState: WeatherState,
        store: Store<WeatherState, WeatherAction, WeatherEvent>
    ) {
        when (action) {
            WeatherAction.OpenedScreen -> {
                getCurrentPlaceUseCase(placeId = null).collectLatest { placeDataState ->
                    when (placeDataState) {
                        is DataState.Loading -> {
                            store.dispatch(WeatherAction.LoadingDataStarted)
                        }

                        is DataState.Exception -> {
                            store.dispatch(
                                WeatherAction.LoadingDataFailed(
                                    cause = placeDataState.cause
                                )
                            )
                        }

                        is DataState.Success -> {
                            getWeatherUseCase(placeModel = placeDataState.data).collectLatest { weatherDataState ->
                                when(weatherDataState) {
                                    is DataState.Loading -> {
                                        store.dispatch(WeatherAction.LoadingDataStarted)
                                    }

                                    is DataState.Exception -> {
                                        store.dispatch(
                                            WeatherAction.LoadingDataFailed(
                                                cause = weatherDataState.cause
                                            )
                                        )
                                    }
                                    is DataState.Success -> {
                                        store.dispatch(
                                            WeatherAction.LoadingWeatherSucceed(
                                                fullWeatherModel = weatherDataState.data,
                                                placeModel = placeDataState.data
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            else -> {}
        }
    }
}