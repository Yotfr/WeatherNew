package ru.yotfr.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.yotfr.common.log.log
import ru.yotfr.geocoding.usecase.GetCurrentPlaceUseCase
import ru.yotfr.shared.redux.LoggingMiddleware
import ru.yotfr.shared.redux.Store
import ru.yotfr.weather.action.WeatherAction
import ru.yotfr.weather.middleware.WeatherNetworkingMiddleware
import ru.yotfr.weather.reducer.WeatherReducer
import ru.yotfr.weather.state.WeatherState
import ru.yotfr.weather.usecase.GetWeatherUseCase
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getCurrentPlaceUseCase: GetCurrentPlaceUseCase
) : ViewModel() {
    private val store = Store(
        initialState = WeatherState(),
        reducer = WeatherReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
            WeatherNetworkingMiddleware(
                getWeatherUseCase = getWeatherUseCase,
                getCurrentPlaceUseCase = getCurrentPlaceUseCase
            )
        )
    )

    val screenState: StateFlow<WeatherState> = store.state

    fun openedScreen() {
        log.i("received action: Entering screen")
        viewModelScope.launch {
            store.dispatch(WeatherAction.OpenedScreen)
        }
    }
}